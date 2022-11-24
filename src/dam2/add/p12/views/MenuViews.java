package dam2.add.p12.views;

import java.util.Scanner;

public class MenuViews {
  public static int showLoginMenu() {
    System.out.println("Bienvenido.");
    System.out.println("Selecione la opción deseada");
    System.out.println("1. - Entrar");
    System.out.println("0. - Terminar");
    return askForOption(2);
  }

  public static int showAdminMenu() {
    System.out.println("Selecione la opción deseada");
    System.out.println("1. - Añadir pregunta");
    System.out.println("2. - Importar preguntas");
    System.out.println("0. - Volver");
    return askForOption(3);
  }

  public static int showStartMenu() {
    System.out.println("Selecione la opción deseada");
    System.out.println("1. - Jugar");
    System.out.println("2. - Ver records");
    System.out.println("3. - Instrucciones");
    System.out.println("0. - Volver");
    return askForOption(4);
  }

  private static boolean askYesNo(String msj) {
    boolean response = false;

    boolean isValid = false;
    while (!isValid) {
      System.out.println(msj);
      Scanner sc = new Scanner(System.in);
      String userInput = sc.nextLine();
      if (userInput.equalsIgnoreCase("S")) {
        response = isValid = true;
      } else if (userInput.equalsIgnoreCase("N")) {
        isValid = true;
      } else {
        System.out.println("Debe escribir S/N");
      }
    }
    return response;

  }

  private static int askForOption(int numeroOpciones) {
    int response = -1;

    boolean isValid = false;
    while (!isValid) {
      Scanner sc = new Scanner(System.in);
      String userInput = sc.nextLine();
      if (esUnEntero(userInput)) {
        response = Integer.parseInt(userInput);
        if (response >= 0 && response < numeroOpciones) {
          isValid = true;
        } else {
          response = -1;
          System.out.println("Debe elegir una de las opciones.");
        }
      } else {
        response = -1;
        System.out.println("Debe escribir un número.");
      }
    }
    return response;
  }

  private static boolean esUnEntero(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static void printSuccess(String msj) {
    System.out.println("EXITO >>> " + msj);
  }

  public static void printError(String msj) {
    System.out.println("ERROR >>> " + msj);
  }

  public static void printInfo(String msj) {
    System.out.println(msj);
  }
}
