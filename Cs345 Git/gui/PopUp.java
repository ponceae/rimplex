package gui;

import javax.swing.JOptionPane;

/**
 * PopUp window.
 */
public class PopUp
{

  /**
   * Popup window normal.
   * 
   * @param infoMessage the information message
   * @param titleBar the title bar
   */
  public static void infoBox(final String infoMessage, final String titleBar)
  {
    JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Error popup.
   * 
   * @param infoMessage the information message
   */
  public static void errorBox(final String infoMessage)
  {
    JOptionPane.showMessageDialog(null, infoMessage, "ERROR", JOptionPane.ERROR_MESSAGE);
  }
}
