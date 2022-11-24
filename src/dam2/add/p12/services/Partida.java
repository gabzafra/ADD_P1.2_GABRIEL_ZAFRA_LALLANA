package dam2.add.p12.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import dam2.add.p12.models.Answer;
import dam2.add.p12.models.Jugador;
import dam2.add.p12.models.Pregunta;
import dam2.add.p12.models.PreguntaDAO;
import dam2.add.p12.models.RecordsDAO;
import dam2.add.p12.views.GameViews;
import dam2.add.p12.views.PdfView;

public class Partida {
  private static final PreguntaDAO QUESTION_DAO = new PreguntaDAO();
  private static final RecordsDAO RECORD_DAO = new RecordsDAO();
  private Jugador player;
  private int numberOfQuestions;
  private ArrayList<Answer> answerLog;

  public Partida(int numberOfQuestions) {
    this.numberOfQuestions = numberOfQuestions;
    this.answerLog = new ArrayList<Answer>();
    this.player = new Jugador();
  }

  public void start() {
    HashMap<Integer, Pregunta> listaPreguntas = getRandomQuestions();
    listaPreguntas.values().stream().forEach(question -> {
      GameViews.printQuestion(question);
      int answer = GameViews.getAnswer() - 1;
      answerLog.add(new Answer(question, answer));
      if (question.getCorrectAnswer() == answer) {
        GameViews.printSuccess("Respuesta correcta!");
        player.setRecord(player.getRecord() + 1);
      } else {
        GameViews.printError("Respuesta erronea!");
        GameViews.printInfo(
            "La respuesta correcta es " + question.getResponseArr()[question.getCorrectAnswer()]);
      }
    });
  }

  public Jugador saveRecord() {
    Jugador foundPlayer = RECORD_DAO.getPlayerById(player.getId());
    if (foundPlayer.getId().equals("")) {
      return RECORD_DAO.createNewPlayer(player);
    } else if (foundPlayer.getRecord() < player.getRecord()) {
      return RECORD_DAO.updatePlayer(player);
    } else {
      return foundPlayer;
    }
  }

  public void showHighScores() {
    ArrayList<Jugador> playersList = RECORD_DAO.getAllPlayers();
    if (playersList.size() > 1) {
      Collections.sort(playersList, (p1, p2) -> p2.getRecord().compareTo(p1.getRecord()));
    }
    GameViews.printHighScores(playersList);
  }

  public void askForPlayerInfo() {
    player.setId(GameViews.askForInnitials());
  };

  public void reportGame() {
    PdfView.printReport(answerLog, player.getRecord().intValue());
  }

  private HashMap<Integer, Pregunta> getRandomQuestions() {
    HashMap<Integer, Pregunta> respuesta = new HashMap<Integer, Pregunta>();

    ArrayList<Integer> idsArr = new ArrayList<Integer>();
    idsArr.addAll(QUESTION_DAO.getAllQuestions().keySet());

    // Mirar si hay suficientes preguntas si no devolver las que tengamos
    if (idsArr.size() < numberOfQuestions) {
      return QUESTION_DAO.getAllQuestions();
    } else {
      Random rnd = new Random();
      for (int i = 0; i < numberOfQuestions; i++) {
        int pos = rnd.nextInt(idsArr.size());
        int questionId = idsArr.get(pos);
        idsArr.remove(pos);
        respuesta.put(questionId, QUESTION_DAO.getQuestionById(questionId));
      }
      return respuesta;
    }
  }
}
