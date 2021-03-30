package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener - Class which implements ActionListener to listen for JButtons pushes and JTextField
 * entries.
 * 
 * @author Ulises Fernandez and Andrew Elbert
 * @version (3/31/21)
 */

public class Listener implements ActionListener
{
  private static Listener listener;

  private Listener()
  {
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    JTextPane theDis = MainPanel.display;

    String previousTotal = "";

    // Inputs
    String input; // eqInput
    /**
    String input; // tInput
    String input; // divInput
    String input; // multInput
    String input; // subInput
    String input; // addInput
    */

    // Outputs
    JTextArea output;

    Object source = e.getSource();

    String command = e.getActionCommand();

    switch (command)
    {
      case "=":
        input = MainPanel.getInput().getText();
        previousTotal.concat(input);
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue
        // class
        if (!input.equals(""))
        {
          output = MainPanel.getDisplayOutput("(" + input + ") = \n(" + previousTotal + ")\n\n");
        }
        else
        {
          output = MainPanel.getDisplayOutput("(0) = \n" + previousTotal + ")\n\n");
        }
        theDis.insertComponent(output);
        MainPanel.getInput().setText("");
        break;
      case "text":
        input = ((JTextField) source).getText();
        previousTotal.concat(input);
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue
        // class
        if (!input.equals(""))
        {
          output = MainPanel.getDisplayOutput("(" + input + ") = \n(" + previousTotal + ")\n\n");
        }
        else
        {
          output = MainPanel.getDisplayOutput("(0) = \n(" + previousTotal + ")\n\n");
        }
        theDis.insertComponent(output);
        ((JTextField) source).setText("");
        break;
      case "/":
        input = MainPanel.getInput().getText();
        previousTotal.concat(input);
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue
        // class
        output = MainPanel.getDisplayOutput("(" + input + ") / ");
        theDis.insertComponent(output);
        MainPanel.getInput().setText("");
        break;
      case "*":
        input = MainPanel.getInput().getText();
        previousTotal.concat(input);
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue
        // class
        output = MainPanel.getDisplayOutput("(" + input + ") * ");
        theDis.insertComponent(output);
        MainPanel.getInput().setText("");
        break;
      case "-":
        input = MainPanel.getInput().getText();
        previousTotal.concat(input);
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue
        // class
        output = MainPanel.getDisplayOutput("(" + input + ") - ");
        theDis.insertComponent(output);
        MainPanel.getInput().setText("");
        break;
      case "+":
        input = MainPanel.getInput().getText();
        previousTotal.concat(input);
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue
        // class
        output = MainPanel.getDisplayOutput("(" + input + ") + ");
        theDis.insertComponent(output);
        MainPanel.getInput().setText("");
        break;
      case "C":
        MainPanel.clear();
        break;
      case "R":
        MainPanel.reset();
        previousTotal = "";
        break;
      default:
        System.exit(0);
    }
  }

  /**
   * Creates a new Listener if not already done and returns this.
   * 
   * @return this, the Listener
   */
  public static Listener getInstance()
  {
    if (listener == null)
    {
      listener = new Listener();
    }

    return listener;
  }
}
