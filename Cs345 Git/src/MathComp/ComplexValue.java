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
  private int val;
  private char car;
  private char op;
  private int exp;

  public ComplexValue()
  {
    val = 0;
    car = ' ';
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
    this.car = car;
    this.exp = 1;
  }

  public ComplexValue(char car, int exp)
  {
    this.val = 1;
    this.car = car;
    this.exp = exp;
  }

  public ComplexValue(char car, int exp, int val, char op)
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

  public int getCar()
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

  public void setCar(char car)
  {
    this.car = car;
  }

  /*
   * change this to return a new complex value that is a combination of the two values
   */
  public ComplexValue add(ComplexValue v)
  {
    boolean test = false;
    ComplexValue ans = null;
    if (this.car == v.getCar())
    {
      ans.setVal(val + v.getVal());
      if(ans.getVal() == 0) {
        ans.setCar(' ');
      }
    }
    return ans;
  }
  
  public ComplexValue sub(ComplexValue v)
  {
    boolean test = false;
    ComplexValue ans = null;
    if (this.car == v.getCar())
    {
      ans.setVal(val - v.getVal());
      if(ans.getVal() == 0) {
        ans.setCar(' ');
      }
    }

    return ans;
  }
  
  public ComplexValue mult(ComplexValue v) {
    ComplexValue ans = null;
    return ans;
  }
}
