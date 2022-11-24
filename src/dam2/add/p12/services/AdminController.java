package dam2.add.p12.services;

import java.io.File;
import dam2.add.p12.models.Pregunta;
import dam2.add.p12.models.PreguntaDAO;
import dam2.add.p12.util.XLSImporter;
import dam2.add.p12.views.GameViews;

public class AdminController {
  private static final String XLS_URL = "ficheros" + File.separator + "preguntas.xls";
  private static final PreguntaDAO DAO = new PreguntaDAO();

  public void importQuestions() {
    if (XLSImporter.importXLS(XLS_URL)) {
      GameViews.printInfo("Se han importado los datos del xls");
    } else {
      GameViews.printError("No se han podido importar los datos del xls.");
    }
  }

  public void createNewQuestion() {
    Pregunta nuevaPregunta = GameViews.askForNewQuestion();
    nuevaPregunta = DAO.createQuestion(nuevaPregunta);
    if (nuevaPregunta.getId() == 0) {
      GameViews.printError("No se ha podido crear la nueva pregunta.");
    } else {
      GameViews.printInfo("Se ha añadido al juego la nueva pregunta.");
    }
  }
}
