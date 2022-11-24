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

  private static final File QUESTIONS_FILE = new File("ficheros" + File.separator + "preguntas.xml");

  public PreguntaDAO() {
    if (getAllQuestions().isEmpty()) {
      initDAO();
    }
  }

  private void initDAO() {
    createQuestion(new Pregunta(0, "En qué año el hombre pisó la Luna por primera vez",
        new String[] {"1979", "1969", "1966"}, 1));
    createQuestion(
        new Pregunta(0, "Qué evento se considera que desencadenó la Primera Guerra Mundial",
            new String[] {"El asesinato del archiduque Francisco Fernando de Habsburgo",
                "El asesinato del archiduque Fernando Francisco de Habsburgo",
                "El intento de asesinato del archiduque Francisco Fernando de Habsburgo"},
            0));
    createQuestion(new Pregunta(0, "Cuánto duró la Guerra de los Cien Años",
        new String[] {"100", "93", "116"}, 2));
    createQuestion(new Pregunta(0,
        "Cómo se llama el filósofo español conocido por su desarrollo de la teoría del cierre categorial",
        new String[] {"Luis Aragonés", "Gustavo Bueno Martínez", "Miguel de Unamuno y Jugo"}, 1));
    createQuestion(new Pregunta(0,
        "Qué filósofo de la Antigua Grecia creía que el elemento del que están compuestas todas las cosas es el agua",
        new String[] {"Tales de Mileto", "Platón", "Demócrito"}, 0));
    createQuestion(new Pregunta(0,
        "Quién era el primer ministro británico cuando la India Británica fue sacudida por la hambruna de Bengala",
        new String[] {"Anthony Eden", "Boris Johnson", "Winston Churchill"}, 2));
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

  public Pregunta getQuestionById(int id) {
    HashMap<Integer, Pregunta> listaPreguntas = getAllQuestions();
    if (listaPreguntas.containsKey(id)) {
      return listaPreguntas.get(id);
    } else {
      return new Pregunta();
    }
  }


  public Pregunta createQuestion(Pregunta pregunta) {
    HashMap<Integer, Pregunta> listaPreguntas = getAllQuestions();

    Integer newId;
    if (!listaPreguntas.isEmpty()) {
      newId = listaPreguntas.keySet().stream().max(Integer::compare).get() + 1;
    } else {
      newId = 1;
    }


    Pregunta qNew = new Pregunta();

    qNew.setId(newId);
    qNew.setQuestion(pregunta.getQuestion());
    qNew.setResponseArr(pregunta.getResponseArr().clone());
    qNew.setCorrectAnswer(pregunta.getCorrectAnswer());

    listaPreguntas.put(newId, qNew);

    Document newDoc = serializeQuestionMap(listaPreguntas);

    if (!writeXMLFile(newDoc).hasRootElement()) {
      GameViews.printError("Error en el archivo de preguntas.");
      return new Pregunta();
    } else {
      return getQuestionById(qNew.getId());
    }
  }


  private Document readXMLFile() {
    SAXBuilder builder = new SAXBuilder();
    Document doc;
    try {
      doc = builder.build(QUESTIONS_FILE);
    } catch (Exception e) {
      return new Document();
    }
    return doc;
  }

  private static Document writeXMLFile(Document doc) {
    Format format = Format.getPrettyFormat();
    XMLOutputter xmlOut = new XMLOutputter(format);
    String nuevoDoc = xmlOut.outputString(doc);

    try (PrintWriter writer = new PrintWriter(new FileWriter(QUESTIONS_FILE))) {
      writer.println(nuevoDoc);
    } catch (Exception e) {
      return new Document();
    }
    return doc;
  }


  private static Pregunta unSerialPregunta(Element node) {
    Pregunta response = new Pregunta();
    response.setId(Integer.parseInt(node.getAttributeValue("id")));
    response.setCorrectAnswer(Integer.parseInt(node.getChild("correct").getText()));
    response.setQuestion(node.getChild("enunciado").getText());
    response.setResponseArr(node.getChild("responses").getChildren().stream()
        .map(respuesta -> respuesta.getText()).toArray(String[]::new));
    return response;
  }

  private static Document serializeQuestionMap(HashMap<Integer, Pregunta> listaPreguntas) {
    Document newDoc = new Document();

    Element rootNode = new Element("preguntas");
    newDoc.addContent(rootNode);

    for (Pregunta qData : listaPreguntas.values()) {
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

    return newDoc;
  }
}
