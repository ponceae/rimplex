package testing;

import static org.junit.jupiter.api.Assertions.*;
import Calculator.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the operations in the Complex class.
 * 
 * @author Adrien Ponce
 * @version v1
 */
class ImgNumberTest
{
  
  private ImgNumber a = new ImgNumber(0.0, -6.0);
  private ImgNumber b = new ImgNumber(2.0, 4.0);
  private ImgNumber c = new ImgNumber(-2.0, 0.0);
  private ImgNumber d = new ImgNumber(0.0, 0.0);
  private ImgNumber e = new ImgNumber(-146.0, -56.0);
  private ImgNumber f = new ImgNumber(7.0, -6.0);
  private ImgNumber g = new ImgNumber(-2.0, 5.0);

  @Test
  void testAdd()
  {
    assertEquals("2.0 - 2.0i", a.add(b).toString());
    assertEquals("-144.0 - 52.0i", b.add(e).toString());
    assertEquals("5.0 - 6.0i", c.add(f).toString());
    assertEquals("-2.0 + 5.0i", d.add(g).toString());
    assertEquals("0.0i", d.add(d).toString());
    assertEquals("-2.0", c.add(d).toString());
  }

  @Test
  void testSubtract()
  {
    assertEquals("-2.0 - 10.0i", a.subtract(b).toString());
    assertEquals("148.0 + 60.0i", b.subtract(e).toString());
    assertEquals("-9.0 + 6.0i", c.subtract(f).toString());
    assertEquals("2.0 - 5.0i", d.subtract(g).toString());
    assertEquals("0.0i", d.subtract(d).toString());
    assertEquals("-2.0", c.subtract(d).toString());
  }

  @Test
  void testMultiply()
  {
    assertEquals("24.0 - 12.0i", a.multiply(b).toString());
    assertEquals("-68.0 - 696.0i", b.multiply(e).toString());
    assertEquals("-14.0 + 12i", c.multiply(f).toString()); 
    assertEquals("0.0", d.multiply(g).toString());
    assertEquals("0.0", d.multiply(d).toString());
    assertEquals("0.0", c.multiply(d).toString());
  }
  
  @Test
  void testDivide()
  {
    assertEquals("-6.0 / 5.0 - 3.0 / 5.0i", a.divide(b).toString());
    assertEquals("-129.0 / 6113.0 - 118.0 / 6113.0i", b.divide(e).toString());
    assertEquals("-14.0 / 85.0 - 12.0 / 85.0i", c.divide(f).toString());
    assertEquals("0.0", d.divide(g).toString());
    assertEquals("1.0", d.divide(d).toString());
    assertEquals("1.0", c.divide(d).toString());
  }
}
