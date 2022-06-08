package API.georef;

public class Distance {

  public int value;
  public String unit;

  public Distance(int value, String unit) {
    this.value = value;
    this.unit = unit;
  }

  public int getValue() {
    return value;
  }

  public String getUnit() {
    return unit;
  }
}
