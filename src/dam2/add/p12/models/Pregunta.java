package dam2.add.p12.models;

public class Pregunta {
  private int id;
  private String question;
  private String[] responseArr;
  private int correctAnswer;

  public Pregunta() {
    this(0, null, null, 0);
  }

  public Pregunta(int id, String question, String[] responseArr, int correctAnswer) {
    this.id = id;
    this.question = question;
    this.responseArr = responseArr;
    this.correctAnswer = correctAnswer;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String[] getResponseArr() {
    return responseArr;
  }

  public void setResponseArr(String[] responseArr) {
    this.responseArr = responseArr;
  }

  public int getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(int correctAnswer) {
    this.correctAnswer = correctAnswer;
  }


}
