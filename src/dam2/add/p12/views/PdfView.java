package dam2.add.p12.views;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import dam2.add.p12.models.Answer;
import dam2.add.p12.models.Pregunta;

public class PdfView {
  private static final File OUT_FILE = new File("ficheros" + File.separator + "partida.pdf");

  public static void printReport(ArrayList<Answer> answerList, int record) {
    Document docu = new Document(PageSize.A4, 20, 20, 70, 50);
    PdfWriter output = null;
    try {
      output = PdfWriter.getInstance(docu, new FileOutputStream(OUT_FILE));
      docu.open();

      Paragraph title = new Paragraph("Estas son sus respuestas:",
          FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));
      title.setAlignment(Element.ALIGN_CENTER);

      title.setSpacingBefore(0);
      title.setSpacingAfter(18);

      docu.add(title);

      answerList.forEach(answer -> {
        try {
          docu.add(formatQuestion(answer.getQuestion(), answer.getResponse()));
        } catch (DocumentException e) {
          GameViews.printError("Error al generar pdf.");
        }
      });

      Paragraph finalScore = new Paragraph(
          "Ha tenido un total de " + record + (record == 1 ? " acierto." : " aciertos."),
          FontFactory.getFont(FontFactory.HELVETICA, 16, Font.NORMAL,
              new BaseColor(150, 150, 150)));
      finalScore.setAlignment(Element.ALIGN_RIGHT);
      finalScore.setSpacingBefore(0);

      docu.add(finalScore);

      docu.close();
      output.close();

      try {
        Desktop.getDesktop().open(OUT_FILE);
      } catch (IOException ex) {
        GameViews.printError("Error al intentar abrir el PDF");
      }

    } catch (Exception e) {
      GameViews.printError("Error al generar pdf.");
    }
  }

  private static Paragraph formatQuestion(Pregunta question, int answer) {

    Paragraph text = new Paragraph();

    Font blackFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
    Font redFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.RED);
    Font greenFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.GREEN);

    Chunk header = new Chunk("¿" + question.getQuestion() + "?", blackFont);

    text.add(header);

    Chunk headerStatus;
    int correctAnswer = question.getCorrectAnswer();
    if (correctAnswer == answer) {
      headerStatus = new Chunk("ACIERTO", greenFont);
    } else {
      headerStatus = new Chunk("FALLO", redFont);
    }

    text.add(headerStatus);
    text.add(Chunk.NEWLINE);

    for (int i = 0; i < question.getResponseArr().length; i++) {
      Chunk option = new Chunk((i + 1) + ". " + question.getResponseArr()[i]);
      if (i == correctAnswer) {
        option.setFont(greenFont);
      } else if (i == answer) {
        option.setFont(redFont);
      } else {
        option.setFont(blackFont);
      }
      text.add(option);
      text.add(Chunk.NEWLINE);
    }
    text.setSpacingBefore(0);
    text.setSpacingAfter(18);
    return text;
  }
}
