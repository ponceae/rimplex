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
    
  }

  /**
   * Tests the multiply method.
   */
  @Test
  void testMultiply()
  {
    
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
