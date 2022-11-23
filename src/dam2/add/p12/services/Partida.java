package dam2.add.p12.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import dam2.add.p12.models.Jugador;
import dam2.add.p12.models.Pregunta;
import dam2.add.p12.models.PreguntaDAO;
import dam2.add.p12.views.GameViews;

public class Partida {
  private static final PreguntaDAO QUESTION_DAO = new PreguntaDAO();
  private Jugador player;
  private int numberOfQuestions;


  public Partida(int numberOfQuestions) {
    this.numberOfQuestions = numberOfQuestions;
    this.player = new Jugador();
  }

  public void start() {
    HashMap<Integer, Pregunta> listaPreguntas = getRandomQuestions();
    listaPreguntas.values().stream().forEach(question -> {
      GameViews.printQuestion(question);
      int answer = GameViews.getAnswer() - 1;
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

  public Jugador getPlayerData() {
    return player;
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
