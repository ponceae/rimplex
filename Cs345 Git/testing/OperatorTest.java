package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calculator.Operator;


/**
 * Checks the Operator enumeration.
 * 
 * @author Adrien Ponce
 * @version 3/31/21
 */
class OperatorTest
{

  /**
   * Tests the whole enumeration.
   */
  @Test
  void testAll()
  {
    Operator a = Operator.ADD;
    Operator b = Operator.SUBTRACT;
    Operator c = Operator.MULTIPLY;
    Operator d = Operator.DIVIDE;
    Operator e = Operator.EMPTY;
    
    assertEquals('+', a.getOperator());
    assertEquals('-', b.getOperator());
    assertEquals('*', c.getOperator());
    assertEquals('/', d.getOperator());
    assertEquals(' ', e.getOperator());

  }

}
