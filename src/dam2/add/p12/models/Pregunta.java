package dam2.add.p12.models;

public class Pregunta {
  private int id;
  private String theme;
  private String question;
  private String[] responseArr;

  public Pregunta(int id, String theme, String question, String[] responseArr) {
    this.id = id;
    this.theme = theme;
    this.question = question;
    this.responseArr = responseArr;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTheme() {
    return theme;
  }

  public void setTheme(String theme) {
    this.theme = theme;
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
}
