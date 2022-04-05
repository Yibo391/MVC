package model.persistence;

import model.domain.AllData;
import model.domain.Boat.BoatType;
import model.domain.Member;
import model.domain.MemberId;
import model.domain.Name;
import model.domain.PersonalNumber;

/**
 * hard coded data, will be change and replace by database after.
 */
public class HardCodedData implements DataPersistence {

  /**
   * create a hard coded data.
   */
  @Override
  public AllData load() {
    AllData members = new AllData();

    Member m = members.addMember(new Member(new Name("member a"), 
        new PersonalNumber("00001122-QWER"), new MemberId("0as8Ud")));
    m.addBoat(BoatType.Sailboat, 10.5);
    m.addBoat(BoatType.Motorsailer, 5);

    m = members.addMember(new Member(new Name("member b"), 
        new PersonalNumber("00001122-ASDF"), new MemberId("6e6PFK")));
    m.addBoat(BoatType.KayakOrCanoe, 11.5);

    m = members.addMember(new Member(new Name("member c"), 
        new PersonalNumber("00001122-ZXCV"), new MemberId("2olu9O")));
    m.addBoat(BoatType.Sailboat, 9.5);
    m.addBoat(BoatType.Other, 8);
    m.addBoat(BoatType.Motorsailer, 6);

    m = members.addMember(new Member(new Name("member d"), 
        new PersonalNumber("00001122-QAZW"), new MemberId("oofAZ6")));
    m.addBoat(BoatType.Other, 12.5);
    m.addBoat(BoatType.KayakOrCanoe, 7);

    return members;
  }

  @Override
  public void save(AllData reg) {
    System.err.println("No saving implemented");
  }
}
