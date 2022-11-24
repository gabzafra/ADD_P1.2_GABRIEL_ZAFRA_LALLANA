package dam2.add.p12.services;

import java.util.Scanner;
import dam2.add.p12.models.Usuario;
import dam2.add.p12.models.UsuariosDAO;
import dam2.add.p12.views.GameViews;

public class LoginController {
  private static final UsuariosDAO DAO = new UsuariosDAO();

  public Usuario login() {
    Scanner input = new Scanner(System.in);

    Usuario user = new Usuario();
    GameViews.printInfo("Introduzca su nombre de usuario:");
    user.setNombre(input.nextLine());
    Usuario foundUser = DAO.getUserByName(user.getNombre());
    if (foundUser.getNombre().equals("")) {
      return new Usuario();
    } else {
      GameViews.printInfo("Introduzca su clave:");
      user.setClave(input.nextLine());
    }

    if (user.getClave().equals(foundUser.getClave())) {
      return foundUser;
    } else {
      return new Usuario();
    }
  }
}
