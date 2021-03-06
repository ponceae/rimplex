package calculator;

/**
 * This class represents a complex value with a real and imaginary value.
 * 
 * @author Chris Cleveland, Adrien Ponce
 * @version 4/12/21
 */
public class ImgNumber implements Number
{
  private double img;
  private Real real;
  private Operator operator;

  /**
   * Explicit value constructor.
   * 
   * @param real
   *          the real number value
   * @param img
   *          the imaginary number value
   * @param operator
   *          the operator in the expression
   */
  public ImgNumber(final double real, final double img, final Operator operator)
  {
    this.real = new Real(real);
    this.img = img;
    this.operator = operator;
  }

  /**
   * Explicit value constructor.
   * 
   * @param real
   *          the real number value
   * @param img
   *          the imaginary number value
   */
  public ImgNumber(final double real, final double img)
  {
    this.real = new Real(real);
    this.img = img;
  }

  /**
   * @return the real part of the expression.
   */
  public double getReal()
  {
    return real.getReal();
  }

  /**
   * @return the imaginary part of the expression.
   */
  public double getImg()
  {
    return img;
  }

  /**
   * @return the real part of the expression
   */
  public Real getRealObj()
  {
    return real;
  }

  /**
   * @return the operator in the expression
   */
  public Operator getOperator()
  {
    return operator;
  }

  /**
   * Adds two complex numbers together. (this + other).
   * 
   * @param other
   *          the other complex number to add
   * @return the newly updated complex number
   */
  public ImgNumber add(final ImgNumber other)
  {
    double newReal = this.getReal() + other.getReal();
    double newImg = this.img + other.getImg();
    return new ImgNumber(newReal, newImg);
  }

  /**
   * Subtracts a complex numbers from this. (this - other).
   * 
   * @param other
   *          the other complex number to subtract
   * @return the newly updated complex number
   */
  public ImgNumber subtract(final ImgNumber other)
  {
    return new ImgNumber(getReal() - other.real.getReal(), img - other.img);
  }

  /**
   * Multiplies two complex numbers together. (this * other).
   * 
   * @param other
   *          the other complex number to multiply
   * @return the newly updated complex number
   */
  public ImgNumber multiply(final ImgNumber other)
  {
    double newReal = getReal() * other.getReal() - this.img * other.getImg();
    double newImg = getReal() * other.getImg() + this.img * other.getReal();
    return new ImgNumber(newReal, newImg);
  }

  /**
   * Divides a complex numbers from this. (this / other).
   * 
   * @param other
   *          the other complex number to divide
   * @return the newly updated complex number
   */
  public ImgNumber divide(final ImgNumber other)
  {
    return this.multiply(other.reciprocal());
  }
  
  /**
   * Performs an exponential operation.
   * 
   * @param x the exponent
   * @return the product
   */
  public ImgNumber exp(final int x) 
  {
    int i = 1;
    ImgNumber n;
    n = new ImgNumber(this.getReal(), img);
    while (i < x)
    { 
      n = (n.multiply(this));
      i++;
    }
    return n;
  }

  /**
   * Helper method for getting the reciprocal.
   * 
   * @return reciprocal of this complex number
   */
  private ImgNumber reciprocal()
  {
    double rec = getReal() * getReal() + img * img;
    return new ImgNumber((getReal() / rec), (-img / rec));
  }

  /**
   * @return the inverse of this imaginary number.
   */
  public ImgNumber inverse()
  {
    if (real.getReal() == 0)
    {
      return new ImgNumber(0, 1 / -img);
    } 
    double realPart = real.getReal() * real.getReal();
    double imgPart = img * img;
    double x = realPart + imgPart;
    
    return new ImgNumber(real.getReal() / x, -img / x);
  }
  
  /**
   * @return true if this has no value.
   */
  private boolean hasNoValue()
  {
    if (getReal() == 0 && img == 0)
    {
      return true;
    }
    return false;
  }
  

  /**
   * @return a String representation of a complex value.
   */
  @Override
  public String toString()
  {
    String format = "%.4f";
    String space = " ";
    String i = "i";
    String result = "";

    if (hasNoValue())
    {
      return Double.toString(0);
    }

    // no real part
    if (getReal() == 0)
    {
      result += String.format(format, img) + i;
    }
    // no imaginary part
    else if (img == 0)
    {
      result += String.format(format, real.getReal()) + "";
    }
    // negative imaginary value
    else if (img < 0)
    {
      // cancel the negative when formatting
      result += String.format(format, real.getReal()) + space + Operator.SUBTRACT.getOperator()
          + space + String.format(format, -img) + i;
    }
    else
    {
      result += String.format(format, real.getReal()) + space + Operator.ADD.getOperator() + space
          + String.format(format, img) + i;
    }
    return result;
  }

  @Override
  public void setReal(final double d)
  {
    // TODO Auto-generated method stub
    real.setReal(d);
  }

  @Override
  public void setOperator(final Operator op)
  {
    // TODO Auto-generated method stub
    operator = op;
  }
  
  /**
   * Sets this imaginary part to the parameter.
   * 
   * @param img the value to set this to
   */
  public void setImg(final double img) 
  {
    this.img = img;
  }
  
  /**
   * Swaps the imaginary sign.
   */
  public void swapSignImg() 
  {
    img = img * -1;
  }
  
  /**
   * Swaps the real sign.
   */
  public void swapsignReal() 
  {
    real.swapSign();
  }

}
