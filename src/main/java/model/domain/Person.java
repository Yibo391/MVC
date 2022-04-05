package model.domain;

/**
 * Represents the information of a person.
 */
public class Person {
  private Name name;
  private PersonalNumber personalNumber;
  
  public Person(Name name, PersonalNumber personalNumber) {
    this.name = name;
    this.personalNumber = personalNumber;
  }

  public Name getName() {
    return name;
  }

  public PersonalNumber getPersonalNumber() {
    return personalNumber;
  }

  protected void setName(Name changedInfo) {
    name = changedInfo;
  }

  protected void setPersonalNumber(PersonalNumber changedInfo) {
    personalNumber = changedInfo;
  }

  /**
   * Change a person data.

   * @param changedInfo info need to change.
   */
  public void changePerson(Person changedInfo) {
    this.setPersonalNumber(changedInfo.getPersonalNumber());
    this.setName(changedInfo.getName());
  }
}