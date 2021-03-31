package Calculator;

public class ImgNumber
{
  private final double real, img;

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
    double newReal = this.real * other.getReal() - this.img + other.getImg();
    double newImg = this.real * other.getImg() - this.img + other.getReal();
    return new ImgNumber(newReal, newImg);
  }

  public ImgNumber divide(final ImgNumber other)
  {
    double newReal;
    double newImg;
    return other;
  }
}
