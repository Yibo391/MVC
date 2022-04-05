package model.domain;

/**
 * A Swedish personal number of a person.
 */
public class PersonalNumber {

  private String personalNumber;

  public PersonalNumber(String personalNumber) {
    this.personalNumber = personalNumber;
  }

  public String getString() {
    return personalNumber;
  }
}
