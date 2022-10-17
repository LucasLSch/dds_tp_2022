package ddsutn.domain.measurements.unit;

public enum Proportionality {

  DIRECT(1),
  INVERSE(-1);

  private final Integer factor;

  private Proportionality(Integer factor) {
    this.factor = factor;
  }

  public Integer getFactor() {
    return factor;
  }
}
