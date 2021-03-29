package MathComp;

import java.util.ArrayList;

public class ComplexNumber
{

  private ArrayList<ComplexValue> number;

  public ComplexNumber()
  {
    number = new ArrayList<ComplexValue>();
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

  /**
   * Evaluates a two complex numbers into one new complex number
   */
  public ComplexNumber evaluate()
  {
    ComplexNumber ans = new ComplexNumber();
    for (int x = 1; x < number.size(); x++)
    {
      if(canAdd(number.get(x), number.get(x-1))) {
        ans.add(number.get(x-1).add(number.get(x)));
      } else if(canSub(number.get(x), number.get(x-1))) {
        ans.add(number.get(x-1).sub(number.get(x)));
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
    if (a.getOp() == ComplexValue.ADD && (a.getCar() == b.getCar()))
    {
      ans = true;
    }
    return ans;
  }
  
  private boolean canSub(ComplexValue a, ComplexValue b) {
    boolean ans = false;
    if (a.getOp() == ComplexValue.SUB && (a.getCar() == b.getCar()))
    {
      ans = true;
    }
    return ans;
  }
}
