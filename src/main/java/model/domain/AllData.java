package model.domain;

import java.util.ArrayList;

/**
 * all date include all members.
 */
public class AllData {
  private ArrayList<Member> members;

  public AllData() {
    members = new ArrayList<Member>();
  }

  /**
   * Access to iterate all members.
   *
   * @return the members.
   */
  public Iterable<Member> getMembers() {
    return members;
  }

  private boolean notUniqueMemberId(MemberId mid) {
    for (Member member : members) {
      if (member.getMemberId().equals(mid)) {
        return true;
      }
    }
    return false;
  }

  /**
   * add a member.

   * @param person a person.
   */
  public Member addMember(Person person) {
    Member m = new Member(person);
    MemberId memberId = m.getMemberId();

    while (notUniqueMemberId(memberId)) {
      m = new Member(person);
      memberId = m.getMemberId();
    }

    members.add(m);

    return m;
  }

  /**
   * delete a member.

   * @param member an Member type member.
   * @return true if delete this member, false if not find this member or id is null.
   */
  public Boolean deleteMember(Member member) {
    return members.remove(member);
  }
}