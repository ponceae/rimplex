package MathComp;
/**
 * 
 * @author chris cleveland
 * 
 * Class to create a complex value to be stored in a complex number
 * 
 */
public class ComplexValue
{
  private int val; // the number
  private String car; // the imaginary varrible
  private char op; // the operation being preformed
  private int exp; // the exponent
  static final char ADD = '+';
  static final char SUB = '-';
  static final char MUL = '*';
  static final char DIV = '/';

  public ComplexValue()
  {
    val = 0;
    car = "";
    exp = 1; 
  }

  public ComplexValue(int val)
  {
    this.val = val;
    this.exp = 1;
  }

  public ComplexValue(char car)
  {
    this.val = 1;
    this.car = "";
    this.exp = 1;
  }

  public ComplexValue(String car, int exp)
  {
    this.val = 1;
    this.car = car;
    this.exp = exp;
  }

  public ComplexValue(String car, int exp, int val, char op)
  {
    this.val = val;
    this.car = car;
    this.exp = exp;
    this.op = op;
  }

  public int getVal()
  {
    return val;
  }

  public int getExp()
  {
    return exp;
  }

  public char getOp()
  {
    return op;
  }

  public String getCar()
  {
    return car;
  }

  /*
   * set methods
   */
  public void setVal(int val)
  {
    this.val = val;
  }

  public void setExp(int exp)
  {
    this.exp = exp;
  }

  public void setOp(char op)
  {
    this.op = op;
  }

  public void setCar(String car)
  {
    this.car = car;
  }

  /*
   * change this to return a new complex value that is a combination of the two values
   */
  public ComplexValue add(ComplexValue v)
  {
    ComplexValue ans = null;
    if (this.car == v.getCar())
    {
      ans.setVal(val + v.getVal());
      if(ans.getVal() == 0) {
        ans.setCar("");
      }
    }
    return ans;
  }
  
  public ComplexValue sub(ComplexValue v)
  {
    ComplexValue ans = null;
    if (this.car == v.getCar())
    {
      ans.setVal(val - v.getVal());
      if(ans.getVal() == 0) {
        ans.setCar("");
      }
    }

    return ans;
  }
  
  public ComplexValue mult(ComplexValue v) {
    ComplexValue ans = null;
    return ans;
  }
}
