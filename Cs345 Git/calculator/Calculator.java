package calculator;

import java.util.ArrayList;

/**
 * Calculator that can perform various operations on numbers that can be real or imaginary.
 * 
 * @author Chris Cleveland and Adrien Ponce
 * @version 4/12/21
 */
public class Calculator
{
  private ArrayList<Number> equation;

  /**
   * Default constructor.
   */
  public Calculator()
  {
    equation = new ArrayList<Number>();
  }

  /**
   * Creates an equation with the given parameter.
   * 
   * @param equation the equation to create
   */
  public Calculator(final ArrayList<Number> equation)
  {
    this.equation = equation;
  }

  /**
   * @return the equation of the calculator
   */
  public ArrayList<Number> getEquation()
  {
    return equation;
  }

  /**
   * Adds a number to the equation.
   * 
   * @param n the number to add
   */
  public void addTo(final Number n)
  {
    equation.add(n);
  }

  /**
   * Gets the x from the equation.
   * 
   * @param x the target to search for
   * @return the number if found
   */
  public Number get(final int x)
  {
    return equation.get(x);
  }

  /**
   * Adds two numbers together.
   * 
   * @param x this number
   * @param y other number
   * @return the sum
   */
  public Number add(final Number x, final Number y)
  {
    Number n;
    if (isReal(x) && isReal(y))
    {
      n = (((Real) x).add((Real) y));
    }
    else if (isImg(y) && isReal(x))
    {
      n = new ImgNumber((((Real) x).add(((ImgNumber) y).getRealObj())).getReal(),
          ((ImgNumber) y).getImg());
    }
    else if (isImg(x) && isReal(y))
    {
      n = new ImgNumber((((Real) y).add(((ImgNumber) x).getRealObj())).getReal(),
          ((ImgNumber) x).getImg());
    }
    else
    {
      n = ((ImgNumber) x).add(((ImgNumber) y));
    }
    n.setOperator(Operator.ADD);
    return n;
  }

  /**
   * Subtracts y from x.
   * 
   * @param x this number
   * @param y other number
   * @return the difference
   */
  public Number subtract(final Number x, final Number y)
  {
    Number n;
    if (isReal(x) && isReal(y))
    {
      n = (((Real) x).subtract((Real) y));
    }
    else if (isImg(y) && isReal(x))
    {
      n = new ImgNumber((((Real) x).subtract(((ImgNumber) y).getRealObj())).getReal(),
          ((ImgNumber) y).getImg());
    }
    else if (isImg(x) && isReal(y))
    {
      n = new ImgNumber(x.getReal() - y.getReal(), ((ImgNumber) x).getImg());
    }
    else
    {
      n = ((ImgNumber) x).subtract(((ImgNumber) y));
    }
    return n;
  }

  /**
   * Multiplies x and y.
   * 
   * @param x this number
   * @param y other number
   * @return the product
   */
  public Number multiply(final Number x, final Number y)
  {
    Number n;
    if (isReal(x) && isReal(y))
    {
      n = (((Real) x).multiply((Real) y));
    }
    else if (isImg(y) && isReal(x))
    {
      n = new ImgNumber((((Real) x).multiply(((ImgNumber) y).getRealObj())).getReal(),
          ((ImgNumber) y).getImg() * x.getReal());
    }
    else if (isImg(x) && isReal(y))
    {
      n = new ImgNumber((((Real) y).multiply(((ImgNumber) x).getRealObj())).getReal(),
          ((ImgNumber) x).getImg() * y.getReal());
    }
    else
    {
      n = ((ImgNumber) x).multiply(((ImgNumber) y));
    }
    return n;
  }

  /**
   * Divides y from x.
   * 
   * @param x this number
   * @param y other number
   * @return the quotient
   */
  public Number divide(final Number x, final Number y)
  {
    Number n;
    if (isReal(x) && isReal(y))
    {
      n = (((Real) x).multiply((Real) y));
    }
    else if (isImg(y) && isReal(x))
    {
      n = new ImgNumber((((Real) x).divide(((ImgNumber) y).getRealObj())).getReal(),
          x.getReal()/((ImgNumber) y).getImg()  );
    }
    else if (isImg(x) && isReal(y))
    {
      n = new ImgNumber(x.getReal() / y.getReal(),((ImgNumber) x).getImg()/ y.getReal()); 
    }
    else
    {
      n = (((ImgNumber) x).divide(((ImgNumber) y)));
    }
    return n;
  }

  /**
   * Checks if x is a real number.
   * 
   * @param x the number in question
   * @return true if real
   */
  private boolean isReal(final Number x)
  {
    boolean test = false;
    if (x instanceof Real)
    {
      test = true;
    }
    return test;
  }

  /**
   * Checks if x is imaginary.
   * 
   * @param x the number in question
   * @return true if imaginary
   */
  private boolean isImg(final Number x)
  {
    boolean test = false;
    if (x instanceof ImgNumber)
    {
      test = true;
    }
    return test;
  }

  /**
   * @return a String representation of the expression
   */
  public String toString()
  {
    String str = "";
    String empty = " ";
    for (int x = 0; x < equation.size(); x++)
    {
      str += "(";
      str += equation.get(x).toString();
      str += ")";
      if (x + 1 != equation.size() && equation.size() > 1)
      {
        str += empty;
        str += equation.get(x).getOperator().getOperator();
        str += empty;
      }

    }
    return str;
  }
}