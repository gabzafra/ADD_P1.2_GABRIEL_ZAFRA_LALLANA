package dam2.add.p12.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import dam2.add.p12.views.GameViews;

public class RecordsDAO {

  private static final File tempFile = new File("ficheros" + File.separator + "temp.txt");
  private static final File recordsFile = new File("ficheros" + File.separator + "records.txt");

  public RecordsDAO() {
    initRecordsFile();
  }

  private void initRecordsFile() {
    if (!recordsFile.exists()) {
      try {
        recordsFile.createNewFile();
      } catch (IOException e) {
        GameViews.printError("No se ha podido crear el archivo de records.");
      }
    }
  }

  public ArrayList<Jugador> getAllPlayers() {
    ArrayList<Jugador> playersList = new ArrayList<Jugador>();
    try (BufferedReader output = new BufferedReader(new FileReader(recordsFile))) {
      String cursor;
      while ((cursor = output.readLine()) != null) {
        String[] colsArr = cursor.split(":");
        Jugador player = new Jugador();
        player.setId(colsArr[0]);
        player.setRecord(Integer.parseInt(colsArr[1]));
        playersList.add(player);
      }
    } catch (Exception e) {
      return playersList;
    }
    return playersList;
  }

  public Jugador getPlayerById(String id) {
    Jugador respuesta = new Jugador();
    try (BufferedReader output = new BufferedReader(new FileReader(recordsFile))) {
      String cursor;
      while ((cursor = output.readLine()) != null) {
        String[] colsArr = cursor.split(":");
        if (colsArr[0].equals(id)) {
          respuesta.setId(colsArr[0]);
          respuesta.setRecord(Integer.parseInt(colsArr[1]));
          return respuesta;
        }
      }
    } catch (Exception e) {
      return respuesta;
    }
    return respuesta;
  }

  public Jugador updatePlayer(Jugador player) {
    Jugador foundPlayer = getPlayerById(player.getId());

    if (foundPlayer.getId().equals("")) {
      return new Jugador();
    } else {
      try {
        tempFile.createNewFile();
      } catch (IOException e) {
        return new Jugador();
      }
      try (BufferedWriter input = new BufferedWriter(new FileWriter(tempFile));
          BufferedReader output = new BufferedReader(new FileReader(recordsFile))) {
        String cursor;
        while ((cursor = output.readLine()) != null) {
          String[] colsArr = cursor.split(":");
          if (colsArr[0].equals(player.getId())) {
            cursor = player.getId() + ":" + player.getRecord();
          }
          input.write(cursor);
          input.newLine();
        }
      } catch (Exception e) {
        return new Jugador();
      }
      recordsFile.delete();
      tempFile.renameTo(recordsFile);
      return getPlayerById(player.getId());
    }
  }

  public Jugador createNewPlayer(Jugador player) {
    Jugador foundPlayer = getPlayerById(player.getId());

    if (foundPlayer.getId().equals("")) {
      try (BufferedWriter input = new BufferedWriter(new FileWriter(recordsFile, true))) {
        String formatedRow = player.getId() + ":" + player.getRecord();
        input.write(formatedRow);
        input.newLine();
      } catch (Exception e) {
        return new Jugador();
      }
      return getPlayerById(player.getId());
    } else {
      return new Jugador();
    }
  }
}
