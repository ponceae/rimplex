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
  private String previousButton = "n";
  private History theHistory = new History();
  // private boolean recentlyReset = false;
  private boolean leftParenthese = false;
  private boolean rightParenthese = false;
  private boolean alreadyHasOperator = false;
  private boolean alreadyHasImaginary = false;

  private calculator calc = new calculator();

  // I need to figure out if I need one or two Number variables for the running calculations (i.e.
  // one for the previous total, which is null at the beginning and then is just the first entry and
  // then is the first entry div by, sub by, etc whatever then next entry is, etc etc and then one
  // for whatever the next entry itself is) and then calculate based on whatever the previous
  // operator press was and if the previous entry is not null
  private ImgNumber previousResult = new ImgNumber(0, 0);
  private ImgNumber currentOperand = new ImgNumber(0, 0);
  private boolean startRunning = false;
  private boolean startNew = false;

  private String currExpression = "";

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

    try
    {
      @SuppressWarnings("unused")
      int number = Integer.parseInt(command);
      if (startRunning && previousPressOperator() && startNew)
      {
        MainPanel.clearDisplay();
        startNew = false;
      }
      if (!leftParenthese)
      {
        MainPanel.appendDisplay("(");
        leftParenthese = true;
      }
      MainPanel.appendDisplay(command);
      previousButton = command;
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
                if (!input.contains(")"))
                {
                  input = input.concat(")");
                }
                currentOperand = parseSingleValue(input);
                previousResult = calculateBasedOnPreviousButton(currentOperand);
                currExpression.concat(input + " = " + previousResult.toString());
                rightParenthese = false;
                leftParenthese = false;
                MainPanel.setDisplay(previousResult.toString());
                previousPress = command;
              }
            }
            else if (previousPress.equals("n"))
            {
              if (!input.contains("("))
              {
                MainPanel.setDisplay("(" + input);
              }
              if (!input.contains(")"))
              {
                MainPanel.appendDisplay(")");
              }
              previousResult = parseSingleValue(input);
              currExpression.concat(input + " = " + previousResult.toString());
              rightParenthese = false;
              leftParenthese = false;
              MainPanel.setDisplay(previousResult.toString());
              previousPress = command;
            }
          }
          startNew = true;
          previousButton = command;
          break;
        case "(":
          if (startRunning && previousPressOperator() && startNew)
          {
            MainPanel.clearDisplay();
            startNew = false;
          }
          if (!leftParenthese)
          {
            MainPanel.appendDisplay("(");
          }
          leftParenthese = true;
          previousButton = command;
          break;
        case ")":
          if (leftParenthese)
          {
            MainPanel.appendDisplay(")");
          }
          rightParenthese = true;
          previousButton = command;
          break;
        case ".":
          MainPanel.appendDisplay(".");
          previousButton = command;
          break;
        case "i":
          if (!alreadyHasImaginary)
          {
            MainPanel.appendDisplay("i");
          }
          alreadyHasImaginary = true;
          previousButton = command;
          break;
        case "Inv":
          currentOperand = parseSingleValue(input);
          rightParenthese = false;
          leftParenthese = false;
          alreadyHasOperator = false;
          alreadyHasImaginary = false;
          theHistory.add(input + " inverse to " + currentOperand.inverse().toString());
          MainPanel.setDisplay(currentOperand.inverse().toString());
          startNew = true;
          previousButton = command;
          break;
        case "+/-":
          MainPanel.toggleSign();
          previousButton = command;
          break;
        case "<-":
          char removed = MainPanel.backspace();
          if (isOperator(removed))
          {
            alreadyHasOperator = false;
          }
          else if (removed == '(')
          {
            leftParenthese = false;
          }
          else if (removed == ')')
          {
            rightParenthese = false;
          }
          else if (removed == 'i')
          {
            alreadyHasImaginary = false;
          }
          previousButton = command;
          break;
        case "C":
          MainPanel.clearDisplay();
          leftParenthese = false;
          rightParenthese = false;
          alreadyHasOperator = false;
          alreadyHasImaginary = false;
          previousButton = command;
          break;
        case "R":
          MainPanel.clearDisplay();
          previousResult = new ImgNumber(0, 0);
          currentOperand = new ImgNumber(0, 0);
          previousPress = "n";
          leftParenthese = false;
          rightParenthese = false;
          startRunning = false;
          alreadyHasOperator = false;
          alreadyHasImaginary = false;
          theHistory.reset();
          previousButton = command;
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
              currExpression.concat(input + " " + command + " ");
              startNew = true;
              previousButton = command;
            }
            else
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
                currExpression.concat(input + " " + command + " ");
                startNew = true;
                previousButton = command;
              }
              else
              {
                currentOperand = parseSingleValue(input);
                previousResult = calculateBasedOnPreviousButton(currentOperand);
                rightParenthese = false;
                leftParenthese = false;
                alreadyHasOperator = false;
                alreadyHasImaginary = false;
                MainPanel.clearDisplay();
                startRunning = true;
                currExpression.concat(input + " " + command + " ");
                startNew = true;
                previousButton = command;
              }
            }
            previousPress = command;
          }
      }
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

  // *****************************PRIVATE METHODS*******************************

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
    boolean wholeNegative = false;
    Operator op = null;
    String[] allParts = new String[2];
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
        }
        else
        {
          allParts[0] = value.substring(2, value.indexOf("-"));
          allParts[1] = value.substring(value.indexOf("-"), value.length() - 2);
          op = Operator.getFrom("-");
        }
        wholeNegative = true;
      }
      else
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
      else if (!containsNumber(value))
      {
        return new ImgNumber(0, 0);
      }
      else if (!containsOperator(value))
      {
        if (value.contains("i"))
        {
          allParts[0] = "0";
          allParts[1] = value.substring(value.indexOf("(") + 1, value.indexOf("i"));
        }
        else
        {
          allParts[0] = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
          allParts[1] = "0";
        }
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

    String part1 = allParts[0];
    String part2 = allParts[1];
    if (wholeNegative)
    {
      if (!part1.equals("0"))
      {
        real = -Double.parseDouble(allParts[0]);
      }
      else
      {
        real = 0.0;
      }
      if (!part2.equals("0"))
      {
        img = -Double.parseDouble(allParts[1]);
      }
      else
      {
        img = 0.0;
      }
    }
    else
    {
      if (!part1.equals("0"))
      {
        real = Double.parseDouble(allParts[0]);
      }
      else
      {
        real = 0.0;
      }
      if (!part2.equals("0"))
      {
        img = Double.parseDouble(allParts[1]);
      }
      else
      {
        img = 0.0;
      }
    }
    // Real realNum = new Real(real);
    ImgNumber imgNum = new ImgNumber(real, img, op);

    return imgNum;
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
        toReturn = (ImgNumber) calc.divide(previousResult, secondNumber);
        break;
      case "*":
        toReturn = (ImgNumber) calc.multiply(previousResult, secondNumber);
        break;
      case "-":
        toReturn = (ImgNumber) calc.subtract(previousResult, secondNumber);
        break;
      case "+":
        toReturn = (ImgNumber) calc.add(previousResult, secondNumber);
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
   * @return
   */
  private boolean previousPressOperator()
  {
    return (previousButton == "+") || (previousButton == "-") || (previousButton == "/")
        || (previousButton == "*") || (previousButton == "=") || (previousButton == "Inv");
  }

  private boolean isOperator(char toCompare)
  {
    return (toCompare == '+') || (toCompare == '-') || (toCompare == '/') || (toCompare == '*');
  }

  private boolean containsNumber(String text)
  {
    return (text.contains("1")) || (text.contains("2")) || (text.contains("3"))
        || (text.contains("4")) || (text.contains("5")) || (text.contains("6"))
        || (text.contains("7")) || (text.contains("8")) || (text.contains("9"))
        || (text.contains("0"));
  }

}
