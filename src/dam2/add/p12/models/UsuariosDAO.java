package dam2.add.p12.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class UsuariosDAO {

  private static final File USERS_FILE = new File("ficheros" + File.separator + "users.txt");

  public UsuariosDAO() {
    if (getAllUsers().isEmpty()) {
      initDAO();
    }
  }

  public Usuario getUserByName(String username) {
    Usuario respuesta = new Usuario();
    try (BufferedReader output = new BufferedReader(new FileReader(USERS_FILE))) {
      String cursor;
      while ((cursor = output.readLine()) != null) {
        String[] colsArr = cursor.split(":");
        if (colsArr[0].equals(username)) {
          respuesta.setNombre(colsArr[0]);
          respuesta.setClave(colsArr[1]);
          return respuesta;
        }
      }
    } catch (Exception e) {
      return respuesta;
    }
    return respuesta;
  }

  public HashMap<String, Usuario> getAllUsers() {
    HashMap<String, Usuario> respuesta = new HashMap<>();

    try (BufferedReader output = new BufferedReader(new FileReader(USERS_FILE))) {
      String cursor;
      while ((cursor = output.readLine()) != null) {
        String[] colsArr = cursor.split(":");
        respuesta.put(colsArr[0], new Usuario(colsArr[0], colsArr[1]));
      }
    } catch (Exception e) {
      return respuesta;
    }
    return respuesta;
  }

  public Usuario createNewUser(Usuario user) {
    HashMap<String, Usuario> userList = getAllUsers();

    if (!userList.containsKey(user.getNombre())) {
      try (BufferedWriter input = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
        String formatedRow = user.getNombre() + ":" + user.getClave();
        input.write(formatedRow);
        input.newLine();
      } catch (Exception e) {
        return new Usuario();
      }
      return new Usuario(user.getNombre(), user.getClave());
    } else {
      return new Usuario();
    }
  }

  private void initDAO() {
    createNewUser(new Usuario("adam", "aaaa"));
    createNewUser(new Usuario("betty", "bbbb"));
    createNewUser(new Usuario("cecil", "cccc"));
    createNewUser(new Usuario("admin", "admin"));
  }
}
