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
  private static Listener listener;
  private static final String LEFT_PAREN = "(";
  private static final String RIGHT_PAREN = ")";
  private static final String PERIOD = ".";
  private static final String MINUS = "-";
  private static final String NEWLINE = "\n";
  private static final String PLUS = "+";
  private static final String EQUAL = "=";
  private static final String SLASH = "/";

  private static final String MULT  = "*";
  
  private static String empty = " ";
  private static String inv = " Inv to... ";

  private HistoryWindow theHistoryWindow;
  private History theHistory = History.getInstance();

  private String previousOp = LEFT_PAREN;
  private String lastPerformed = "n";
  private String previousButton = lastPerformed;

  // Booleans for checking if a button press is allowed or what happens in certain cases
  private boolean leftParenthese = false;
  private boolean rightParenthese = false;
  private boolean alreadyHasOperator = false;
  private boolean alreadyHasImaginary = false;
  private boolean startRunning = false;
  private boolean isNegative = false;
  private boolean rightParenParse = false;
  // private boolean startNew = false;

  // calculator object used for doing calculations
  private Calculator calc = new Calculator();

  // ImgNumber objects used for a default "reset" value, as parameters for calculator methods,
  // holding the value of the current input, and holding the value of the running value
  // private final ImgNumber initialValue = new ImgNumber(0, 0);
  private ImgNumber runningResult = initialValue();
  private ImgNumber currentOperand = initialValue();

  private Number parsed;
  private String toParse;

  // Doubles used to represent the first and/or second parts of the current operand, mostly used for
  // when the user backspaces and a value needs to be reset for parsing
  private double firstPart = 0.0;
  private double secondPart = 0.0;

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

    // String toParse;

    try
    {
      @SuppressWarnings("unused")
      int number = Integer.parseInt(command);
      /**
       * if (startRunning && previousPressOperator() && startNew) { MainPanel.clearDisplay();
       * startNew = false; }
       */
      if (!leftParenthese)
      {
        MainPanel.appendInput(LEFT_PAREN);
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
        case EQUAL:
          // Add a ) if there isn't already one for parsing purposes and take the substring to get
          // the value in String form for parsing
          if (!rightParenthese)
          {
            input += RIGHT_PAREN;
          }
          toParse = input.substring(input.lastIndexOf(previousOp) + 1);

          if (!rightParenParse)
          {
            // previousOp = LEFT_PAREN;
            // parsed = Parser.parseSingleValue(toParse, previousOp);
            setCurrentOperand(previousOp);
          }

          // Add a ( if there isn't already one for parsing purposes and then parse it
          if (!toParse.contains(LEFT_PAREN))
          {
            toParse = LEFT_PAREN + toParse;
          }
          // parsed = Parser.parseSingleValue(toParse, previousOp);
          // setCurrentOperand(previousOp);

          if (lastPerformed.equals(SLASH) && isZero(currentOperand))
          {
            // Divide by zero exception
            PopUp.errorBox(Language.getDialog(Language.DIVIDE_BY_ZERO));
            break;
          }

          if (isNegative)
          {
            setNegative();
          }

          theHistory.add(LEFT_PAREN + currentOperand.toString() + ") = ");
          runningResult = calculateBasedOnPreviousOperator(currentOperand);
          theHistory.add(LEFT_PAREN + runningResult.toString() + ")\n");

          MainPanel.setDisplay(" = " + runningResult.toString());
          MainPanel.clearInput();

          resetPartChecks();
          currentOperand = initialValue();
          startRunning = true;
          previousButton = command;
          previousOp = LEFT_PAREN;
          break;
        case LEFT_PAREN:
          if (!leftParenthese)
          {
            MainPanel.appendInput(LEFT_PAREN);
          }
          leftParenthese = true;
          previousButton = command;
          break;
        case RIGHT_PAREN:
          if (!rightParenthese && leftParenthese)
          {
            MainPanel.appendInput(RIGHT_PAREN);
            rightParenthese = true;
          }
          toParse = input.substring(input.indexOf(previousOp) + 1);
          toParse = LEFT_PAREN + toParse;

          if (!toParse.contains(RIGHT_PAREN))
          {
            toParse += RIGHT_PAREN;
          }

          // previousOp = LEFT_PAREN;
          // parsed = Parser.parseSingleValue(toParse, previousOp);
          setCurrentOperand(previousOp);

          rightParenParse = true;
          previousButton = command;
          break;
        case PERIOD:
          if (previousButton != PERIOD)
          {
            MainPanel.appendInput(command);
          }
          previousButton = command;
          break;
        case "<html><i>i</i></html>":
          if (!alreadyHasImaginary)
          {
            MainPanel.appendInput("i");
            alreadyHasImaginary = true;
          }
          previousButton = command;
          break;
        case "Inv":
          if (!input.contains(RIGHT_PAREN))
          {
            input += RIGHT_PAREN;
          }
          if (isZero(runningResult))
          {
            theHistory.add(currentOperand.toString() + inv);
            runningResult = currentOperand.inverse();
            theHistory.add(runningResult.toString() + NEWLINE);
            MainPanel
                .setDisplay(currentOperand.toString() + " Inv to..." + runningResult.toString());
            // MainPanel.setInput(runningResult.toString());
          }
          else
          {
            if (!MainPanel.getDisplay().getText().contains(EQUAL))
            {
              runningResult = calculateBasedOnPreviousOperator(currentOperand);
              theHistory.add(NEWLINE + runningResult.toString() + inv);
              MainPanel.setDisplay(runningResult.toString() + inv);
              runningResult = runningResult.inverse();
              MainPanel.appendDisplay(runningResult.toString());
              theHistory.add(runningResult.toString() + NEWLINE);
            }
            else
            {
              theHistory.add(NEWLINE + runningResult.toString() + inv);
              MainPanel.setDisplay(runningResult.toString() + inv);
              runningResult = runningResult.inverse();
              MainPanel.appendDisplay(runningResult.toString());
              theHistory.add(runningResult.toString() + NEWLINE);
            }
          }
          resetPartChecks();
          currentOperand = initialValue();
          startRunning = true;
          previousOp = LEFT_PAREN;
          lastPerformed = previousButton;
          previousButton = command;
          break;
        case "\u00B1":
          if (!noInput())
          {
            // currentOperand.swapSignImg();
            // currentOperand.swapsignReal();
            if (!isNegative)
            {
              isNegative = true;
            }
            else
            {
              isNegative = false;
            }
            MainPanel.toggleSign();
          }
          break;
        case "\u232B":
          char removed = MainPanel.backspace();
          if (isOperator(removed))
          {
            alreadyHasOperator = false;
            currentOperand.setReal(currentOperand.getReal() - firstPart);
            previousOp = LEFT_PAREN;
          }
          else if (removed == '(')
          {
            leftParenthese = false;
          }
          else if (removed == ')')
          {
            rightParenthese = false;
            currentOperand.setImg(currentOperand.getImg() - secondPart);
          }
          else if (removed == 'i')
          {
            alreadyHasImaginary = false;
          }
          if (MainPanel.getInput().getText().isEmpty())
          {
            currentOperand = initialValue();
            resetPartChecks();
            previousOp = LEFT_PAREN;
          }
          previousButton = command;
          break;
        case "C":
          // This will have to be more complicated now that we know there has to be two lines, one
          // for the previous/running entry/result and one for where the user is entering the next
          // result
          MainPanel.clearInput();
          resetPartChecks();
          previousOp = LEFT_PAREN;
          previousButton = command;
          currentOperand = initialValue();
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
            if (!MainPanel.getInput().getText().isEmpty())
            {
              checkRight = 0;
            }
            else
            {
              MainPanel.appendDisplay(empty + command);
              theHistory.add(LEFT_PAREN + runningResult.toString() + RIGHT_PAREN + empty + command);
              lastPerformed = command;
            }
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
              // Checks if the expression already has an operator so that they can't add another
              if (!alreadyHasOperator)
              {
                // Breaks the switch if the user enters a * or / and tells them they must enter a
                // plus or minus
                if (!command.equals(PLUS) && !command.equals(MINUS))
                {
                  PopUp.errorBox(Language.getDialog(Language.IMPROPER_OPERAND));
                  break;
                }

                // Adding a ) for parsing purposes
                input += RIGHT_PAREN;

                // Getting the substring for a single value and adding a ( for parsing purposes if
                // needed
                toParse = input.substring(input.lastIndexOf(previousOp));
                if (!toParse.contains(LEFT_PAREN))
                {
                  toParse = LEFT_PAREN + toParse;
                }

                // Parsing the single value and adding/subtracting it as a Real or Imaginary part to
                // the currentOperand depending on if it's a Real or ImgNumber
                // parsed = Parser.parseSingleValue(toParse, command);
                setCurrentOperand(command);
                firstPart = currentOperand.getReal();

                // Visually putting the operator in the input for the user and setting the boolean
                // check for an operator to be true so that they cannot add another operator
                MainPanel.appendInput(command);
                alreadyHasOperator = true;
                previousButton = command;
                previousOp = command;
              }
              break;
            case (1):
              if (isNegative)
              {
                setNegative();
              }
              lastPerformed = command;
              MainPanel.setDisplay(input + empty + command);
              MainPanel.clearInput();
              setRunningResult(currentOperand);

              theHistory.add(LEFT_PAREN + runningResult.toString() + ") " + command + empty);

              resetPartChecks();
              previousOp = LEFT_PAREN;
              previousButton = command;
              currentOperand = initialValue();
              startRunning = true;
              // isNegative = false;
              break;
            case (2):

              if (lastPerformed.equals(SLASH) && isZero(currentOperand))
              {
                // Divide by zero exception
                PopUp.errorBox(Language.getDialog(Language.DIVIDE_BY_ZERO));
                break;
              }

              if (isNegative)
              {
                setNegative();
              }
              runningResult = calculateBasedOnPreviousOperator(currentOperand);
              theHistory.add(LEFT_PAREN + runningResult.toString() + RIGHT_PAREN + command + empty);
              lastPerformed = command;
              MainPanel.setDisplay(runningResult.toString() + empty + command);
              MainPanel.clearInput();
              resetPartChecks();
              previousOp = LEFT_PAREN;
              previousButton = command;
              currentOperand = initialValue();
              // isNegative = false;
              break;
            default:
              // PopUp.infoBox("Starting running result", "");
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
      case SLASH:
        toReturn = (ImgNumber) calc.divide(runningResult, secondNumber);
        break;
      case MULT:
        toReturn = (ImgNumber) calc.multiply(runningResult, secondNumber);
        break;
      case MINUS:
        toReturn = (ImgNumber) calc.subtract(runningResult, secondNumber);
        break;
      case PLUS:
        toReturn = (ImgNumber) calc.add(runningResult, secondNumber);
        break;
      default:
        toReturn = secondNumber;
    }
    return toReturn;
  }

  /**
   * a.
   * 
   * @return a
   */
  private boolean noInput()
  {
    return MainPanel.getDisplay().getText().isEmpty();
  }

  /**
   * 
   * @return
   *
   *         private boolean noOpPress() { if (previousOp.equals(LEFT_PAREN)) { return true; } else
   *         { return false; } }
   */

  /**
   * 
   * @return
   *
   *         private boolean previousPressOperator() { return (previousButton == PLUS) ||
   *         (previousButton == MINUS) || (previousButton == SLASH) || (previousButton == mult) ||
   *         (previousButton == EQUAL) || (previousButton == "Inv"); }
   * 
   *         private boolean previousPressOperatorMinusEquals() { return (previousButton == PLUS) ||
   *         (previousButton == MINUS) || (previousButton == SLASH) || (previousButton == mult) ||
   *         (previousButton == "Inv"); }
   */

  /**
   * a.
   * 
   * @param toCompare
   *          a
   * @return a
   */
  private boolean isOperator(final char toCompare)
  {
    return (toCompare == '+') || (toCompare == '-') || (toCompare == '/') || (toCompare == '*');
  }

  private void resetPartChecks()
  {
    leftParenthese = false;
    rightParenthese = false;
    alreadyHasOperator = false;
    alreadyHasImaginary = false;
    rightParenParse = false;
    isNegative = false;
  }

  private void resetInitialValues()
  {
    runningResult = initialValue();
    currentOperand = initialValue();
    previousOp = LEFT_PAREN;
    lastPerformed = previousButton;
    startRunning = false;
    // isNegative = false;
    firstPart = 0.0;
    secondPart = 0.0;
  }

  private void setCurrentOperand(final String theCommand)
  {
    parsed = Parser.parseSingleValue(toParse, theCommand);
    if (parsed instanceof Real)
    {
      if (previousOp.equals(MINUS))
      {
        currentOperand.setReal(currentOperand.getReal() - parsed.getReal());
      }
      else
      {
        currentOperand.setReal(currentOperand.getReal() + parsed.getReal());
      }
    }
    else
    {
      if (previousOp.equals(MINUS))
      {
        currentOperand.setImg(currentOperand.getImg() - ((ImgNumber) parsed).getImg());
      }
      else
      {
        currentOperand.setImg(currentOperand.getImg() + ((ImgNumber) parsed).getImg());
      }
    }
  }

  private void setNegative()
  {
    currentOperand.swapSignImg();
    currentOperand.swapsignReal();
  }

  private void setRunningResult(final ImgNumber theNumber)
  {
    runningResult.setImg(theNumber.getImg());
    runningResult.setReal(theNumber.getReal());
  }

  private ImgNumber initialValue()
  {
    return new ImgNumber(0, 0);
  }

  private boolean isZero(final ImgNumber number)
  {
    return number.getImg() == 0.0 && number.getReal() == 0.0;
  }

}
