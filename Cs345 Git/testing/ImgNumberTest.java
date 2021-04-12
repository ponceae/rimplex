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
  
  private ImgNumber a = new ImgNumber(5.0, 3.0, Operator.ADD); // + (5 + 3i)
  private ImgNumber b = new ImgNumber(4.0, 6.0, Operator.SUBTRACT); // - (4 + 6i)
  private ImgNumber c = new ImgNumber(4.0, -6.0, Operator.SUBTRACT); // - (4 - 6i)
  private ImgNumber d = new ImgNumber(0.0, 3.0, Operator.EMPTY); // (3i)
 

  /**
   * Tests the add method.
   */
  @Test
  void testAdd()
  {
    assertEquals("9.00 + 9.00i", a.add(b).toString());
    assertEquals("5.00 + 6.00i", a.add(d).toString()); 
    assertEquals("9.00 - 3.00i", a.add(c).toString()); 
    assertEquals("4.00 - 3.00i", c.add(d).toString()); 
  }

  /**
   * Tests the subtract method.
   */
  @Test
  void testSubtract()
  {
    assertEquals("1.00 - 3.00i", a.subtract(b).toString());
    assertEquals("5.00", a.subtract(d).toString()); 
    assertEquals("1.00 + 9.00i", a.subtract(c).toString()); 
    assertEquals("4.00 - 9.00i", c.subtract(d).toString()); 
  }

  /**
   * Tests the multiply method.
   */
  @Test
  void testMultiply()
  {
    assertEquals("2.00 + 42.00i", a.multiply(b).toString());
    assertEquals("52.00", b.multiply(c).toString()); 
    assertEquals("38.00 - 18.00i", a.multiply(c).toString()); 
    assertEquals("18.00 + 12.00i", c.multiply(d).toString());
  }
  
  /**
   * Tests the divide method.
   */
  @Test
  void testDivide()
  {
   
  }
  
  /**
   * Tests misc functionality.
   */
  @Test
  void miscTests()
  {
    
  }
}
