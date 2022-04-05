package model.domain;

/**
 * Boat type.
 */
public class Boat {
  /**
   * boat type enum.
   */
  public enum BoatType {
    Sailboat, Motorsailer, KayakOrCanoe, Other
  }

  private BoatType type;
  private Double length;

  public Boat(BoatType type, Double length) {
    this.type = type;
    this.length = length;
  }

  public Boat(BoatType type, Integer length) {
    this.type = type;
    this.length = length.doubleValue();
  }

  public BoatType getType() {
    return this.type;
  }

  public void setType(BoatType type) {
    this.type = type;
  }

  public Double getLength() {
    return this.length;
  }

  public void setLength(Double length) {
    this.length = length;
  }

  /**
   * change a boat data.

   * @param changeInfo Info need to change.
   */
  public void changeBoat(Boat changeInfo) {
    this.setType(changeInfo.getType());
    this.setLength(changeInfo.getLength());
  }
}
