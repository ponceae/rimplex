package calculator;

/**
 * Enumeration of different operators.
 * 
 * @author Adrien Ponce
 * @version 4/12
 */

public enum Operator
{

  ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/'), EMPTY(' ');

  private char operator;

  /**
   * Constructs an enumeration with the appropriate value.
   * 
   * @param operator
   *          the operator
   */
  private Operator(final char operator)
  {
    this.operator = operator;
  }
  
  /**
   * Returns the description of the enum.
   * 
   * @return the String representation
   */
  public char getOperator() 
  {
    return operator;
  }
  
  /**
   * Parses a string a returns the operator.
   * 
   * @param theOp the string to parse
   * @return the recognized operator, or null if not found
   */
  public static Operator getFrom(final String theOp) 
  {
    Operator returnOp = null;
    for (Operator op : Operator.values()) 
    {
      if (op.getOperator() == theOp.charAt(0)) 
      {
        returnOp = op;
      }
    }
    return returnOp;
  }
}
