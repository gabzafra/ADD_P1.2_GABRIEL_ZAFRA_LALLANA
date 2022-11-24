package dam2.add.p12;

import dam2.add.p12.services.AdminController;
import dam2.add.p12.services.Partida;

public class MainP12 {

  public static void main(String[] args) {
    Partida game = new Partida(2);
    // game.start();
    // game.askForPlayerInfo();
    // game.reportGame();
    // game.saveRecord();
    // game.showHighScores();
    // AdminController.importQuestions();
    AdminController.createNewQuestion();
  }
}
