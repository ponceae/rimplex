package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

import javax.swing.*;
import java.util.ArrayList;
import calculator.*;

/**
 * Listener - Class which implements ActionListener to listen for JButtons pushes and JTextField
 * entries.
 * 
 * @author Ulises Fernandez and Andrew Elbert
 * @version (3/31/21)
 */
public class Listener extends KeyAdapter implements ActionListener
{
  private static Listener listener;
  private ImgNumber result = null;
  private String previousPress = "n";
  // private History theHistory;
  private boolean recentlyReset = false;
  private boolean leftParenthese = false;
  private boolean rightParenthese = false;

  private calculator calc;

  // I need to figure out if I need one or two Number variables for the running calculations (i.e.
  // one for the previous total, which is null at the beginning and then is just the first entry and
  // then is the first entry div by, sub by, etc whatever then next entry is, etc etc and then one
  // for whatever the next entry itself is) and then calculate based on whatever the previous
  // operator press was and if the previous entry is not null

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
    JTextPane theDis = MainPanel.getDisplay();
    String command = e.getActionCommand();

    // Input
    String input = theDis.getText();
    // Output
    JTextArea output;

    // A String representation of the total expression to be parsed
    // String total = "";

    try
    {
      int number = Integer.parseInt(command);
      if (!leftParenthese)
      {
        MainPanel.appendDisplay("(");
      }
      MainPanel.appendDisplay(stringNum(number));
    }
    catch (NumberFormatException nfe)
    {
      switch (command)
      {
        case "=":
          break;
        case "+":
          if (!previousPressOperator())
          {
            if (!rightParenthese)
            {
              MainPanel.appendDisplay(command);
            }
            else
            {
              // parse and calculation
              rightParenthese = false;
              leftParenthese = false;
            }
          }
          break;
        case "-":
          if (!previousPressOperator())
          {
            if (!rightParenthese)
            {
              MainPanel.appendDisplay(command);
            }
            else
            {
              // parse and calculation
              rightParenthese = false;
              leftParenthese = false;
            }
          }
          break;
        case "/":
          if (!previousPressOperator())
          {
            if (!rightParenthese)
            {
              MainPanel.appendDisplay(command);
            }
            else
            {
              // parse and calculation
              rightParenthese = false;
              leftParenthese = false;
            }
          }
          break;
        case "x":
          if (!previousPressOperator())
          {
            if (!rightParenthese)
            {
              MainPanel.appendDisplay(command);
            }
            else
            {
              // parse and calculation
              rightParenthese = false;
              leftParenthese = false;
            }
          }
          break;
        case "(":
          MainPanel.appendDisplay("(");
          leftParenthese = true;
          break;
        case ")":
          MainPanel.appendDisplay(")");
          rightParenthese = true;
          break;
        case ".":
          MainPanel.appendDisplay(".");
          break;
        case "i":
          MainPanel.appendDisplay("i");
          break;
        case "Inv":
          break;
        case "+/-":
          MainPanel.toggleSign();
          break;
        case "<-":
          MainPanel.backspace();
          break;
        case "C":
          MainPanel.clearDisplay();
          break;
        case "R":
          MainPanel.clearDisplay();
          break;
        default:
          System.exit(0);
      }
    }

    /**
     * switch (command) { case "=": if (!noInput()) { // total.concat(theDis.getText()); result =
     * calculateBasedOnPreviousButton(parseSingleValue(input)); // add a line for parsing the text
     * here to pass it to the ComplexNumber or ComplexValue // class if (!input.equals("")) { output
     * = MainPanel .getDisplayOutput("(" + input + ") = \n(" + result.toString() + ")\n\n"); } else
     * { output = MainPanel.getDisplayOutput("= \n" + result.toString() + ")\n\n"); }
     * theDis.insertComponent(output); MainPanel.clearDisplay(); previousPress = "n"; } break; case
     * "text": if (!noInput()) { // total.concat(theDis.getText()); result =
     * calculateBasedOnPreviousButton(parseSingleValue(input)); // add a line for parsing the text
     * here to pass it to the ComplexNumber or ComplexValue // class if (!input.equals("")) { output
     * = MainPanel .getDisplayOutput("(" + input + ") = \n(" + result.toString() + ")\n\n"); } else
     * { output = MainPanel.getDisplayOutput("= \n(" + result.toString() + ")\n\n"); }
     * theDis.insertComponent(output); MainPanel.clearDisplay(); previousPress = "n"; } break; case
     * "/": result = calculateBasedOnPreviousButton(parseSingleValue(input)); // add a line for
     * parsing the text here to pass it to the ComplexNumber or ComplexValue // class if
     * (recentlyReset) { output = MainPanel.getDisplayOutput("cancelled\n\n(" + input + ") / "); }
     * else { output = MainPanel.getDisplayOutput("(" + input + ") / "); }
     * theDis.insertComponent(output); // MainPanel.getInput().setText(""); previousPress = command;
     * recentlyReset = false; break; case "*": result =
     * calculateBasedOnPreviousButton(parseSingleValue(input)); // add a line for parsing the text
     * here to pass it to the ComplexNumber or ComplexValue // class if (recentlyReset) { output =
     * MainPanel.getDisplayOutput("cancelled\n\n(" + input + ") * "); } else { output =
     * MainPanel.getDisplayOutput("(" + input + ") * "); } theDis.insertComponent(output); //
     * MainPanel.getInput().setText(""); previousPress = command; recentlyReset = false; break; case
     * "-": result = calculateBasedOnPreviousButton(parseSingleValue(input)); // add a line for
     * parsing the text here to pass it to the ComplexNumber or ComplexValue // class if
     * (recentlyReset) { output = MainPanel.getDisplayOutput("cancelled\n\n(" + input + ") - "); }
     * else { output = MainPanel.getDisplayOutput("(" + input + ") - "); }
     * theDis.insertComponent(output); // MainPanel.getInput().setText(""); previousPress = command;
     * recentlyReset = false; break; case "+": result =
     * calculateBasedOnPreviousButton(parseSingleValue(input)); // add a line for parsing the text
     * here to pass it to the ComplexNumber or ComplexValue // class if (recentlyReset) { output =
     * MainPanel.getDisplayOutput("cancelled\n\n(" + input + ") + "); } else { output =
     * MainPanel.getDisplayOutput("(" + input + ") + "); } theDis.insertComponent(output); //
     * MainPanel.getInput().setText(""); previousPress = command; recentlyReset = false; break; case
     * "C": MainPanel.clearDisplay(); break; case "R": MainPanel.clearDisplay(); // total = "";
     * previousPress = "n"; result = null; recentlyReset = true; break; default: System.exit(0); }
     */
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

    return null;
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

  private boolean noInput()
  {
    if (result == null)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  private String stringNum(int num)
  {
    return "" + num;
  }

  private boolean previousPressOperator()
  {
    char theChar = MainPanel.getDisplay().getText().charAt(0);
    return (theChar == '+') || (theChar == '-') || (theChar == '/') || (theChar == '*');
  }
}
