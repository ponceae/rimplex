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
 * @author Ulises Fernandez, Andrew Elbert, and Ian lips
 * @version (3/31/21)
 */
public class Listener extends KeyAdapter implements ActionListener
{
  private HistoryWindow theHistoryWindow;
  private static Listener listener;
  private String previousOp = "n";
  private String previousButton = "n";
  private History theHistory = History.getInstance();
  // private boolean recentlyReset = false;
  private boolean leftParenthese = false;
  private boolean rightParenthese = false;
  private boolean alreadyHasOperator = false;
  private boolean alreadyHasImaginary = false;
  private boolean firstDecimal = false;
  private boolean secondDecimal = false;

  private calculator calc = new calculator();
  
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
   * Gives the functionality to all the swing buttons and displays errors in entry.
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
        MainPanel.displayError("The Expression Should Start With a '('");
        leftParenthese = true;
      }
      MainPanel.appendDisplay(command);
      previousButton = command;
    }
    catch (NumberFormatException nfe)
    {
      switch (command)
      {
        case "<":
          theHistoryWindow.close();
          break;
        case ">":
          theHistoryWindow = HistoryWindow.getInstance();
          break;
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
                if (input.charAt(0) == '-') {
                  currentOperand = parseSingleValue(input, "-");
                } else {
                  currentOperand = parseSingleValue(input, "+");
                }
                previousResult = calculateBasedOnPreviousButton(currentOperand);
                currExpression.concat(input + " = " + previousResult.toString());
                rightParenthese = false;
                leftParenthese = false;
                alreadyHasOperator = false;
                alreadyHasImaginary = false;
                startRunning = true;
                MainPanel.setDisplay(previousResult.toString());
                previousOp = command;
              }
              else {
                if (!input.contains(")"))
                {
                  input = input.concat(")");
                }
                if (input.charAt(0) == '-') {
                  currentOperand = parseSingleValue(input, "-");
                } else {
                  currentOperand = parseSingleValue(input, "+");
                }
                previousResult = calculateBasedOnPreviousButton(currentOperand);
                currExpression.concat(input + " = " + previousResult.toString());
                rightParenthese = false;
                leftParenthese = false;
                alreadyHasOperator = false;
                alreadyHasImaginary = false;
                startRunning = true;
                MainPanel.setDisplay(previousResult.toString());
                previousOp = command;
              }
            }
            else if (noPress())
            {
              if (!input.contains("("))
              {
                MainPanel.setDisplay("(" + input);
              }
              if (!input.contains(")"))
              {
                MainPanel.appendDisplay(")");
              }
              if (input.charAt(0) == '-') {
                currentOperand = parseSingleValue(input, "-");
              } else {
                currentOperand = parseSingleValue(input, "+");
              }
              currExpression.concat(input + " = " + previousResult.toString());
              rightParenthese = false;
              leftParenthese = false;
              alreadyHasOperator = false;
              alreadyHasImaginary = false;
              startRunning = true;
              MainPanel.setDisplay(previousResult.toString());
              previousOp = command;
            }
          }
          else
          {
            MainPanel.displayError("There is no entry");
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
          else
          {
            MainPanel.displayError("')' Must be Preceded by a '('");
          }
          rightParenthese = true;
          previousButton = command;
          break;
        case ".":
          if (!firstDecimal)
          {
            MainPanel.appendDisplay(".");
            firstDecimal = true;
          } else if (!secondDecimal) 
          {
            if (!alreadyHasImaginary && alreadyHasOperator) 
            {
              MainPanel.appendDisplay(".");
              secondDecimal = true;
            }
          }
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
          if (command.equals("-")) {
            currentOperand = parseSingleValue(input, "-");
          } else {
            currentOperand = parseSingleValue(input, "+");
          }
          rightParenthese = false;
          leftParenthese = false;
          alreadyHasOperator = false;
          alreadyHasImaginary = false;
          if (noPress())
          {
            theHistory.add(input + " inverse to " + currentOperand.inverse().toString());
            MainPanel.setDisplay(currentOperand.inverse().toString());
          }
          else
          {
            theHistory.add(input + " inverse to " + previousResult.inverse().toString());
            MainPanel.setDisplay(currentOperand.inverse().toString());
          }
          startNew = true;
          previousButton = command;
          break;
        case "+/-":
          if (!noInput())
          {
            MainPanel.toggleSign();
          }
          else
          {
            MainPanel.displayError("There is NO Expression to toggle sign");
          }
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
          previousOp = "n";
          leftParenthese = false;
          rightParenthese = false;
          startRunning = false;
          alreadyHasOperator = false;
          alreadyHasImaginary = false;
          theHistory.reset();
          previousButton = command;
          break;
        default:
          if (!previousPressOperatorMinusEquals())
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
              if (command.equals("-")) {
                previousResult = parseSingleValue(input, "-");
              } else {
                previousResult = parseSingleValue(input, "+");
              }
              rightParenthese = false;
              leftParenthese = false;
              alreadyHasOperator = false;
              alreadyHasImaginary = false;
              MainPanel.clearDisplay();
              currExpression.concat(input + " " + command + " ");
              startNew = true;
              previousButton = command;
              previousOp = command;
            }
            else
            {
              if (command.equals("-")) {
                currentOperand = parseSingleValue(input, "-");
              } else {
                currentOperand = parseSingleValue(input, "+");
              }

              if (currentOperand.getImg() == 0 && currentOperand.getReal() == 0
                  && command.equals("/"))
              {
                MainPanel.displayError("Cannot divide by 0");
              }

              previousResult = calculateBasedOnPreviousButton(currentOperand);
              rightParenthese = false;
              leftParenthese = false;
              alreadyHasOperator = false;
              alreadyHasImaginary = false;
              previousOp = command;
              if (startRunning)
              {
                MainPanel.setDisplay(previousResult.toString());
                currExpression.concat(input + " " + command + " ");
                startNew = true;
                previousButton = command;
              }
              else
              {
                MainPanel.clearDisplay();
                startRunning = true;
                currExpression.concat(input + " " + command + " ");
                startNew = true;
                previousButton = command;
              }
            }
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
  private ImgNumber parseSingleValue(final String value, final String operator)
  {
    boolean wholeNegative = false;
    Operator op = Operator.getFrom(operator);
    String[] allParts = new String[2];
    if (value.contains("-"))
    {
      if (value.charAt(0) == '-')
      {
        if (value.contains("+"))
        {
          allParts[0] = value.substring(2, value.indexOf("+"));
          allParts[1] = value.substring(value.indexOf("+") + 1, value.length() - 2);
          // op = Operator.getFrom("+");
        }
        else if (value.contains("*"))
        {
          allParts[0] = value.substring(2, value.indexOf("*"));
          allParts[1] = value.substring(value.indexOf("*") + 1, value.length() - 2);
          // op = Operator.getFrom("*");
        }
        else if (value.contains("/"))
        {
          if (value.charAt(value.indexOf("/") + 1) == '0'
              && value.substring(0, value.indexOf("/") + 4).length() == value.length())
          {
            MainPanel.displayError("Cannot Divide by 0");
          }
          else
          {
            allParts[0] = value.substring(2, value.indexOf("/"));
            allParts[1] = value.substring(value.indexOf("/") + 1, value.length() - 2);
            // op = Operator.getFrom("/");
          }
        }
        else
        {
          allParts[0] = value.substring(2, value.indexOf("-"));
          allParts[1] = value.substring(value.indexOf("-"), value.length() - 2);
          // op = Operator.getFrom("-");
        }
        wholeNegative = true;
      }
      else
      {
        allParts[0] = value.substring(1, value.lastIndexOf("-"));
        allParts[1] = value.substring(value.lastIndexOf("-"), value.length() - 2);
        // op = Operator.getFrom("-");
      }
    }
    else
    {
      if (value.contains("+"))
      {
        allParts[0] = value.substring(1, value.indexOf("+"));
        allParts[1] = value.substring(value.indexOf("+") + 1, value.length() - 2);
        // op = Operator.getFrom("+");
      }
      else if (value.contains("*"))
      {
        allParts[0] = value.substring(1, value.indexOf("*"));
        allParts[1] = value.substring(value.indexOf("*") + 1, value.length() - 2);
        // op = Operator.getFrom("*");
      }
      else if (value.contains("/"))
      {
        if (value.charAt(value.indexOf("/") + 1) == '0'
            && value.substring(0, value.indexOf("/") + 4).length() == value.length())
        {
          MainPanel.displayError("Cannot Divide by 0");
        }
        else
        {
          System.out.println(value.toString());
          allParts[0] = value.substring(1, value.indexOf("/"));
          allParts[1] = value.substring(value.indexOf("/") + 1, value.length() - 2);
          // op = Operator.getFrom("/");
        }
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
      } else if (containsOperator(value) && !value.contains("i")) {
        
      }
    }
    double real = 0.0;
    double img = 0.0;

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

    switch (previousOp)
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
    if (previousOp.equals("n"))
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

  private boolean previousPressOperatorMinusEquals()
  {
    return (previousButton == "+") || (previousButton == "-") || (previousButton == "/")
        || (previousButton == "*") || (previousButton == "Inv");
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
