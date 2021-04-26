package gui;

import java.awt.Component;

import javax.swing.JOptionPane;
/**
 * PopUp
 * 
 */
public class PopUp {
  
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void errorBox(String infoMessage, String titleBar)
    {
      JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.ERROR_MESSAGE);
    }
}
