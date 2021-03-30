package testing;

import static org.junit.jupiter.api.Assertions.*;
import MathComp.*;
import org.junit.jupiter.api.Test;


/**
 * Checks the Operator enumeration.
 * 
 * @author Adrien Ponce
 * @version v1
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
