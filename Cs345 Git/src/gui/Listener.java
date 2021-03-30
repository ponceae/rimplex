package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener
{
  private static Listener listener;

  private Listener()
  {

  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    MainFrame frame = MainFrame.getInstance();
    JTextPane theDis = MainPanel.display;
    
    String testAnswer = "The Answer";
    String previousTotal = "";
    
    // Inputs
    String eqInput;
    String tInput;
    String divInput;
    String multInput;
    String subInput;
    String addInput;
    
    // Outputs
    JTextArea output; // eqOutput
    // JTextArea output; // tOutput
    // JTextArea output; // divOutput
    // JTextArea output; // multiOutput
    // JTextArea output; // subOutput
    // JTextArea output; // addOutput

    Object source = e.getSource();

    String command = e.getActionCommand();

    switch (command)
    {
      case "=":
        eqInput = MainPanel.getInput().getText();
        previousTotal.concat(eqInput);
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue class
        if (!eqInput.equals(""))
        {
          output = MainPanel.getDisplayOutput("(" + eqInput + ") = \n(" + previousTotal + ")\n\n");
        } else
        {
          output = MainPanel.getDisplayOutput("(0) = \n" + previousTotal + ")\n\n");
        }
        theDis.insertComponent(output);
        MainPanel.getInput().setText("");
        break;
      case "text":
        tInput = ((JTextField) source).getText();
        previousTotal.concat(tInput);
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue class
        if (!tInput.equals(""))
        {
          output = MainPanel.getDisplayOutput("(" + tInput + ") = \n(" + previousTotal + ")\n\n");
        } else
        {
          output = MainPanel.getDisplayOutput("(0) = \n(" + previousTotal + ")\n\n");
        }
        theDis.insertComponent(output);
        ((JTextField)source).setText("");
        break;
      case "/":
        divInput = MainPanel.getInput().getText();
        previousTotal.concat(divInput);
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue class
        output = MainPanel.getDisplayOutput("(" + divInput + ") / ");
        theDis.insertComponent(output);
        MainPanel.getInput().setText("");
        break;
      case "*":
        multInput = MainPanel.getInput().getText();
        previousTotal.concat(multInput);
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue class
        output = MainPanel.getDisplayOutput("(" + multInput + ") * ");
        theDis.insertComponent(output);
        MainPanel.getInput().setText("");
        break;
      case "-":
        subInput = MainPanel.getInput().getText();
        previousTotal.concat(subInput);
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue class
        output = MainPanel.getDisplayOutput("(" + subInput + ") - ");
        theDis.insertComponent(output);
        MainPanel.getInput().setText("");
        break;
      case "+":
        addInput = MainPanel.getInput().getText();
        previousTotal.concat(addInput);
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue class
        output = MainPanel.getDisplayOutput("(" + addInput + ") + ");
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

  public static Listener getInstance()
  {
    if (listener == null)
    {
      listener = new Listener();
    }

    return listener;
  }
}
