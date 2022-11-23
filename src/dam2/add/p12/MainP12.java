package dam2.add.p12;

import dam2.add.p12.models.Jugador;
import dam2.add.p12.services.Partida;
import dam2.add.p12.views.GameViews;

public class MainP12 {

  public static void main(String[] args) {
    Partida game = new Partida(2);
    game.start();
    Jugador player = game.getPlayerData();
    player.setId(GameViews.askForInnitials());
    System.out.println(player.getId() + "  <.-.---.-.>  " + player.getRecord());
  }
}