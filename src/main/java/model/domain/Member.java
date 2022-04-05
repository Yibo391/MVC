package model.domain;

import java.util.ArrayList;
import model.domain.Boat.BoatType;

/**
 * member type.
 */
public class Member extends Person {
  private MemberId memberId;
  private ArrayList<Boat> boats;

  /**
   * constructer of member, use to create a new member.

   * @param name name of member.
   * @param personalNumber personal number of member.
   */
  public Member(Name name, PersonalNumber personalNumber) {
    super(name, personalNumber);
    this.memberId = new MemberId();
    this.boats = new ArrayList<Boat>();
  }

  /**
   * constructer of member, use to load, delete and chnage.

   * @param name name of member.
   * @param personalNumber personal number of member.
   * @param memberId member id of member.
   */
  public Member(Name name, PersonalNumber personalNumber, MemberId memberId) {
    super(name, personalNumber);
    this.memberId = memberId;
    this.boats = new ArrayList<Boat>();
  }

  /**
   * Use person to create a member.

   * @param person a person have name and personal number.
   */
  Member(Person person) {
    super(person.getName(), person.getPersonalNumber());
    this.memberId = new MemberId();
    this.boats = new ArrayList<Boat>();
  }

  public MemberId getMemberId() {
    return this.memberId;
  }

  public Iterable<Boat> getBoats() {
    return this.boats;
  }

  public int getBoatSize() {
    return this.boats.size();
  }

  public int getBoatCount() {
    return boats.size();
  }

  /**
   * Add a boat.

   * @param type boat type.
   * @param length boat length.
   * @return a boat.
   */
  public Boat addBoat(BoatType type, Double length) {
    Boat b = new Boat(type, length);
    this.boats.add(b);
    return b;
  }

  /**
   * Add a boat.

   * @param type boat type.
   * @param length boat length.
   * @return a boat.
   */
  public Boat addBoat(BoatType type, Integer length) {
    Boat b = new Boat(type, length);
    this.boats.add(b);
    return b;
  }

  /**
   * delete a boat.

   * @param b boat.
   * @return true if boat is deleted, false if not. 
   */
  public Boolean deleteBoat(Boat b) {
    return this.boats.remove(b);
  }
}
