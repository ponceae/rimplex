package gui;

// import java.awt.Font;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

import javax.swing.*;
// import java.util.ArrayList;
import calculator.*;
// import calculator.Number;

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
  private String previousPress = "n";
  // private History theHistory;
  // private boolean recentlyReset = false;
  private boolean leftParenthese = false;
  private boolean rightParenthese = false;
  private boolean alreadyHasOperator = false;
  private boolean alreadyHasImaginary = false;

  // private calculator calc;

  // I need to figure out if I need one or two Number variables for the running calculations (i.e.
  // one for the previous total, which is null at the beginning and then is just the first entry and
  // then is the first entry div by, sub by, etc whatever then next entry is, etc etc and then one
  // for whatever the next entry itself is) and then calculate based on whatever the previous
  // operator press was and if the previous entry is not null
  private ImgNumber previousResult = new ImgNumber(0, 0);
  private ImgNumber currentOperand = new ImgNumber(0, 0);
  private boolean startRunning = false;

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
    // JTextArea output;

    // A String representation of the total expression to be parsed
    // String total = "";

    try
    {
      int number = Integer.parseInt(command);
      if (!leftParenthese)
      {
        MainPanel.appendDisplay("(");
        leftParenthese = true;
      }
      MainPanel.appendDisplay(stringNum(number));
    }
    catch (NumberFormatException nfe)
    {
      switch (command)
      {
        case "=":
          if (!noInput())
          {
            if (!noPress())
            {
              if (containsOperator(input) && containsImaginary(input))
              {
                currentOperand = parseSingleValue(input);
                previousResult = calculateBasedOnPreviousButton(currentOperand);
                rightParenthese = false;
                leftParenthese = false;
                MainPanel.setDisplay(previousResult.toString());
              }
              parseSingleValue(input);
            } else if (previousPress.equals("n"))
            {
              if (!input.contains("("))
              {
                MainPanel.setDisplay("(" + input);
              }
              if (!input.contains(")"))
              {
                MainPanel.appendDisplay(")");
              }
            }
          }
          break;
          /**
        case "+":
          if (!previousPressOperator())
          {
            if (!rightParenthese)
            {
              MainPanel.appendDisplay(command);
            }
            else
            {
              previousResult
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
          */
        case "(":
          if (!leftParenthese)
          {
            MainPanel.appendDisplay("(");
          }
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
          if (!alreadyHasImaginary)
          {
            MainPanel.appendDisplay("i");
          }
          alreadyHasImaginary = true;
          break;
        case "Inv":
          break;
        case "+/-":
          MainPanel.toggleSign();
          break;
        case "<-":
          char removed = MainPanel.backspace();
          if (isOperator(removed)) {
            alreadyHasOperator = false;
          } else if (removed == '(')
          {
            leftParenthese = false;
          } else if (removed == ')')
          {
            rightParenthese = false;
          } else if (removed == 'i')
          {
            alreadyHasImaginary = false;
          }
          break;
        case "C":
          MainPanel.clearDisplay();
          leftParenthese = false;
          rightParenthese = false;
          break;
        case "R":
          MainPanel.clearDisplay();
          previousResult = new ImgNumber(0, 0);
          currentOperand = new ImgNumber(0, 0);
          // recentlyReset = true;
          previousPress = "n";
          leftParenthese = false;
          rightParenthese = false;
          startRunning = false;
          alreadyHasOperator = false;
          alreadyHasImaginary = false;
          break;
        default:
          if (!previousPressOperator())
          {
            if (!rightParenthese)
            {
              if (!alreadyHasOperator)
              {
                MainPanel.appendDisplay(command);
                alreadyHasOperator = true;
              }
            }
            else if (noPress())
            {
              previousResult = parseSingleValue(input);
              rightParenthese = false;
              leftParenthese = false;
              alreadyHasOperator = false;
              alreadyHasImaginary = false;
              MainPanel.clearDisplay();
            } else
            {
              if (startRunning)
              {
                currentOperand = parseSingleValue(input);
                previousResult = calculateBasedOnPreviousButton(currentOperand);
                rightParenthese = false;
                leftParenthese = false;
                alreadyHasOperator = false;
                alreadyHasImaginary = false;
                MainPanel.setDisplay(previousResult.toString());
              } else {
                currentOperand = parseSingleValue(input);
                previousResult = calculateBasedOnPreviousButton(currentOperand);
                rightParenthese = false;
                leftParenthese = false;
                alreadyHasOperator = false;
                alreadyHasImaginary = false;
                MainPanel.clearDisplay();
                startRunning = true;
              }
            }
            previousPress = command;
          }
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
  
  //*****************************PRIVATE METHODS*******************************

  private boolean containsOperator(String input)
  {
    boolean containsMinus = false;
    if (input.contains("-"))
    {
      if (input.lastIndexOf('-') != 0)
      {
        containsMinus = true;
      }
    }
    return (input.contains("+")) || containsMinus || (input.contains("/")) || (input.contains("*"));
  }
  
  private boolean containsImaginary(String input)
  {
    return input.contains("i");
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
    // Need to to add cases for when 
    Operator op = null;
    String[] allParts = new String[2];
    /**
    if (value.contains("+"))
    {
      allParts[0] = value.substring(1, value.indexOf("+"));
      allParts[1] = value.substring(value.indexOf("+") + 1, value.length() - 2);
      op = Operator.getFrom("+");
    }
    else if (value.contains("*"))
    {
      allParts[0] = value.substring(1, value.indexOf("*"));
      allParts[1] = value.substring(value.indexOf("*") + 1, value.length() - 2);
      op = Operator.getFrom("*");
    }
    else if (value.contains("/"))
    {
      allParts[0] = value.substring(1, value.indexOf("/"));
      allParts[1] = value.substring(value.indexOf("/") + 1, value.length() - 2);
      op = Operator.getFrom("/");
    }
    */
    if (value.contains("-"))
    {
      if (value.charAt(0) == '-')
      {
        if (value.contains("+"))
        {
          allParts[0] = value.substring(2, value.indexOf("+"));
          allParts[1] = value.substring(value.indexOf("+") + 1, value.length() - 2);
          op = Operator.getFrom("+");
        }
        else if (value.contains("*"))
        {
          allParts[0] = value.substring(2, value.indexOf("*"));
          allParts[1] = value.substring(value.indexOf("*") + 1, value.length() - 2);
          op = Operator.getFrom("*");
        }
        else if (value.contains("/"))
        {
          allParts[0] = value.substring(2, value.indexOf("/"));
          allParts[1] = value.substring(value.indexOf("/") + 1, value.length() - 2);
          op = Operator.getFrom("/");
        } else 
        {
          allParts[0] = value.substring(2, value.indexOf("-"));
          allParts[1] = value.substring(value.indexOf("-") + 1, value.length() - 2);
          op = Operator.getFrom("-");
        }
      } else
      {
        allParts[0] = value.substring(1, value.lastIndexOf("-"));
        allParts[1] = value.substring(value.lastIndexOf("-") + 1, value.length() - 2);
        op = Operator.getFrom("-");
      }
    }
    else
    {
      if (value.contains("+"))
      {
        allParts[0] = value.substring(1, value.indexOf("+"));
        allParts[1] = value.substring(value.indexOf("+") + 1, value.length() - 2);
        op = Operator.getFrom("+");
      }
      else if (value.contains("*"))
      {
        allParts[0] = value.substring(1, value.indexOf("*"));
        allParts[1] = value.substring(value.indexOf("*") + 1, value.length() - 2);
        op = Operator.getFrom("*");
      }
      else if (value.contains("/"))
      {
        allParts[0] = value.substring(1, value.indexOf("/"));
        allParts[1] = value.substring(value.indexOf("/") + 1, value.length() - 2);
        op = Operator.getFrom("/");
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
   * @param secondNumber
   *          the case to switch
   * @return the complex number
   */
  private ImgNumber calculateBasedOnPreviousButton(final ImgNumber secondNumber)
  {
    ImgNumber toReturn = null;
    
    switch (previousPress)
    {
      case "/":
        toReturn = previousResult.divide(secondNumber);
        break;
      case "*":
        toReturn = previousResult.multiply(secondNumber);
        break;
      case "-":
        toReturn = previousResult.subtract(secondNumber);
        break;
      case "+":
        toReturn = previousResult.add(secondNumber);
        break;
      default:
        toReturn = previousResult;
    }
    return toReturn;
  }

  /**
   * 
   * @return
   */
  private boolean noInput()
  {
    return MainPanel.getDisplay().getText().isEmpty();
  }

  /**
   * 
   * @return
   */
  private boolean noPress()
  {
    if (previousPress.equals("n"))
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * 
   * @param num
   * @return
   */
  private String stringNum(int num)
  {
    return "" + num;
  }

  /**
   * 
   * @return
   */
  private boolean previousPressOperator()
  {
    char theChar = MainPanel.getDisplay().getText().charAt(0);
    return (theChar == '+') || (theChar == '-') || (theChar == '/') || (theChar == '*');
  }
  
  private boolean isOperator(char toCompare) {
    return (toCompare == '+') || (toCompare == '-') || (toCompare == '/') || (toCompare == '*');
  }

}
