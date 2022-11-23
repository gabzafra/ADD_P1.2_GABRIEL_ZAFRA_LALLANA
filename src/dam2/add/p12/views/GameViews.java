package dam2.add.p12.views;

import java.util.Scanner;
import dam2.add.p12.models.Pregunta;

public class GameViews {
  public static void printQuestion(Pregunta question) {
    System.out.println("¿" + question.getQuestion() + "?");
    for (int i = 0; i < question.getResponseArr().length; i++) {
      System.out.println((i + 1) + ". " + question.getResponseArr()[i]);
    }
  }

  public static int getAnswer() {
    int response = -1;

    printInfo("Elija el número de la respuesta correcta.");

    boolean isValid = false;
    while (!isValid) {
      Scanner sc = new Scanner(System.in);
      String userInput = sc.nextLine();
      if (esUnEntero(userInput)) {
        response = Integer.parseInt(userInput);
        if (response > 0 && response < 4) {
          isValid = true;
        } else {
          response = -1;
          printError("Debe elegir una de las tres respuestas.");
        }
      } else {
        response = -1;
        printError("Debe escribir un número.");
      }
    }
    return response;
  }

  public static void printSuccess(String msj) {
    System.out.println(msj);
  }

  public static void printError(String msj) {
    System.err.println(msj);
  }

  public static void printInfo(String msj) {
    System.out.println(msj);
  }

  private static boolean esUnEntero(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static String askForInnitials() {
    printInfo("La partida ha terminado.");

    String id = "";
    printInfo("Por favor introduzca su nombre:");
    id = id.concat(askForValidString().substring(0, 1));
    printInfo("Por favor introduzca su primer apellido:");
    id = id.concat(askForValidString().substring(0, 1));
    printInfo("Por favor introduzca su segundo apellido:");
    id = id.concat(askForValidString().substring(0, 1));

    return id.toUpperCase();
  }

  private static String askForValidString() {
    Scanner input = new Scanner(System.in);
    String response = "";

    boolean isValid = false;
    while (!isValid) {
      response = input.nextLine();
      if (response.length() > 0) {
        isValid = true;
      } else {
        printError("Debe escribir algo.");
      }
    }

    return response;
  }
}
