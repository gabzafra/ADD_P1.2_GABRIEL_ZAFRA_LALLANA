package dam2.add.p12;

import dam2.add.p12.models.Usuario;
import dam2.add.p12.services.LoginController;
import dam2.add.p12.views.MenuViews;

public class MainP12 {

  public static void main(String[] args) {

    // Partida game = new Partida(2);
    // game.start();
    // game.askForPlayerInfo();
    // game.reportGame();
    // game.saveRecord();
    // game.showHighScores();
    // AdminController.importQuestions();
    // AdminController.createNewQuestion();
    LoginController logCtrl = new LoginController();
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
            // añadir pregunta
          } else if (option == 2) {
            // importar xls
          } else {
            currentMenu = "login";
          }
          break;
        case "start":
          option = MenuViews.showStartMenu();
          if (option == 1) {
            // jugar partida
          } else if (option == 2) {
            // mostrar records
          } else if (option == 3) {
            // mostrar instrucciones
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
