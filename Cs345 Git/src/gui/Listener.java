package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {
private static Listener listener;
    private Listener() {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame frame = MainFrame.getInstance();
        
        Object source = e.getSource();
        
        String command = e.getActionCommand();

        /**
         * 
        switch (((AbstractButton) source).getText()) {
            case "=":
                System.out.println();
                break;
            case "/":
                System.out.println();

                break;
            case "*":
                break;
            case "-":
                break;
            case "+":
                break;
            case "C":
                break;
            case "R":
                break;
            default:
                System.exit(0);
        }
        */
        
        switch (command) {
          case "=":
             String input = ((JTextField) source).getText();
              System.out.println(input);
              break;
          case "/":
              System.out.println();
              break;
          case "*":
              break;
          case "-":
              break;
          case "+":
              break;
          case "C":
              break;
          case "R":
              break;
          default:
              System.exit(0);
      }
    }
    public static Listener getInstance(){
     if ( listener == null ) {
        listener = new Listener();
    }

        return listener;
}
}