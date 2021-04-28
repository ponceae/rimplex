package calculator;

/**
 * This class represents a real number.
 * 
 * @author Chris Cleveland, Adrien Ponce
 * @version 4/12/21
 */
public class Real implements Number
{
  private double real;
  private Operator op;

  /**
   * Creates a real number with a double value.
   * 
   * @param real
   *          the real number
   */
  public Real(final double real)
  {
    this.real = real;
    op = Operator.ADD;
  }

  /**
   * Creates a real number with a double value and an operator to the left of it.
   * 
   * @param real
   *          the real number
   * @param op
   *          the operator on the number
   */
  public Real(final double real, final Operator op)
  {
    this.real = real;
    this.op = op;
  }

  /**
   * Adds other to this real number. (this + other).
   * 
   * @param other
   *          the other real number
   * @return the sum
   */
  public Real add(final Real other)
  {
    Real n = new Real(this.real + other.getReal());
    return n;
  }

  /**
   * Subtracts other from this real number. (this - other).
   * 
   * @param other
   *          the other real number
   * @return the difference
   */
  public Real subtract(final Real other)
  {

    return new Real(this.real - other.getReal());

  }

  /**
   * Multiplies other to this real number. (this * other).
   * 
   * @param other
   *          the other real number
   * @return the product
   */
  public Real multiply(final Real other)
  {
    double test = other.getReal();
    Real n;

    if (test == 0.0 || real == 0.0)
    {
      n = new Real(0.000);
    }
    else
    {
      n = new Real(this.real * other.getReal());
    }
    return n;
  }

  /**
   * Divides other from this real number. (real / other).
   * 
   * @param other
   *          the other real number
   * @return the quotient
   */
  public Real divide(final Real other)
  {
    Real n = new Real(this.real / other.getReal());
    return n;
  }

  /**
   * Exponentially increases this real number.
   * 
   * @param x
   *          the exponent
   * @return the exponential product
   */
  public Real squared(final int x)
  {
    int i = 1;
    double val = real;
    Real n;
    while (i < x)
    {
      val *= real;
      i++;
    }
    n = new Real(val);
    return n;
  }

  /**
   * @return this real number
   */
  public double getReal()
  {
    return real;
  }

  @Override
  public void setReal(final double d)
  {
    real = d;
  }

  @Override
  public Operator getOperator()
  {
    return op;
  }

  @Override
  public void setOperator(final Operator o)
  {
    op = o;
  }
  
  /**
   * Swaps the sign of this real number.
   */
  public void swapSign() 
  {
    real = real * -1;
  }

  /**
   * @return a String representation of a real number
   */
  public String toString()
  {
    String str = "";
    str += real;
    return str;
  }

}
