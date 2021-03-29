package MathComp;

/**
 * Enumeration of different operators.
 * 
 * @author Adrien Ponce
 * @version v1
 */
public enum Operator
{

  ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/");

  private String operator;

  /**
   * Constructs an enumeration with the appropriate value.
   * 
   * @param operator
   *          the operator
   */
  private Operator(final String operator)
  {
    this.operator = operator;
  }
  
  /**
   * Returns the description of the enum.
   * 
   * @return the String representation
   */
  @Override
  public String toString() 
  {
    return operator;
  }
}
