package controller;

import model.domain.AllData;
import model.domain.Boat;
import model.domain.Member;
import model.domain.Person;
import view.ConsoleUi;
import view.ConsoleUi.MainMenuAction;

/**
 * Represents the full system controller.
 */
public class MenuController {

  boolean keepRun;

  /**
   * Call to run the program from the main menu.
   *
   * @param ad The registry to manipulate.
   * @param ui The ui to use for presentation and input.
   */
  public void doMainMenu(AllData ad, ConsoleUi ui) {

    Member selectedMember = null;
    keepRun = true;

    while (keepRun) {
      if (selectedMember == null) {
        selectedMember = doMainMenuImpl(ad, ui);
      } else {
        selectedMember = doMemberMenu(ad, selectedMember, ui);
      }
      
    }
  }

  private Member doMainMenuImpl(AllData ad, ConsoleUi ui) {
    MainMenuAction action = ui.presentMainMenu();
    switch (action) {
      case CompactList:
        return listAllMembers(ad.getMembers(), ui, true);
      case VerboseList:
        return listAllMembers(ad.getMembers(), ui, false);
      case AddNewMember:
        return addNewMember(ad, ui);
      case Quit:
        keepRun = false;
        return null;
      default:
        return null;
    }
  }

  private Member doMemberMenu(AllData ad, Member selectedMember, ConsoleUi ui) {
    Boat selectedBoat = null;

    do {
      if (selectedBoat != null) {
        selectedBoat = doBoatMenu(selectedMember, selectedBoat, ui);
      } else {
        ConsoleUi.MemberMenuAction action = ui.presentMemberMenu(selectedMember);
        switch (action) {
          case Delete:
            ad.deleteMember(selectedMember);
            selectedMember = null;
            break;
          case View:
            selectedBoat = ui.presentMember(selectedMember);
            break;
          case AddBoat:
            selectedBoat = addBoat(selectedMember, ui);
            break;
          case Change:
            Person changedInfo = ui.presentNewMemberForm(selectedMember);
            selectedMember.changePerson(changedInfo);
            break;
          case Back:
            selectedMember = null;
            break;
          default:
        }
      }
    } while (selectedMember != null || selectedBoat != null);
    return null;
  }


  private Boat doBoatMenu(Member selectedMember, Boat selectedBoat, ConsoleUi ui) {
    ConsoleUi.BoatMenuAction action = ui.presentBoatMenu(selectedMember, selectedBoat);
    switch (action) {
      case Delete:
        selectedMember.deleteBoat(selectedBoat);
        return null;
      case Change:
        Boat changeInfo = ui.presentAddBoatForm(selectedBoat);
        selectedBoat.changeBoat(changeInfo);
        return selectedBoat;
      case Back:
        return null;
      default:
    }

    return null;
  }

  private Boat addBoat(Member selectedMember, ConsoleUi ui) {
    Boat b = ui.presentAddBoatForm(null);
    if (b != null) {
      return selectedMember.addBoat(b.getType(), b.getLength());
    }

    return null;
  }

  private Member addNewMember(AllData ad, ConsoleUi ui) {
    Person personToAdd = ui.presentNewMemberForm(null);

    if (personToAdd != null) {
      return ad.addMember(personToAdd);
    } else {
      // some error happened
    }
    return null;
  }

  private Member listAllMembers(Iterable<Member> members, ConsoleUi ui, boolean isCompact) {
    if (isCompact) {
      return ui.printCompactMemberList(members);
    } else {
      return ui.printVerboseMemberList(members);
    }
    
  }
}
