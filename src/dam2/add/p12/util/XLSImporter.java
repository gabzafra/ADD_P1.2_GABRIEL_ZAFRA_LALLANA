package dam2.add.p12.util;

import java.io.File;
import dam2.add.p12.models.Pregunta;
import dam2.add.p12.models.PreguntaDAO;
import jxl.Sheet;
import jxl.Workbook;

public class XLSImporter {
  private static final int COL_ENUNCIADO = 0;
  private static final int COL_OPCION_0 = 1;
  private static final int COL_OPCION_1 = 2;
  private static final int COL_OPCION_2 = 3;
  private static final int COL_RESPUESTA = 4;
  private static final PreguntaDAO DAO = new PreguntaDAO();

  public static boolean importXLS(String xlsFileURL) {
    Sheet pagina;
    try {
      Workbook libro = Workbook.getWorkbook(new File(xlsFileURL));
      pagina = libro.getSheet(0);
    } catch (Exception e) {
      return false;
    }
    // Ojo que empieza en la fila 1 para no leer los encabezados del xls
    for (int i = 1; i < pagina.getRows(); i++) {
      Pregunta question = new Pregunta();
      question.setQuestion(pagina.getCell(COL_ENUNCIADO, i).getContents());
      question.setResponseArr(new String[] {pagina.getCell(COL_OPCION_0, i).getContents(),
          pagina.getCell(COL_OPCION_1, i).getContents(),
          pagina.getCell(COL_OPCION_2, i).getContents()});
      question.setCorrectAnswer(Integer.parseInt(pagina.getCell(COL_RESPUESTA, i).getContents()));
      DAO.createQuestion(question);
    }
    return true;
  }
}
