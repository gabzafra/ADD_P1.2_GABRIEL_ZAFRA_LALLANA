package dam2.add.p12.models;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import dam2.add.p12.views.GameViews;

public class PreguntaDAO {
  // ------------- Mock DAO
  private static final HashMap<String, Pregunta> QUESTION_LIST = new HashMap<String, Pregunta>();
  private static final File questionsFile = new File("ficheros" + File.separator + "preguntas.xml");

  public PreguntaDAO() {
    initDAO();
  }

  private void initDAO() {
    QUESTION_LIST.put(String.valueOf(1),
        new Pregunta(1, "En qué año el hombre pisó la Luna por primera vez",
            new String[] {"1979", "1969", "1966"}, 1));
    QUESTION_LIST.put(String.valueOf(2),
        new Pregunta(2, "Qué evento se considera que desencadenó la Primera Guerra Mundial",
            new String[] {"El asesinato del archiduque Francisco Fernando de Habsburgo",
                "El asesinato del archiduque Fernando Francisco de Habsburgo",
                "El intento de asesinato del archiduque Francisco Fernando de Habsburgo"},
            0));
    QUESTION_LIST.put(String.valueOf(3), new Pregunta(3, "Cuánto duró la Guerra de los Cien Años",
        new String[] {"100", "93", "116"}, 2));
    QUESTION_LIST.put(String.valueOf(4), new Pregunta(4,
        "Cómo se llama el filósofo español conocido por su desarrollo de la teoría del cierre categorial",
        new String[] {"Luis Aragonés", "Gustavo Bueno Martínez", "Miguel de Unamuno y Jugo"}, 1));
    QUESTION_LIST.put(String.valueOf(5), new Pregunta(5,
        "Qué filósofo de la Antigua Grecia creía que el elemento del que están compuestas todas las cosas es el agua",
        new String[] {"Tales de Mileto", "Platón", "Demócrito"}, 0));
    QUESTION_LIST.put(String.valueOf(6), new Pregunta(6,
        "Quién era el primer ministro británico cuando la India Británica fue sacudida por la hambruna de Bengala",
        new String[] {"Anthony Eden", "Boris Johnson", "Winston Churchill"}, 2));

    Document newDoc = new Document();

    Element rootNode = new Element("preguntas");
    newDoc.addContent(rootNode);

    for (Pregunta qData : QUESTION_LIST.values()) {
      Element pregunta = new Element("pregunta").setAttribute("id", String.valueOf(qData.getId()));

      Element preguntaEnunciado = new Element("enunciado").setText(qData.getQuestion());

      Element preguntaCorrecta =
          new Element("correct").setText(String.valueOf(qData.getCorrectAnswer()));

      Element respuestas = new Element("responses");

      Arrays.stream(qData.getResponseArr())
          .forEach(text -> respuestas.addContent(new Element("respuesta").setText(text)));;

      pregunta.addContent(preguntaEnunciado);
      pregunta.addContent(preguntaCorrecta);
      pregunta.addContent(respuestas);

      rootNode.addContent(pregunta);
    }



    if (!writeXMLFile(newDoc).hasRootElement()) {
      GameViews.printError("Error en el archivo de preguntas.");
    } else {
      GameViews.printSuccess("Se ha creado el archivo de preguntas.");
    }
  }

  private Document readXMLFile() {
    SAXBuilder builder = new SAXBuilder();
    Document doc;
    try {
      doc = builder.build(questionsFile);
    } catch (Exception e) {
      return new Document();
    }
    return doc;
  }

  private static Document writeXMLFile(Document doc) {
    Format format = Format.getPrettyFormat();
    XMLOutputter xmlOut = new XMLOutputter(format);
    String nuevoDoc = xmlOut.outputString(doc);

    try (PrintWriter writer = new PrintWriter(new FileWriter(questionsFile))) {
      writer.println(nuevoDoc);
    } catch (Exception e) {
      return new Document();
    }
    return doc;
  }

  public HashMap<Integer, Pregunta> getAllQuestions() {
    HashMap<Integer, Pregunta> response = new HashMap<Integer, Pregunta>();
    Document doc = readXMLFile();
    if (doc.hasRootElement()) {
      response = (HashMap<Integer, Pregunta>) doc.getRootElement().getChildren().stream()
          .map(PreguntaDAO::unSerialPregunta)
          .collect(Collectors.toMap(Pregunta::getId, Function.identity()));
    }
    return response;
  }

  public static Pregunta unSerialPregunta(Element node) {
    Pregunta response = new Pregunta();
    response.setId(Integer.parseInt(node.getAttributeValue("id")));
    response.setCorrectAnswer(Integer.parseInt(node.getChild("correct").getText()));
    response.setQuestion(node.getChild("enunciado").getText());
    response.setResponseArr(node.getChild("responses").getChildren().stream()
        .map(respuesta -> respuesta.getText()).toArray(String[]::new));
    return response;
  }

  // public Pregunta getQuestionById(int id) {
  // return QUESTION_LIST.get(id);
  // }
  //
  // public Pregunta createQuestion(Pregunta pregunta) {
  // Integer maxId = QUESTION_LIST.keySet().stream().max(Integer::compare).get();
  //
  // Pregunta qNew = new Pregunta();
  //
  // qNew.setId(maxId + 1);
  // qNew.setQuestion(pregunta.getQuestion());
  // qNew.setResponseArr(pregunta.getResponseArr().clone());
  // qNew.setCorrectAnswer(pregunta.getCorrectAnswer());
  //
  // QUESTION_LIST.put(maxId, qNew);
  //
  // return qNew;
  // }
  //
  public static void main(String[] args) {
    PreguntaDAO DAO = new PreguntaDAO();
    DAO.getAllQuestions();
  }
}
