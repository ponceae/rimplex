package calculator;

import java.util.ArrayList;

public class calculator
{
  private ArrayList<Number> equation;

  public calculator()
  {
    equation = new ArrayList<Number>();
  }

  public calculator(ArrayList<Number> equation)
  {
    this.equation = equation;
  }

  public ArrayList<Number> getEquation()
  {
    return equation;
  }

  public void add(Number n)
  {
    equation.add(n);
  }

  public Number get(int x)
  {
    return equation.get(x);
  }

  /*
   * for all of these on add operators to them adrian i forgot too
   */
  public Number add(Number x, Number y)
  {
    Number n;
    if (isReal(x) && isReal(y))
    {
      n = (((Real) x).add((Real) y));
    }
    else if (isReal(x))
    {
      n = new ImgNumber((((Real) x).add(((ImgNumber) y).getRealObj())).getReal(),
          ((ImgNumber) y).getImg());
    }
    else
    {
      n = new ImgNumber((((Real) y).add(((ImgNumber) x).getRealObj())).getReal(),
          ((ImgNumber) x).getImg());
    }
    return n;
  }

  public Number subtract(Number x, Number y)
  {
    Number n;
    if (isReal(x) && isReal(y))
    {
      n = (((Real) x).subtract((Real) y));
    }
    else if (isReal(x))
    {
      n = new ImgNumber((((Real) x).subtract(((ImgNumber) y).getRealObj())).getReal(),
          ((ImgNumber) y).getImg());
    }
    else
    {
      n = new ImgNumber((((Real) y).subtract(((ImgNumber) x).getRealObj())).getReal(),
          ((ImgNumber) x).getImg());
    }
    return n;
  }

  public Number multiply(Number x, Number y)
  {
    Number n;
    if (isReal(x) && isReal(y))
    {
      n = (((Real) x).multiply((Real) y));
    }
    else if (isReal(x))
    {
      n = new ImgNumber((((Real) x).multiply(((ImgNumber) y).getRealObj())).getReal(),
          ((ImgNumber) y).getImg() * x.getReal());
    }
    else
    {
      n = new ImgNumber((((Real) y).multiply(((ImgNumber) x).getRealObj())).getReal(),
          ((ImgNumber) x).getImg() * y.getReal());
    }
    return n;
  }

  public Number divide(Number x, Number y)
  {
    Number n;
    if (isReal(x) && isReal(y))
    {
      n = (((Real) x).multiply((Real) y));
    }
    else if (isReal(x))
    {
      n = new ImgNumber((((Real) x).divide(((ImgNumber) y).getRealObj())).getReal(),
          ((ImgNumber) y).getImg() / x.getReal());
    }
    else
    {
      n = new ImgNumber((((Real) y).divide(((ImgNumber) x).getRealObj())).getReal(),
          ((ImgNumber) x).getImg() / y.getReal());
    }
    return n;
  }

  private boolean isReal(Number x)
  {
    boolean test = false;
    if (x instanceof Real)
    {
      test = true;
    }
    return test;
  }

  private boolean isImg(Number x)
  {
    boolean test = false;
    if (x instanceof ImgNumber)
    {
      test = true;
    }
    return test;
  }

  public String toString()
  {
    String str = "";
    for (int x = 0; x < equation.size() - 1; x++)
    {
      str += " (";
      str += equation.get(x).toString();
      str += ") ";
      str += equation.get(x + 1).getOperator();
    }
    str += equation.get(equation.size() - 1);
    return str;
  }
}
