package calculator;

public class Real implements Number
{
  private double real;
  private Operator op;

  public Real(double real)
  {
    this.real = real;
    op = Operator.ADD;
  }

  public Real(double real, Operator op)
  {
    this.real = real;
    this.op = op;
  }

  public Real add(Real other)
  {
    Real n = new Real(this.real + other.getReal());
    return n;
  }

  public Real subtract(Real other)
  {

    return new Real(this.real - other.getReal());

  }

  public Real multiply(Real other)
  {
    double test = other.getReal();
    Real n;

    if (test == 0.0 || real == 0.0)
    {
      n = new Real(0.000);
    } else {
      n = new Real(this.real * other.getReal());
    }
    return n;
  }

  public Real divide(Real other)
  {
    Real n = new Real(this.real / other.getReal());
    return n;
  }

  public Real squared(int x)
  {
    int i = 1;
    double val = real;
    Real n;
    while (i < x)
    {
      val *= real;
      i++;
    }
    return n = new Real(val);
  }

  public double getReal()
  {
    // TODO Auto-generated method stub
    return real;
  }

  @Override
  public void setReal(double d)
  {
    // TODO Auto-generated method stub
    real = d;
  }

  @Override
  public Operator getOperator()
  {
    // TODO Auto-generated method stub
    return op;
  }

  @Override
  public void setOperator(Operator op)
  {
    // TODO Auto-generated method stub
    this.op = op;
  }

  public String toString()
  {
    String str = "";
    str += real;
    return str;
  }

}
