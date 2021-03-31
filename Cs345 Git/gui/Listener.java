package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Calculator.*;
import java.util.ArrayList;
import math.Operator;
import MathComp.*;

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
  private ImgNumber result = null;
  private String previousPress = "n";

  /**
   * Default constructor.
   */
  private Listener()
  {

  }

  /**
   * Performs an action.
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    JTextPane theDis = MainPanel.display;
    String command = e.getActionCommand();

    // Input
    String input = MainPanel.getInput().getText();
    // Output
    JTextArea output;

    // A String representation of the total expression to be parsed
    // String total = "";

    switch (command)
    {
      case "=":
        // total.concat(theDis.getText());
        result = calculateBasedOnPreviousButton(parseSingleValue(input));
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue
        // class
        if (!input.equals(""))
        {
          output = MainPanel
              .getDisplayOutput("(" + input + ") = \n(" + result.toString() + ")\n\n");
        }
        else
        {
          output = MainPanel.getDisplayOutput("= \n" + result.toString() + ")\n\n");
        }
        theDis.insertComponent(output);
        MainPanel.clear();
        previousPress = "n";
        break;
      case "text":
        // total.concat(theDis.getText());
        result = calculateBasedOnPreviousButton(parseSingleValue(input));
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue
        // class
        if (!input.equals(""))
        {
          output = MainPanel
              .getDisplayOutput("(" + input + ") = \n(" + result.toString() + ")\n\n");
        }
        else
        {
          output = MainPanel.getDisplayOutput("= \n(" + result.toString() + ")\n\n");
        }
        theDis.insertComponent(output);
        MainPanel.clear();
        previousPress = "n";
        break;
      case "/":
        result = calculateBasedOnPreviousButton(parseSingleValue(input));
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue
        // class
        output = MainPanel.getDisplayOutput("(" + input + ") / ");
        theDis.insertComponent(output);
        MainPanel.getInput().setText("");
        previousPress = command;
        break;
      case "*":
        result = calculateBasedOnPreviousButton(parseSingleValue(input));
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue
        // class
        output = MainPanel.getDisplayOutput("(" + input + ") * ");
        theDis.insertComponent(output);
        MainPanel.getInput().setText("");
        previousPress = command;
        break;
      case "-":
        result = calculateBasedOnPreviousButton(parseSingleValue(input));
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue
        // class
        output = MainPanel.getDisplayOutput("(" + input + ") - ");
        theDis.insertComponent(output);
        MainPanel.getInput().setText("");
        previousPress = command;
        break;
      case "+":
        result = calculateBasedOnPreviousButton(parseSingleValue(input));
        // add a line for parsing the text here to pass it to the ComplexNumber or ComplexValue
        // class
        output = MainPanel.getDisplayOutput("(" + input + ") + ");
        theDis.insertComponent(output);
        MainPanel.getInput().setText("");
        previousPress = command;
        break;
      case "C":
        MainPanel.clear();
        break;
      case "R":
        MainPanel.reset();
        // total = "";
        previousPress = "n";
        result = null;
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

  /**
   * Ignore for now, was using to parse the entire expression until I figured I can parse one at a
   * time instead.
   * 
   * @param exp
   * @return a
   */
  private ImgNumber parseExpression(final String exp)
  {
    ArrayList<String> unparsedValuesAndNumbers = new ArrayList<String>();
    ArrayList<ComplexValue> parsedValues = new ArrayList<ComplexValue>();

    int numOfValues = 0;
    int indOfLastLeft = exp.lastIndexOf("(");

    for (int i = 0; i < exp.length(); i++)
    {
      if (exp.charAt(i) == '(')
      {
        numOfValues++;
      }
    }

    // Figure out way to add the first value if that's actually something that's needed

    // int parenInd = indOfLeftParen.length - 1;
    for (int i = 0; i < exp.length(); i++)
    {
      if (exp.charAt(i) == '+' || exp.charAt(i) == '-' || exp.charAt(i) == '*'
          || exp.charAt(i) == '/')
      {
        unparsedValuesAndNumbers
            .add(exp.substring(exp.lastIndexOf("(", i) + 1, exp.indexOf(")", i)));
      }
    }

    /**
     * for (String value : unparsedValuesAndNumbers) { parsedValues.add(parseValues(value)); }
     */
    // parsedValues = parseValues(value);

    // returning null for sake of compilation and because I figured that since we're just using
    // single ImgNumber objects, I can just parse each entry after each button press and do the
    // calculation from switch/case itself
    return null;
  }

  /**
   * Ignore for now, was using to parse the entire expression until I figured I can parse one at a
   * time instead.
   * 
   * @param theValue
   * @return a
   */
  private ArrayList<ImgNumber> parseValues(final String theValue)
  {
    String[] allValues = theValue.split(" ");
    // ArrayList<String> actualValues = new ArrayList<String>();
    // ArrayList<ComplexValue> finalValues = new ArrayList<ComplexValue>();

    /**
     * for (int i = 0; i < allValues.length; i++) { if (allValues[i] == "+" || allValues[i] == "-"
     * || allValues[i] == "*" || allValues[i] == "/") { allValues[i] += allValues[i + 1]; } }
     * 
     * actualValues.add(allValues[0]); for (String value : allValues) { if (!value.contains("+") &&
     * !value.contains("-") && !value.contains("*") && !value.contains("/")) {
     * actualValues.add(value); } }
     */

    return null;
  }

  /**
   * Parse a single value.
   * 
   * @param value
   *          the value to parse
   * @return the parsed value
   */
  private ImgNumber parseSingleValue(final String value)
  {
    Operator op = null;
    String[] allParts = new String[2];
    if (value.contains("+"))
    {
      allParts[0] = value.substring(0, value.indexOf("+"));
      allParts[1] = value.substring(value.indexOf("+"), value.length() - 1);
      op = Operator.getFrom("+");
    }
    else if (value.contains("*"))
    {
      allParts[0] = value.substring(0, value.indexOf("*"));
      allParts[1] = value.substring(value.indexOf("*"), value.length() - 1);
      op = Operator.getFrom("*");
    }
    else if (value.contains("/"))
    {
      allParts[0] = value.substring(0, value.indexOf("/"));
      allParts[1] = value.substring(value.indexOf("/"), value.length() - 1);
      op = Operator.getFrom("/");
    }
    else if (value.contains("-"))
    {
      if (value.charAt(0) != '-')
      {
        allParts[0] = value.substring(0, value.indexOf("-"));
        allParts[1] = value.substring(value.indexOf("-"), value.length() - 1);
        op = Operator.getFrom("-");
      }
      else
      {
        allParts[0] = value.substring(0, value.lastIndexOf("-"));
        allParts[1] = value.substring(value.lastIndexOf("-"), value.length() - 1);
        op = Operator.getFrom("-");
      }
    }
    double real = 0.0;
    double img = 0.0;

    // allParts[allParts.length - 1] = allParts[allParts.length - 1].substring(0, allParts.length -
    // 1);

    for (String part : allParts)
    {
      part.replaceAll("\\s", "");
    }

    real = Double.parseDouble(allParts[0]);
    img = Double.parseDouble(allParts[1]);

    return new ImgNumber(real, img, op);
  }

  /**
   * Checks the prev button.
   * 
   * @param theNumber
   *          the case to switch
   * @return the complex number
   */
  private ImgNumber calculateBasedOnPreviousButton(final ImgNumber theNumber)
  {
    ImgNumber toReturn = null;
    switch (previousPress)
    {
      case "/":
        toReturn = result.divide(theNumber);
        break;
      case "*":
        toReturn = result.multiply(theNumber);
        break;
      case "-":
        toReturn = result.subtract(theNumber);
        break;
      case "+":
        toReturn = result.add(theNumber);
        break;
      default:
        toReturn = theNumber;
    }
    return toReturn;
  }
  
  private boolean ifNoInput() {
    if (result == null) {
      return true;
    } else {
      return false;
    }
  }
}
