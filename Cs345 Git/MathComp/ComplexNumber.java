package MathComp;

import java.util.ArrayList;

public class ComplexNumber
{
  private ArrayList<ComplexValue> number;

  public ComplexNumber()
  {
    number = new ArrayList<ComplexValue>();
  }

  public ComplexNumber(ArrayList<ComplexValue> number)
  {
    this.number = number;
  }

  /*
   * Adds a complex value to the list, returns true if it was added.
   */
  public boolean add(ComplexValue c)
  {
    boolean test = false;
    if (c != null)
    {
      number.add(c);
      test = true;
    }
    return test;
  }

  /*
   * Removes a complex value
   */
  public boolean remove(ComplexValue c)
  {
    boolean test = false;
    if (c != null)
    {
      number.remove(c);
      test = true;
    }
    return test;
  }

  /*
   * gets the size of the array
   */
  public int size()
  {
    return number.size();
  }

  public ComplexNumber copy(ArrayList<ComplexValue> number)
  {
    ComplexNumber ans = new ComplexNumber();
    for (int x = 0; x < number.size(); x++)
    {
      ans.add(number.get(x));
    }
    return ans;
  }

  /**
   * Evaluates a two complex numbers into one new complex number
   */
  public ComplexNumber evaluate()
  {
    ComplexNumber ans = new ComplexNumber();
    ComplexNumber clone = new ComplexNumber();
    clone.copy(number);
    if (number.size() == 1)
    {
      ans.add(number.get(0));
      return ans;
    }
    for (int x = 1; x < number.size(); x++)
    {
      if (canAdd(number.get(x), number.get(x - 1)))
      {
        ans.add(number.get(x - 1).add(number.get(x)));
      }
      else if (canSub(number.get(x), number.get(x - 1)))
      {
        ans.add(number.get(x - 1).subtract(number.get(x)));
      }
      else
      {
        ans.add(number.get(x - 1));
      }
    }
    return ans;
  }

  /**
   * Helper Method to see if two values can be added together.
   * 
   * @param a
   *          the first value
   * @param b
   *          the second value
   * @return ans
   */
  private boolean canAdd(ComplexValue a, ComplexValue b)
  {
    boolean ans = false;
    ComplexValue c = a.add(b);
    if (a.getOperator() == Operator.ADD && c.getVariable() != ComplexValue.badOp)
    {

      ans = true;
    }
    return ans;
  }

  private boolean canSub(ComplexValue a, ComplexValue b)
  {
    boolean ans = false;
    ComplexValue c = a.subtract(b);
    if (a.getOperator() == Operator.SUBTRACT && c.getVariable() != ComplexValue.badOp)
    {
      ans = true;
    }
    return ans;
  }

  public String toString()
  {
    String str = "";
    for (int x = 0; x < number.size(); x++)
    {
      str += number.get(x).toString();
    }
    return str;
  }
}
