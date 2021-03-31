package Calculator;

public class imgNumber
{
  private final double real, img;

  public imgNumber(double real, double img)
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

  public imgNumber add(imgNumber other)
  {
    double newReal = this.real + other.getReal();
    double newImg = this.img + other.getImg();
    return new imgNumber(newReal, newImg);
  }

  public imgNumber subtract(final imgNumber other)
  {
    return new imgNumber(real - other.real, img - other.img);
  }

  public imgNumber mult(final imgNumber other)
  {
    double newReal = this.real * other.getReal() - this.img + other.getImg();
    double newImg = this.real * other.getImg() - this.img + other.getReal();
    return new imgNumber(newReal, newImg);
  }
  
  public imgNumber div(final imgNumber other) {
    double newReal;
    double newImg;
    return other;
  }
}
