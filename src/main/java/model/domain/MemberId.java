package model.domain;

import java.util.Random;

/**
 * Represents a random member id which have 6 char.
 */
public class MemberId {
  String id;

  MemberId() {
    id = randomId();
  }

  public MemberId(String id) {
    this.id = id;
  }

  /**
   * get a random id, 6 character.

   * @return 6 character random string.
   */
  public String randomId() {
    //generate 6 chacacters
    Random random = new Random();
    StringBuffer a = new StringBuffer();
    int times = 6;
    while (times != 0) {
      int randNum = random.nextInt(3) + 1;
      switch (randNum) {
        case 1:
          randNum = random.nextInt(10) + 48;
          a.append(Character.toChars(randNum));
          break;
        case 2:
          randNum = random.nextInt(26) + 65;
          a.append(Character.toChars(randNum));
          break;
        case 3:
          randNum = random.nextInt(26) + 97;
          a.append(Character.toChars(randNum));
          break;
        default:
          break;
      }
      times--;
    }
    return a.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof MemberId) {
      return ((MemberId) o).id.equals(id);
    }
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  /**
   * Id as a string.
   *
   * @return the id as a string.
   */
  @Override
  public String toString() { 
    return id;
  }
}
