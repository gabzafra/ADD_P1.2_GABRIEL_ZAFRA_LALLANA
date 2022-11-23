package dam2.add.p12.models;

import java.util.HashMap;

public class PreguntaDAO {
  // ------------- Mock DAO
  private static final HashMap<Integer, Pregunta> QUESTION_LIST = new HashMap<Integer, Pregunta>();

  public PreguntaDAO() {
    initDAO();
  }

  private void initDAO() {
    QUESTION_LIST.put(1, new Pregunta(1, "En qué año el hombre pisó la Luna por primera vez",
        new String[] {"1979", "1969", "1966"}, 1));
    QUESTION_LIST.put(2,
        new Pregunta(2, "Qué evento se considera que desencadenó la Primera Guerra Mundial",
            new String[] {"El asesinato del archiduque Francisco Fernando de Habsburgo",
                "El asesinato del archiduque Fernando Francisco de Habsburgo",
                "El intento de asesinato del archiduque Francisco Fernando de Habsburgo"},
            0));
    QUESTION_LIST.put(3, new Pregunta(3, "Cuánto duró la Guerra de los Cien Años",
        new String[] {"100", "93", "116"}, 2));
    QUESTION_LIST.put(4, new Pregunta(4,
        "Cómo se llama el filósofo español conocido por su desarrollo de la teoría del cierre categorial",
        new String[] {"Luis Aragones", "Gustavo Bueno Martínez", "Miguel de Unamuno y Jugo"}, 1));
    QUESTION_LIST.put(5, new Pregunta(5,
        "Qué filósofo de la Antigua Grecia creía que el elemento del que están compuestas todas las cosas es el agua",
        new String[] {"Tales de Mileto", "Platón", "Demócrito"}, 0));
    QUESTION_LIST.put(6, new Pregunta(6,
        "Quién era el primer ministro británico cuando la India Británica fue sacudida por la hambruna de Bengala",
        new String[] {"Anthony Eden", "Boris Johnson", "Winston Churchill"}, 2));
  }

  public HashMap<Integer, Pregunta> getAllQuestions() {
    return QUESTION_LIST;
  }

  public Pregunta getQuestionById(int id) {
    return QUESTION_LIST.get(id);
  }

  public Pregunta createQuestion(Pregunta pregunta) {
    Integer maxId = QUESTION_LIST.keySet().stream().max(Integer::compare).get();

    Pregunta qNew = new Pregunta();

    qNew.setId(maxId + 1);
    qNew.setQuestion(pregunta.getQuestion());
    qNew.setResponseArr(pregunta.getResponseArr().clone());
    qNew.setCorrectAnswer(pregunta.getCorrectAnswer());

    QUESTION_LIST.put(maxId, qNew);

    return qNew;
  }
}
