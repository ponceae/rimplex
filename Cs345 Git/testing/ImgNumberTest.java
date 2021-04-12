package testing;

import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the operations in the Complex class.
 * 
 * @author Adrien Ponce, Chris Cleveland
 * @version 3/31/21
 */
class ImgNumberTest
{ 
  
  private ImgNumber a = new ImgNumber(5.0, 3.0, Operator.ADD); // (5 + 3i)
  private ImgNumber b = new ImgNumber(4.0, 6.0, Operator.SUBTRACT); // (4 - 6i)
  private Real c = new Real(5.0, Operator.EMPTY); // (5)
  private ImgNumber d = new ImgNumber(0.0, 3.0, Operator.EMPTY); // (3i)

  /**
   * Tests the add method.
   */
  @Test
  void testAdd()
  {
    assertEquals("9.0 - 3.0i", calculator.add(a, b).toString());
    assertEquals("9.0 - 3.0i", calculator.add(b, a).toString()); 
    assertEquals("10.0 + 3.0i", calculator.add(c, a).toString());
    assertEquals("10.0 + 3.0i", calculator.add(a, c).toString());
    assertEquals("5.0 + 6.0i", calculator.add(d, a).toString());
    assertEquals("5.0 + 6.0i", calculator.add(a, d).toString());
  }

  /**
   * Tests the subtract method.
   */
  @Test
  void testSubtract()
  {
    assertEquals("", calculator.subtract(a, b).toString());
    assertEquals("", calculator.subtract(a, b).toString()); 
  }

  /**
   * Tests the multiply method.
   */
  @Test
  void testMultiply()
  {
    assertEquals("2.0 - 2.0i", a.multiply(b).toString());
    assertEquals("15.0 - 3.0i", c.multiply(d).toString()); 
  }
  
  /**
   * Tests the divide method.
   */
  @Test
  void testDivide()
  {
    assertEquals("2.0 - 2.0i", a.divide(b).toString());
    assertEquals("15.0 - 3.0i", c.divide(d).toString()); 
  }
  
  /**
   * Tests misc functionality.
   */
  @Test
  void miscTests()
  {
    
  }
}
