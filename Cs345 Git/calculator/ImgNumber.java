package calculator;

import math.Operator;

/**
 * This class represents a complex value with a real and imaginary value.
 * 
 * @author Chris Cleveland, Adrien Ponce
 * @version 3/31/21
 */
public class ImgNumber
{
  private final double real, img;
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
    this.real = real;
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
    this.real = real;
    this.img = img;
  }

  /**
   * @return the real part of the expression.
   */
  public double getReal()
  {
    return real;
  }

  /**
   * @return the imaginary part of the expression.
   */
  public double getImg()
  {
    return img;
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
    double newReal = this.real + other.getReal();
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
    return new ImgNumber(real - other.real, img - other.img);
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
    double newReal = this.real * other.getReal() - this.img * other.getImg();
    double newImg = this.real * other.getImg() + this.img * other.getReal();
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
   * Helper method for getting the reciprocal.
   * 
   * @return reciprocal of this complex number
   */
  private ImgNumber reciprocal()
  {
    double rec = real * real + img * img;
    return new ImgNumber((real / rec), (-img / rec));
  }

  /**
   * @return true if this has no value.
   */
  private boolean hasNoValue()
  {
    if (real == 0 && img == 0)
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
    String format = "%.2f";
    String space = " ";
    String i = "i";
    String result = "";

    if (hasNoValue())
    {
      return Double.toString(0);
    }

    // no real part
    if (real == 0)
    {
      result += String.format(format, img) + i;
    }
    // no imaginary part
    else if (img == 0)
    {
      result += String.format(format, real) + "";
    }
    // negative imaginary value
    else if (img < 0)
    {
      // cancel the negative when formatting
      result += String.format(format, real) + space + Operator.SUBTRACT.getOperator() + space
          + String.format(format, -img) + i;
    }
    else
    {
      result += String.format(format, real) + space + Operator.ADD.getOperator() + space
          + String.format(format, img) + i;
    }
    return result;
  }

}
