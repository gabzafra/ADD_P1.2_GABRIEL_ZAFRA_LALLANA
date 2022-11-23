package dam2.add.p12.models;

public class Jugador {
  private String id;
  private Integer record;

  public Jugador() {
    this("", 0);
  }

  public Jugador(String id, int record) {
    this.id = id;
    this.record = record;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getRecord() {
    return record;
  }

  public void setRecord(int record) {
    this.record = record;
  }

}
