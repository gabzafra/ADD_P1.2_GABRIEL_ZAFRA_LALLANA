package dam2.add.p12;

import dam2.add.p12.models.Usuario;
import dam2.add.p12.services.AdminController;
import dam2.add.p12.services.LoginController;
import dam2.add.p12.services.Partida;
import dam2.add.p12.views.MenuViews;

public class MainP12 {
  private static final int NUM_PREGUNTAS = 5;

  public static void main(String[] args) {

    LoginController logCtrl = new LoginController();
    AdminController admCtrl = new AdminController();
    Partida game = new Partida(NUM_PREGUNTAS);
    Usuario usuario = new Usuario();
    boolean haveEnd = false;
    int option = 1;
    String currentMenu = "login";
    while (!haveEnd) {
      switch (currentMenu) {
        case "login":
          option = MenuViews.showLoginMenu();
          if (option == 1) {
            usuario = logCtrl.login();
            if (usuario.getNombre().equals("admin")) {
              currentMenu = "admin";
            } else if (!usuario.getNombre().equals("")) {
              currentMenu = "start";
            } else {
              MenuViews.printError("Los datos introducidos no son válidos");
            }
          } else {
            haveEnd = true;
          }
          break;
        case "admin":
          option = MenuViews.showAdminMenu();
          if (option == 1) {
            admCtrl.createNewQuestion();
          } else if (option == 2) {
            admCtrl.importQuestions();
          } else {
            currentMenu = "login";
          }
          break;
        case "start":
          option = MenuViews.showStartMenu();
          if (option == 1) {
            game = new Partida(NUM_PREGUNTAS);
            game.start();
            game.askForPlayerInfo();
            if (MenuViews.askYesNo("¿Desea ver el resumen de la partida? S/N")) {
              game.reportGame();
            }
            game.saveRecord();
            game.showHighScores();
            MenuViews.waitEnter();
          } else if (option == 2) {
            game.showHighScores();
          } else if (option == 3) {
            MenuViews.showHelp();
          } else {
            currentMenu = "login";
          }
          break;
        default:
          haveEnd = true;
      }
    }
    MenuViews.printInfo("Saliendo de la aplicación. Hasta la proxima!");
  }
}
