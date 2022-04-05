package view;

/**
 * error view for print an error.
 */
public class ErrorView {
  public void error(Exception e) {
    System.out.println("there is an error." + e);
  }

  public void error() {
    System.out.println("there is an error.");
  }
}
