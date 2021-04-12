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
  String zero = "0.0";
  String nTwo = "-2.00";
  
  private ImgNumber a = new ImgNumber(0.0, 6.0, Operator.SUBTRACT);
  private ImgNumber b = new ImgNumber(2.0, 4.0);
  private ImgNumber c = new ImgNumber(-2.0, 0.0);
  private ImgNumber d = new ImgNumber(0.0, 0.0);
  private ImgNumber e = new ImgNumber(-146.0, -56.0);
  private ImgNumber f = new ImgNumber(7.0, -6.0);
  private ImgNumber g = new ImgNumber(-2.0, 5.0);

  /**
   * Tests the add method.
   */
  @Test
  void testAdd()
  {
    assertEquals("2.00 - 2.00i", a.add(b).toString());
    assertEquals("-144.00 - 52.00i", b.add(e).toString());
    assertEquals("5.00 - 6.00i", c.add(f).toString());
    assertEquals("-2.00 + 5.00i", d.add(g).toString());
    assertEquals(zero, d.add(d).toString());
    assertEquals(nTwo, c.add(d).toString());
  }

  /**
   * Tests the subtract method.
   */
  @Test
  void testSubtract()
  {
    assertEquals("-2.00 - 10.00i", a.subtract(b).toString());
    assertEquals("148.00 + 60.00i", b.subtract(e).toString());
    assertEquals("-9.00 + 6.00i", c.subtract(f).toString());
    assertEquals("2.00 - 5.00i", d.subtract(g).toString());
    assertEquals(zero, d.subtract(d).toString());
    assertEquals(nTwo, c.subtract(d).toString());
  }

  /**
   * Tests the multiply method.
   */
  @Test
  void testMultiply()
  {
    assertEquals("24.00 - 12.00i", a.multiply(b).toString());
    assertEquals("-68.00 - 696.00i", b.multiply(e).toString());
    assertEquals("-14.00 + 12.00i", c.multiply(f).toString()); 
    assertEquals(zero, d.multiply(g).toString());
  }
  
  /**
   * Tests the divide method.
   */
  @Test
  void testDivide()
  {
    assertEquals("-1.20 - 0.60i", a.divide(b).toString());
    assertEquals("-0.02 - 0.02i", b.divide(e).toString());
    assertEquals("-0.16 - 0.14i", c.divide(f).toString());
  }
  
  /**
   * Tests misc functionality.
   */
  @Test
  void miscTests()
  {
    
  }
}
