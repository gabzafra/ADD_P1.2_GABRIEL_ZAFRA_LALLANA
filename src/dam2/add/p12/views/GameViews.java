package dam2.add.p12.views;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;
import static com.diogonunes.jcolor.Attribute.RED_TEXT;
import java.util.Scanner;
import dam2.add.p12.models.Pregunta;
import dam2.add.p12.services.Partida;

public class GameViews {

  public static void printQuestion(Pregunta question) {
    printInfo("¿" + question.getQuestion() + "?");
    for (int i = 0; i < question.getResponseArr().length; i++) {
      printInfo((i + 1) + ". " + question.getResponseArr()[i]);
    }
  }

  public static void printQuestion(Pregunta question, int answer) {
    printInfo("¿" + question.getQuestion() + "?");
    int correctAnswer = question.getCorrectAnswer();
    if (correctAnswer == answer) {
      printSuccess("ACIERTO");
    } else {
      printError("FALLO");
    }
    for (int i = 0; i < question.getResponseArr().length; i++) {
      if (i == correctAnswer) {
        printSuccess((i + 1) + ". " + question.getResponseArr()[i]);
      } else if (i == answer) {
        printError((i + 1) + ". " + question.getResponseArr()[i]);
      } else {
        printInfo((i + 1) + ". " + question.getResponseArr()[i]);
      }
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
    System.out.println(colorize(msj, GREEN_TEXT()));
  }

  public static void printError(String msj) {
    System.out.println(colorize(msj, RED_TEXT()));
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

  public static void printReport(Partida game) {
    printInfo("Estas son sus respuestas:");
    game.getAnswerLog().forEach(answer -> {
      printQuestion(answer.getQuestion(), answer.getResponse());
    });
    printInfo("Ha tenido un total de " + game.getPlayerData().getRecord() + " aciertos.");
  }
}
