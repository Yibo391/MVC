package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import model.domain.Boat;
import model.domain.Boat.BoatType;
import model.domain.Member;
import model.domain.MemberId;
import model.domain.Name;
import model.domain.Person;
import model.domain.PersonalNumber;

/**
 * Represents a console terminal based user interface.
 */
public class ConsoleUi {
  ErrorView errorView = new ErrorView();

  private String readKeyboard() {
    try {
      BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in, "Cp850"));

      try {
        return keyboard.readLine();
      } catch (IOException e) {
        errorView.error(e);
      }

    } catch (UnsupportedEncodingException e) {
      errorView.error(e);
    }

    return "";
  }

  private String stringId(MemberId id) {
    return id.toString();
  }

  /**
   * Represents the actions in the main menu.
   */
  public enum MainMenuAction {
    AddNewMember,
    VerboseList,
    CompactList,
    Quit,
    None
  }

  private String boatTypeToString(BoatType t) {
    switch (t) {
      case Motorsailer:
        return "motor boat";
      case Sailboat:
        return "sail boat";
      case KayakOrCanoe:
        return "canoe or kayak";
      case Other:
        return "some thing else";
      default:
        return "unknown boat type " + t;
    }
  }

  /**
   * Presents the application main menu.
   *
   * @return The otpion the user chooses.
   */
  public MainMenuAction presentMainMenu() {
    System.out.println("Main menu");
    System.out.println("1. to Add a new member.");
    System.out.println("2. to List all members (Verbose).");
    System.out.println("3. to list all members (Compact)");

    System.out.println();
    System.out.println("q. to quit.");

    String kbd = readKeyboard();
    if (kbd.equals("1")) {
      return MainMenuAction.AddNewMember;
    } else if (kbd.equals("2")) {
      return MainMenuAction.VerboseList;
    } else if (kbd.equals("3")) {
      return MainMenuAction.CompactList;
    } else if (kbd.equals("q")) {
      return MainMenuAction.Quit;
    }

    return MainMenuAction.None;
  }

  /**
   * Presents the interface for creating a new member.
   *
   * @return A person with all information set.
   */
  public model.domain.Person presentNewMemberForm(Person defaultInfo) {
    if (defaultInfo == null) {
      System.out.println("Add new member");
      System.out.println("Something empty cancels creation.");
      System.out.println("Enter member name:");
      String name = readKeyboard();
      if (name.length() == 0) {
        return null;
      }

      System.out.println("Enter member personal number:");
      String pnr = readKeyboard();
      if (pnr.length() == 0) {
        return null;
      }
      return new Person(new Name(name), new PersonalNumber(pnr));
    } else {
      Name n;
      PersonalNumber p;
      
      System.out.println("Change member");
      System.out.println("Empty input keeps the old value");
      System.out.println("Enter member name (" + defaultInfo.getName().toString() + "):");
      String name = readKeyboard();
      if (name.length() == 0) {
        n = defaultInfo.getName();
      } else {
        n = new Name(name);
      }

      System.out.println("Enter member personal number (" + defaultInfo.getPersonalNumber().getString() + ") :");
      String pnr = readKeyboard();
      if (pnr.length() == 0) {
        p = defaultInfo.getPersonalNumber();
      }  else {
        p = new PersonalNumber(pnr);
      }
      return new Person(n, p);
    }
    
  }

  /**
   * Prints a list of members with all information.
   *
   * @param members The members to print.
   */
  public Member printVerboseMemberList(Iterable<Member> members) {
    System.out.println("The verbose list of members:");

    int ix = 1;
    for (Member member : members) {
      System.out.println("\n" +  ix + ".\tName: " + member.getName() + "\tmember id:" + stringId(member.getMemberId()));
      for (Boat b : member.getBoats()) {
        System.out.println("\t\tBoat: " + boatTypeToString(b.getType()) + " - length: " + b.getLength() +  " feet");
      }
      ix++;
    }

    return getMemberByIndex(members);
  }

  /**
   * Prints a list of members with compact information.
   *
   * @param members The members to print.
   */
  public Member printCompactMemberList(Iterable<Member> members) {
    System.out.println("The compact list of members:");

    int ix = 1;
    for (Member member : members) {
      System.out.println("" +  ix + ".\tName: " + member.getName() + "\tmember id:" 
          + stringId(member.getMemberId()) + " boats: " + member.getBoatCount());
      ix++;
    }

    return getMemberByIndex(members);
  }

  private Member getMemberByIndex(Iterable<Member> members) {

    System.out.println("Enter the number of the memmber to select");
    String number = readKeyboard();
    int readIx = 0;
    try {
      readIx = Integer.parseInt(number);
    } catch (java.lang.NumberFormatException e) {
      errorView.error(e);
    }

    int ix = 1;
    for (Member member : members) {
      if (ix == readIx) {
        return member;
      }
      ix++;
    }

    return null;
  }

  /**
   * Represents the actions of the member menu.
   */
  public enum MemberMenuAction {
    Delete,
    AddBoat,
    View,
    Change,
    Back,
    None
  }

  /**
   * Shows a member specific menu.
   *
   * @param m the member to show the menu for.
   * @return the action the user has selected to perfom on the member or back.
   */
  public MemberMenuAction presentMemberMenu(Member m) {
    System.out.println("Member menu menu: " + m.getName() +  " (" + stringId(m.getMemberId()) + ")");
    System.out.println("1. to View the Member details.");
    System.out.println("2. to add a boat.");
    System.out.println("3. to change info.");
    System.out.println("4. to delete the member.");

    System.out.println();
    System.out.println("b. to go back.");

    String kbd = readKeyboard();
    if (kbd.equals("1")) {
      return MemberMenuAction.View;
    } else if (kbd.equals("2")) {
      return MemberMenuAction.AddBoat;
    } else if (kbd.equals("3")) {
      return MemberMenuAction.Change;
    } else if (kbd.equals("4")) {
      return MemberMenuAction.Delete;
    } else if (kbd.equals("b")) {
      return MemberMenuAction.Back;
    }

    return MemberMenuAction.None;
  }


  /**
   * Shows detailed information about a particular member.
   *
   * @param selectedMember the member to show info about.
   */
  public Boat presentMember(Member selectedMember) {
    System.out.println("Name:\t\t" + selectedMember.getName());
    System.out.println("Member id:\t\t" + stringId(selectedMember.getMemberId()));
    System.out.println("Personal Number:\t" + selectedMember.getPersonalNumber().getString());
    int ix = 1;
    for (Boat b : selectedMember.getBoats()) {
      System.out.println("\t" + ix + ". Boat: " + boatTypeToString(b.getType())
            + " - length: " + b.getLength() +  " feet");
      ix++;
    }

    System.out.println("Please enter a number to select a boat");
    String input = readKeyboard();
    try {
      int intInput = Integer.parseInt(input);
      ix = 1;
      for (Boat b : selectedMember.getBoats()) {
        if (ix == intInput) {
          return b;
        }
        ix++;
      }

    } catch (NumberFormatException e) {
      errorView.error(e);
      return null;
    } 
    return null;
  }

  /**
   * Represents the actions of the boat menu.
   */
  public enum BoatMenuAction {
    Delete,
    Change,
    Back,
    None
  }

  /**
   * Presents the boat menu and prompts for a boat action.

   * @param selectedMember The owner of the boat.
   * @return The selected boat action.
   */
  public BoatMenuAction presentBoatMenu(Member selectedMember, Boat b) {
    System.out.println("Boat: " + boatTypeToString(b.getType()) + " - length: " + b.getLength() +  " feet");
    System.out.println("Boat menu");
    System.out.println("1. to Change boat information.");
    System.out.println("2. to Delete the boat.");

    System.out.println();
    System.out.println("b. to go back.");

    String kbd = readKeyboard();
    if (kbd.equals("1")) {
      return BoatMenuAction.Change;
    } else if (kbd.equals("2")) {
      return BoatMenuAction.Delete;
    } else if (kbd.equals("b")) {
      return BoatMenuAction.Back;
    }

    return BoatMenuAction.None;
  }

  /**
   * Used to create a new boat with a type and a length as specified by the user.
   *
   * @return Boat object or null if user cancelled.
   */
  public Boat presentAddBoatForm(Boat oldInfo) {
    if (oldInfo == null) {
      System.out.println("Add a new boat.");
      BoatType t = promptForBoatType();
      if (t != null) {
        Double length = promptForBoatLenght();

        if (length > 0) {
          return new Boat(t, length);
        }
      }
      return null;
    } else {
      BoatType type;
      
      System.out.println("Change Boat");
      System.out.println("Empty input keeps the old value");
      System.out.println("Select boat type");
      System.out.println("1. " + boatTypeToString(BoatType.Sailboat));
      System.out.println("2. " + boatTypeToString(BoatType.Motorsailer));
      System.out.println("3. " + boatTypeToString(BoatType.KayakOrCanoe));
      System.out.println("4. " + boatTypeToString(BoatType.Other));
      System.out.println("Choose boat type (" + boatTypeToString(oldInfo.getType()) + "):");
      String input = readKeyboard();
      if (input.equals("1")) {
        type = BoatType.Sailboat;
      } else if (input.equals("2")) {
        type = BoatType.Motorsailer;
      } else if (input.equals("3")) {
        type = BoatType.KayakOrCanoe;
      } else if (input.equals("4")) {
        type = BoatType.Other;
      } else {
        type = oldInfo.getType();
      }

      Double length;

      System.out.println("Enter member personal number (" + oldInfo.getLength().toString() + ") :");
      input = readKeyboard();
      if (input.length() != 0) {
        try {
          length = Double.parseDouble(input);
        } catch (NumberFormatException e) {
          errorView.error(e);
          length = oldInfo.getLength();
        }
      } else {
        length = oldInfo.getLength();
      }
      return new Boat(type, length);
    }
  }

  private BoatType promptForBoatType() {
    System.out.println("Select boat type");
    System.out.println("1. " + boatTypeToString(BoatType.Sailboat));
    System.out.println("2. " + boatTypeToString(BoatType.Motorsailer));
    System.out.println("3. " + boatTypeToString(BoatType.KayakOrCanoe));
    System.out.println("4. " + boatTypeToString(BoatType.Other));
    System.out.println("");
    System.out.println("b Back");
    
    String input = readKeyboard();
    if (input.equals("1")) {
      return BoatType.Sailboat;
    } else if (input.equals("2")) {
      return BoatType.Motorsailer;
    } else if (input.equals("3")) {
      return BoatType.KayakOrCanoe;
    } else if (input.equals("4")) {
      return BoatType.Other;
    }

    return null;
  }

  private Double promptForBoatLenght() {
    System.out.print("Length of boat in feet: ");
    String input = readKeyboard();

    try {
      return Double.parseDouble(input);
    } catch (NumberFormatException e) {
      errorView.error(e);
      return 0.0;
    }
  }
}
