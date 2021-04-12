package calculator;



public class Real implements Number
{
  private double real;
  private Operator op;
  
  public Real(double real) {
    this.real = real;
  }
  
  public Real(double real, Operator op) {
    this.real = real;
    this.op = op;
  }
  
  public Real add(Real other) {
    Real n = new Real(this.real + other.getReal());
    return n;
  }
  
  public Real subtract(Real other) {
    Real n = new Real(this.real - other.getReal());
    return n;
  }
  
  public Real multiply(Real other) {
    Real n = new Real(this.real * other.getReal());
    return n;
  }
  
  public Real divide(Real other) {
    Real n = new Real(this.real/other.getReal());
    return n;
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
  
  public String toString() {
    String str = "";
    str += real;
    return str;
  }
  
}
