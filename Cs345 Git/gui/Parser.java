package gui;

import calculator.Number;
import calculator.Real;
import calculator.ImgNumber;
import calculator.Operator;

public class Parser
{
  /**
   * Parse a single value.
   * 
   * @param value
   *          the value to parse
   * @return the parsed value
   */
  static ImgNumber parseWholeValue(final String value, final String operator)
  { 
    boolean wholeNegative = false;
    Operator op = Operator.getFrom(operator);
    String[] allParts = new String[2];
    // ***CASES WHERE THERE IS A POSSIBLE NEGATIVE OR MINUS***
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
            displayError(MouseList.getLanguage());
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
    // ***CASES WHERE THERE IS NO POSSIBLE NEGATIVE OR MINUS***
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
          displayError(MouseList.getLanguage());
        }
        else
        {
          System.out.println(value.toString());
          allParts[0] = value.substring(1, value.indexOf("/"));
          allParts[1] = value.substring(value.indexOf("/") + 1, value.length() - 2);
          // op = Operator.getFrom("/");
        }
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
    Number imgNum = new ImgNumber(real, img, op);

    return (ImgNumber) imgNum;
  }

  static Number parseSingleValue(final String value, final String operator)
  {
    boolean wholeNegative = false;
    Operator op = Operator.getFrom(operator);
    String[] allParts = new String[2];

    // ***CASES FOR WHERE THERE IS ONLY A SINGLE VALUE OR NO VALUE (i.e. no operator)***
    if (!containsNumber(value))
    {
      if (containsImaginary(value))
      {
        return new ImgNumber(0, 1);
      }
      else
      {
        return new ImgNumber(0, 0);
      }
    }
    else if (!containsOperator(value) && containsNumber(value))
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
    // ***CASES WHERE THERE IS AN OPERATOR, BUT THERE'S ONLY REALS OR IMAGINARIES***
    else if (containsOperator(value) && !containsImaginary(value))
    {

    }
    else if (containsOperator(value) && containsImaginary(value))
    {

    }

    return null;
  }

  private static boolean containsOperator(String input)
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

  private static boolean containsNumber(String text)
  {
    return (text.contains("1")) || (text.contains("2")) || (text.contains("3"))
        || (text.contains("4")) || (text.contains("5")) || (text.contains("6"))
        || (text.contains("7")) || (text.contains("8")) || (text.contains("9"))
        || (text.contains("0"));
  }
  
  static boolean containsImaginary(String input)
  {
    return input.contains("i");
  }
  
  static boolean oneValue(String text) {
    return !containsOperator(text);
  }
  
  private static void displayError(int languageCode) {
    switch(languageCode) {
      case 1:
        PopUp.errorBox(Language.ENGLISH_DIVIDE_BY_ZERO.getToken());
      case 2:
        PopUp.errorBox(Language.FRENCH_DIVIDE_BY_ZERO.getToken());
      case 3:
        PopUp.errorBox(Language.SPANISH_DIVIDE_BY_ZERO.getToken());
    }
    
  }
}
