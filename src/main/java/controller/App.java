package controller;

import model.domain.AllData;
import model.persistence.DataPersistence;
import model.persistence.HardCodedData;
import view.ConsoleUi;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.

   * @param args command line arguments.
   */
  public static void main(String[] args) {
    // get data from hard coded data
    DataPersistence persistence = new HardCodedData();
    AllData ad = persistence.load();
    ConsoleUi ui = new ConsoleUi();
    MenuController controller = new MenuController();
    
    //c.doSomethingSimple(m, v);

    controller.doMainMenu(ad, ui);

    persistence.save(ad);
  }
}
