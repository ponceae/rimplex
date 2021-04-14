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
  private ImgNumber f = new ImgNumber(2.0, 3.0, Operator.SUBTRACT);

  /**
   * Tests the add method.
   */
  @Test
  void testAdd()
  {
    assertEquals("9.0000 + 9.0000i", a.add(b).toString());
    assertEquals("5.0000 + 6.0000i", a.add(d).toString()); 
    assertEquals("9.0000 - 3.0000i", a.add(c).toString()); 
    assertEquals("4.0000 - 3.0000i", c.add(d).toString()); 
   
  }

  /**
   * Tests the subtract method.
   */
  @Test
  void testSubtract()
  {
    assertEquals("1.0000 - 3.0000i", a.subtract(b).toString());
    assertEquals("5.0000", a.subtract(d).toString()); 
    assertEquals("1.0000 + 9.0000i", a.subtract(c).toString()); 
    assertEquals("4.0000 - 9.0000i", c.subtract(d).toString()); 
    assertEquals("0.0", c.subtract(c).toString()); 
  }

  /**
   * Tests the multiply method.
   */
  @Test
  void testMultiply()
  {
    assertEquals("2.0000 + 42.0000i", a.multiply(b).toString());
    assertEquals("52.0000", b.multiply(c).toString()); 
    assertEquals("38.0000 - 18.0000i", a.multiply(c).toString()); 
    assertEquals("18.0000 + 12.0000i", c.multiply(d).toString());
  }
  
  /**
   * Tests the divide method.
   */
  @Test
  void testDivide()
  {
    assertEquals("0.7308 - 0.3462i", a.divide(b).toString());
    assertEquals("-0.3846 + 0.9231i", b.divide(c).toString()); 
    assertEquals("0.0385 + 0.8077i", a.divide(c).toString()); 
    assertEquals("-2.0000 - 1.3333i", c.divide(d).toString());
  }
  
  @Test
  void testInverse()
  {

    assertEquals("16.0000 + 30.0000i", a.exp(2).toString());
    assertEquals("-10.0000 + 198.0000i", a.exp(3).toString());  

    assertEquals("0.1471 - 0.0882i", a.inverse().toString());
    assertEquals("0.0769 - 0.1154i", b.inverse().toString());
    assertEquals("0.0769 + 0.1154i", c.inverse().toString());
    assertEquals("-0.3333i", d.inverse().toString());

  }
  
}
