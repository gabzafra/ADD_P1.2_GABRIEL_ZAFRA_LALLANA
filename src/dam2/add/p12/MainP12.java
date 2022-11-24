package dam2.add.p12;

import java.io.File;
import dam2.add.p12.services.Partida;
import dam2.add.p12.services.XLSImporter;

public class MainP12 {

  public static void main(String[] args) {
    Partida game = new Partida(2);
    game.start();
    game.askForPlayerInfo();
    game.reportGame();
    game.saveRecord();
    game.showHighScores();
    XLSImporter.importXLS("ficheros" + File.separator + "preguntas.xls");
  }
}
