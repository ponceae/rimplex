package gui;

import java.awt.BorderLayout;

// import java.awt.Font;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

import javax.swing.*;
// import java.util.ArrayList;
import calculator.*;
import calculator.Number;
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
  private History theHistory = History.getInstance();

  private String previousOp = "(";
  private String lastPerformed = "n";
  private String previousButton = "n";

  // Booleans for checking if a button press is allowed or what happens in certain cases
  private boolean leftParenthese = false;
  private boolean rightParenthese = false;
  private boolean alreadyHasOperator = false;
  private boolean alreadyHasImaginary = false;
  private boolean startRunning = false;
  private boolean startNew = false;

  // calculator object used for doing calculations
  private calculator calc = new calculator();

  // ImgNumber objects used for a default "reset" value, as parameters for calculator methods,
  // holding the value of the current input, and holding the value of the running value
  private final ImgNumber initialValue = new ImgNumber(0, 0);
  private ImgNumber runningResult = initialValue;
  private ImgNumber currentOperand = initialValue;

  // String used for adding stuff to the history
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
    // JTextPane theDis = MainPanel.getDisplay();
    JTextPane theInput = MainPanel.getInput();
    String command = e.getActionCommand();

    String input = theInput.getText();

    String toParse;

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
        MainPanel.appendInput("(");
        MainPanel.displayError("The Expression Should Start With a '('");
        leftParenthese = true;
      }
      MainPanel.appendInput(command);
      previousButton = command;
    }
    catch (NumberFormatException nfe)
    {
      switch (command)
      {
        case "<":
          theHistoryWindow.close();
          HistoryWindow.makeNull();
          break;
        case ">":
          theHistoryWindow = HistoryWindow.getInstance();
          theHistoryWindow.setUndecorated(true);
          theHistoryWindow.addComps();
          theHistoryWindow.setSize(250, 350);
          theHistoryWindow.getContentPane().add(theHistoryWindow.getTheMain(), BorderLayout.CENTER);
          theHistoryWindow.placeFrame();
          theHistoryWindow.setVisible(true);
          break;
        case "=":
          if (!rightParenthese)
          {
            input += ")";
          }
          toParse = input.substring(input.lastIndexOf(previousOp));
          calc.addTo(Parser.parseSingleValue(toParse, command));
          MainPanel.appendInput(command);
          resetPartChecks();
          break;
        case "(":
          if (!leftParenthese)
          {
            MainPanel.appendInput("(");
          }
          leftParenthese = true;
          previousButton = command;
          break;
        case ")":
          if (!rightParenthese && leftParenthese)
          {
            MainPanel.appendInput(")");
          }
          previousButton = command;
          break;
        case ".":
          if (previousButton != ".")
          {
            MainPanel.appendInput(command);
          }
          previousButton = command;
          break;
        case "i":
          if (!alreadyHasImaginary)
          {
            MainPanel.appendInput("i");
          }
          previousButton = command;
          break;
        case "Inv":
          if (!input.contains(")"))
          {
            input += ")";
          }
          if (runningResult.equals(initialValue))
          {
            currentOperand = (ImgNumber) Parser.parseSingleValue(input, "+");
            MainPanel.setDisplay(theInput.getText() + " Inv to...");
            MainPanel.setInput(currentOperand.inverse().toString());
          }
          else
          {
            currentOperand = (ImgNumber) Parser.parseSingleValue(input, "+");
          }
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
          // This will have to be more complicated now that we know there has to be two lines, one
          // for the previous/running entry/result and one for where the user is entering the next
          // result
          MainPanel.clearInput();
          resetPartChecks();
          previousOp = "(";
          previousButton = command;
          break;
        case "R":
          MainPanel.clearDisplay();
          MainPanel.clearInput();
          resetInitialValues();
          resetPartChecks();
          startRunning = false;
          theHistory.reset();
          previousButton = command;
          break;
        default:
          // Making an int to use to check to see if simple or complex operator call, and, if
          // complex, to see if it needs to start a running calculation or not
          int checkRight = -1;
          if (!rightParenthese)
          {
            checkRight = 0;
          }
          else
          {
            if (!startRunning)
            {
              checkRight = 1;
            }
            else
            {
              checkRight = 2;
            }
          }
          // Begin switch which will determine simple operator call or complex call and determine of
          // running calculation will be made or not
          switch (checkRight)
          {
            // ***CASE WHERE THE USER IS JUST INPUTTING THE OPERATOR FOR THE COMPLEX NUMBER, NO
            // CALCULATIONS NEEDED, JUST PARSING***
            case (0):
              // Checks if the expression already has an operator
              if (!alreadyHasOperator) {
                // Breaks the switch if the user enters a * or / and tells them they must enter a plus
                // or minus
                if (!command.equals("+") || !command.equals("-"))
                {
                  MainPanel.displayError("Inside operator must be a + or -");
                  break;
                }
  
                // Adding a ) for parsing purposes
                input += ")";
  
                // Getting the substring for a single value and adding a ( for parsing purposes if
                // needed
                toParse = input.substring(input.lastIndexOf(previousOp));
                if (!toParse.contains("("))
                {
                  toParse = "(" + toParse;
                }
  
                // Parsing the single value and adding/subtracting it as a Real or Imaginary part to
                // the
                // depending on if it's a Real or ImgNumber
                Number parsed = Parser.parseSingleValue(toParse, command);
                if (parsed instanceof Real)
                {
                  if (command.equals("+"))
                  {
                    currentOperand.setReal(currentOperand.getReal() + parsed.getReal());
                  }
                  else
                  {
                    currentOperand.setReal(currentOperand.getReal() - parsed.getReal());
                  }
                }
                else
                {
                  if (command.equals("+"))
                  {
                    currentOperand.setImg(currentOperand.getImg() + ((ImgNumber) parsed).getImg());
                  }
                  else
                  {
                    currentOperand.setImg(currentOperand.getImg() - ((ImgNumber) parsed).getImg());
                  }
                }
                // Visually putting the operator in the input for the user and setting the boolean
                // check for an operator to be true so that they cannot add another operator
                MainPanel.appendInput(command);
                alreadyHasOperator = true;
              }
              break;
            case (1):
              break;
            case (2):
              break;
            default:
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

  /**
   * Checks the prev operation performed and .
   * 
   * @param secondNumber
   *          the case to switch
   * @return the complex number
   */
  private ImgNumber calculateBasedOnPreviousOperator(final ImgNumber secondNumber)
  {
    ImgNumber toReturn = null;

    switch (lastPerformed)
    {
      case "/":
        toReturn = (ImgNumber) calc.divide(runningResult, secondNumber);
        break;
      case "*":
        toReturn = (ImgNumber) calc.multiply(runningResult, secondNumber);
        break;
      case "-":
        toReturn = (ImgNumber) calc.subtract(runningResult, secondNumber);
        break;
      case "+":
        toReturn = (ImgNumber) calc.add(runningResult, secondNumber);
        break;
      default:
        toReturn = runningResult;
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
  private boolean noOpPress()
  {
    if (previousOp.equals("("))
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

  private void resetPartChecks()
  {
    leftParenthese = false;
    rightParenthese = false;
    alreadyHasOperator = false;
    alreadyHasImaginary = false;
  }

  private void resetInitialValues()
  {
    runningResult = initialValue;
    currentOperand = initialValue;
    previousOp = "(";
    lastPerformed = "n";
    startRunning = false;
  }

}
