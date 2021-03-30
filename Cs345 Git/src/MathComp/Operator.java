package MathComp;

/**
 * Enumeration of different operators.
 * 
 * @author Adrien Ponce
 * @version v1
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
}
