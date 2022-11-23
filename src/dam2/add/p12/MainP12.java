package dam2.add.p12;

import dam2.add.p12.services.Partida;

public class MainP12 {

  public static void main(String[] args) {
    Partida game = new Partida(22);
    game.start();
    System.out.println("End");
  }
}
