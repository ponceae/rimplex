package MathComp;

/**
 * Class to create a complex value to be stored in a complex number.
 * 
 * @author Chris Cleveland & Adrien Ponce
 * @version v2
 */
public class ComplexValue
{
  private int real; // the real value
  private int imaginary; // the imaginary value
  private Operator operator; // the operation being performed (+, -, *, /)
  private String variable; // the variable in the real number (i.e. x in 2x)
  private int exponent; // the exponent (if applicable)

  /**
   * Default Constructor. No values set. NOTE: Exponent set to one as to not break the real value of
   * 0 (i.e. 0^0 = undefined & 0^1 = 0).
   */
  public ComplexValue()
  {
    this(0, null, 1, Operator.EMPTY, 0);
  }

  /**
   * Creates a ComplexValue with explicitly set values.
   * 
   * @param real
   *          the real part
   * @param variable
   *          the variable for the real number
   * @param imaginary
   *          the complex value
   * @param operator
   *          the operation being performed
   * @param exponent
   *          the exponent
   */
  public ComplexValue(final int real, final String variable, final int exponent,
      final Operator operator, final int imaginary)
  {
    this.real = real;
    this.variable = variable;
    this.imaginary = imaginary;
    this.operator = operator;
    this.exponent = exponent;
  }

  /**
   * @return the real value
   */
  public int getReal()
  {
    return real;
  }

  /**
   * @return the exponent value (if it exists)
   */
  public int getExponent()
  {
    return exponent;
  }

  /**
   * @return the operator in the expression
   */
  public Operator getOperator()
  {
    return operator;
  }

  /**
   * @return the variable in the expression (if it exists)
   */
  public String getVariable()
  {
    return variable;
  }

  /**
   * @return the imaginary value
   */
  public int getImaginary()
  {
    return imaginary;
  }

  /**
   * Sets the real value in the expression.
   * 
   * @param value
   *          the real part that is replacing this
   */
  public void setReal(final int value)
  {
    this.real = value;
  }

  /**
   * Sets the exponent part in the expression.
   * 
   * @param value
   *          the exponent that is replacing this
   */
  public void setExponent(final int value)
  {
    this.exponent = value;
  }

  /**
   * Sets the operation in the expression.
   * 
   * @param value
   *          the operation that is replacing this
   */
  public void setOperator(final Operator value)
  {
    this.operator = value;
  }

  /**
   * Sets the imaginary part in the expression.
   * 
   * @param value
   *          the imaginary value that is replacing this
   */
  public void setImaginary(final int value)
  {
    this.imaginary = value;
  }

  /**
   * Sets the variable for the real number.
   * 
   * @param value
   *          the variable that is replacing this
   */
  public void setVariable(final String value)
  {
    this.variable = value;
  }

  /**
   * Helper method that copies this values so that they stay immutable.
   * 
   * @return the copy of this ComplexValue
   */
  private ComplexValue deepCopy()
  {
    return new ComplexValue(real, variable, exponent, operator, imaginary);
  }

  /**
   * Modify this to return a new ComplexValue that is (this + other).
   * 
   * @param other
   *          the value that is being added to this
   * @return a new ComplexValue that is a sum of the values
   */
  public ComplexValue add(final ComplexValue other)
  {
    // to avoid null pointer exception
    ComplexValue ans = deepCopy();
    if (ans.variable == other.variable)
    {
      ans.setReal(ans.real + other.real);

      // Logic in the block below is debatable
      //////////////////////////////////////////////////////
      if ((imaginary * other.imaginary) <= 0)
      {
        ans.setImaginary(ans.imaginary + other.imaginary);
        ans.setOperator(Operator.SUBTRACT);
      }
      else
      {
        ans.setImaginary(ans.imaginary - other.imaginary);
        ans.setOperator(Operator.ADD);
      }
      //////////////////////////////////////////////////////
      if (ans.real == 0)
      {
        ans.setVariable(null);
      }
    }
    return ans;
  }

  /**
   * Modify this to return a new ComplexValue that is (this - other).
   * 
   * @param other
   *          the value that is being subtracted from this
   * @return a new ComplexValue that is a difference of the values
   */
  public ComplexValue subtract(final ComplexValue other)
  {
    // to avoid null pointer exception
    ComplexValue ans = deepCopy();
    if (ans.variable == other.variable)
    {
      ans.setReal(ans.real - other.real);

      // Logic in the block below is debatable (copied from above and swapped)
      //////////////////////////////////////////////////////
      if ((imaginary * other.imaginary) <= 0)
      {
        ans.setImaginary(ans.imaginary - other.imaginary);
        ans.setOperator(Operator.ADD);
      }
      else
      {
        ans.setImaginary(ans.imaginary + other.imaginary);
        ans.setOperator(Operator.SUBTRACT);
      }
      //////////////////////////////////////////////////////
      if (ans.real == 0)
      {
        ans.setVariable(null);
      }
    }
    return ans;
  }

  /**
   * Modify this to return a new ComplexValue that is (this * other).
   * 
   * @param other
   *          the value that is being multiplied to this
   * @return a new ComplexValue that is a product of the values
   */
  public ComplexValue multiply(final ComplexValue other)
  {
    ComplexValue ans = deepCopy();

    if (exponent == 1)
    {
      // do normal multiplication
    }
    else
    {
      // special case exponent multiplication
    }
    return ans;
  }

  /**
   * Helper method to check if a ComplexValue has no value.
   * 
   * @return true if all values are zero
   */
  private boolean hasNoValue()
  {
    if (real == 0 && imaginary == 0)
    {
      return true;
    }
    return false;
  }

  /**
   * @returns a String encapsulation of a ComplexValue.
   */
  @Override
  public String toString()
  {
    String space = " ";
    String result = "";
    String imgChar = "i";

    // empty ComplexValue
    if (hasNoValue())
    {
      return Integer.toString(0);
    }
    result += '(' + real;

    // no variables present
    if (variable != null)
    {
      result += variable;
    }
    // show exponent
    if (exponent > 1)
    {
      result += "^" + exponent + space + operator.getOperator() + space;
    } // do not show exponent
    else
    {
      result += space + operator.getOperator() + space;
    }

    // handle negative imaginary
    if (imaginary < 0)
    {
      result += -imaginary + imgChar + ')';
    } // normal imaginary
    else if (imaginary != 0)
    {
      result += imaginary + imgChar + ')';
    }
    else
    {
      result += ')';
    }
    return result;
  }
}
