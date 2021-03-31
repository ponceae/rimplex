package Calculator;

import MathComp.Operator;

public class ImgNumber
{
  private final double real, img;
  private Operator operator;

  public ImgNumber(double real, double img, Operator operator)
  {
    this.real = real;
    this.img = img;
    this.operator = operator;
  }

  public ImgNumber(double real, double img)
  {
    this.real = real;
    this.img = img;
  }

  public double getReal()
  {
    return real;
  }

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

  public ImgNumber add(ImgNumber other)
  {
    double newReal = this.real + other.getReal();
    double newImg = this.img + other.getImg();
    return new ImgNumber(newReal, newImg);
  }

  public ImgNumber subtract(final ImgNumber other)
  {
    return new ImgNumber(real - other.real, img - other.img);
  }

  public ImgNumber multiply(final ImgNumber other)
  {
    double newReal = this.real * other.getReal() - this.img * other.getImg();
    double newImg = this.real * other.getImg() + this.img * other.getReal();
    return new ImgNumber(newReal, newImg);
  }

  public ImgNumber divide(final ImgNumber other)
  {
    return this.multiply(other.reciprocal());
  }

  /**
   * helper method for getting reciprocal.
   * 
   * @return
   */
  public ImgNumber reciprocal()
  {
    double rec = real * real + img * img;
    return new ImgNumber((real / rec), (-img / rec));
  }

  /*
   * @return true if
   * 
   * the ImgNumber has no value
   */

  private boolean hasNoValue()
  {
    if (real == 0 && img == 0)
    {
      return true;
    }
    return false;
  }

  /*
   * 
   * Returns a String representation of a complex value.
   */

  public String toString()
  {
    String result = "";

    if (hasNoValue())
    {
      return Double.toString(0);
    }

    // no real part
    if (real == 0)
    {
      result += img + "i";
    }
    // no imaginary part
    else if (img == 0)
    {
      result += real + "";
    }
    // negative imaginary value
    else if (img < 0)
    {
      // cancel the negative when formatting
      result += real + " " + Operator.SUBTRACT.getOperator() + " " + -img + "i";
    }
    else
    {
      result += real + " " + Operator.ADD.getOperator() + " " + img + "i";
    }
    return result;
  }

}
