package gui;

import calculator.Number;
import calculator.Real;
import calculator.ImgNumber;
import calculator.Operator;

/**
 * Parser class.
 * 
 * @author u
 * @version 4/28
 */
public class Parser
{
  private static String plus = "+";
  private static String minus = "-";
  private static String divide = "/";
  private static String mult = "*";
  private static String zero = "0";
  private static String leftParen = "(";
  private static String rightParen = ")";
  private static String i = "i";

  /**
   * Parse a single value.
   * 
   * @param value
   *          the value to parse
   * @param operator
   *          the operator
   * @return the parsed value
   */
  static ImgNumber parseWholeValue(final String value, final String operator)
  {
    boolean wholeNegative = false;
    Operator op = Operator.getFrom(operator);
    String[] allParts = new String[2];
    // ***CASES WHERE THERE IS A POSSIBLE NEGATIVE OR MINUS***
    if (value.contains(minus))
    {
      if (value.charAt(0) == '-')
      {
        if (value.contains(plus))
        {
          allParts[0] = value.substring(2, value.indexOf(plus));
          allParts[1] = value.substring(value.indexOf(plus) + 1, value.length() - 2);
          // op = Operator.getFrom(plus);
        }
        else if (value.contains(mult))
        {
          allParts[0] = value.substring(2, value.indexOf(mult));
          allParts[1] = value.substring(value.indexOf(mult) + 1, value.length() - 2);
          // op = Operator.getFrom(mult);
        }
        else if (value.contains(divide))
        {
          if (value.charAt(value.indexOf(divide) + 1) == '0'
              && value.substring(0, value.indexOf(divide) + 4).length() == value.length())
          {
            PopUp.errorBox(Language.getDialog(Language.DIVIDE_BY_ZERO));
          }
          else
          {
            allParts[0] = value.substring(2, value.indexOf(divide));
            allParts[1] = value.substring(value.indexOf(divide) + 1, value.length() - 2);
            // op = Operator.getFrom(divide);
          }
        }
        else
        {
          allParts[0] = value.substring(2, value.indexOf(minus));
          allParts[1] = value.substring(value.indexOf(minus), value.length() - 2);
          // op = Operator.getFrom(minus);
        }
        wholeNegative = true;
      }
      else
      {
        allParts[0] = value.substring(1, value.lastIndexOf(minus));
        allParts[1] = value.substring(value.lastIndexOf(minus), value.length() - 2);
        // op = Operator.getFrom(minus);
      }
    }
    // ***CASES WHERE THERE IS NO POSSIBLE NEGATIVE OR MINUS***
    else
    {
      if (value.contains(plus))
      {
        allParts[0] = value.substring(1, value.indexOf(plus));
        allParts[1] = value.substring(value.indexOf(plus) + 1, value.length() - 2);
        // op = Operator.getFrom(plus);
      }
      else if (value.contains(mult))
      {
        allParts[0] = value.substring(1, value.indexOf(mult));
        allParts[1] = value.substring(value.indexOf(mult) + 1, value.length() - 2);
        // op = Operator.getFrom(mult);
      }
      else if (value.contains(divide))
      {
        if (value.charAt(value.indexOf(divide) + 1) == '0'
            && value.substring(0, value.indexOf(divide) + 4).length() == value.length())
        {
          PopUp.errorBox(Language.getDialog(Language.DIVIDE_BY_ZERO));
        }
        else
        {
          System.out.println(value.toString());
          allParts[0] = value.substring(1, value.indexOf(divide));
          allParts[1] = value.substring(value.indexOf(divide) + 1, value.length() - 2);
          // op = Operator.getFrom(divide);
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
      if (!part1.equals(zero))
      {
        real = -Double.parseDouble(allParts[0]);
      }
      else
      {
        real = 0.0;
      }
      if (!part2.equals(zero))
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
      if (!part1.equals(zero))
      {
        real = Double.parseDouble(allParts[0]);
      }
      else
      {
        real = 0.0;
      }
      if (!part2.equals(zero))
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
    Number toReturn;

    // ***CASES FOR WHERE THERE IS ONLY A SINGLE VALUE OR NO VALUE (i.e. no operator)***
    if (!containsNumber(value))
    {
      if (containsImaginary(value))
      {
        return new ImgNumber(0, 1, op);
      }
      else
      {
        return new Real(0, op);
      }
    }
    
    else
    {
      String doubZe = "0.0";
      if (containsImaginary(value))
      {
        allParts[0] = doubZe;
        allParts[1] = value.substring(value.indexOf(leftParen) + 1, value.indexOf(i));
      }
      else
      {
        allParts[0] = value.substring(value.indexOf(leftParen) + 1, value.indexOf(rightParen));
        allParts[1] = doubZe;
      }
    }
    /**
     * // ***CASES WHERE THERE IS AN OPERATOR, BUT THERE'S ONLY REALS OR IMAGINARIES*** else if
     * (containsOperator(value) && !containsImaginary(value)) { } else if (containsOperator(value)
     * && containsImaginary(value)) { }
     */

    if (allParts[0].contains(leftParen))
    {
      allParts[0] = allParts[0].substring(1);
    }

    if (allParts[1].contains(rightParen))
    {
      allParts[1] = allParts[1].substring(0, allParts[1].length() - 1);
    }

    double real = Double.parseDouble(allParts[0]);
    double img = Double.parseDouble(allParts[1]);

    if (wholeNegative)
    {
      real = -real;
      img = -img;
    }

    if (containsImaginary(value))
    {
      toReturn = new ImgNumber(real, img, op);
    }
    else
    {
      toReturn = new Real(real, op);
    }

    return toReturn;
  }

  private static boolean containsOperator(final String input)
  {
    boolean containsMinus = false;
    if (input.contains(minus))
    {
      if (input.lastIndexOf('-') != 0)
      {
        containsMinus = true;
      }
    }
    return (input.contains(plus)) || containsMinus || (input.contains(divide))
        || (input.contains(mult));
  }

  private static boolean containsNumber(final String text)
  {
    return (text.contains("1")) || (text.contains("2")) || (text.contains("3"))
        || (text.contains("4")) || (text.contains("5")) || (text.contains("6"))
        || (text.contains("7")) || (text.contains("8")) || (text.contains("9"))
        || (text.contains(zero));
  }

  static boolean containsImaginary(final String input)
  {
    return input.contains(i);
  }

  static boolean oneValue(final String text)
  {
    return !containsOperator(text);
  }

}
