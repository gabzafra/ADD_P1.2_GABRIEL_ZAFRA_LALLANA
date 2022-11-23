package dam2.add.p12.models;

public class Answer {
  private Pregunta question;
  private int response;

  public Answer(Pregunta question, int response) {
    this.question = question;
    this.response = response;
  }

  public Pregunta getQuestion() {
    return question;
  }

  public int getResponse() {
    return response;
  }

}
