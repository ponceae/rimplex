package testing;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import calculator.ImgNumber;
import calculator.Operator;
import calculator.Real;
import calculator.Calculator;

/**
 * Tests the calculator class.
 * 
 * @author Chris Cleveland & Adrien Ponce
 * @version 4/12/21
 */
class CalculatorTest
{
  private ImgNumber a = new ImgNumber(5.0, 3.0, Operator.ADD); // + (5 + 3i)
  private ImgNumber b = new ImgNumber(4.0, 6.0, Operator.SUBTRACT); // - (4 + 6i)
  private ImgNumber c = new ImgNumber(4.0, -6.0, Operator.SUBTRACT); // - (4 - 6i)
  private ImgNumber d = new ImgNumber(0.0, 3.0, Operator.EMPTY); // (3i)
  private ImgNumber j = new ImgNumber(2, 3.0, Operator.EMPTY); // (3i)
  private Real e = new Real(5.0);
  private Real f = new Real(1.5);
  private Real h = new Real(-2.0);
  private Real i = new Real(0.0);
  private Calculator cl;
  private String zero = "(0.0)";
  
  /**
   * Tests all adding.
   */
  @Test
  void testAdd()
  {
    cl = new Calculator();
    cl.addTo(cl.add(a, e));
    String res = "(10.0000 + 3.0000i)";
    assertEquals(res, cl.toString());
    cl.addTo(cl.add(a, e));
    assertEquals(res + " + " + res, cl.toString());
    cl = new Calculator();
    cl.addTo(cl.add(e,a));
    assertEquals(res, cl.toString());
    cl = new Calculator();
    cl.addTo(cl.add(a,i));
    String result = "(5.0000 + 3.0000i)";
    assertEquals(result, cl.toString());
    cl = new Calculator();
    cl.addTo(cl.add(d,e));
    assertEquals(result, cl.toString());
    cl = new Calculator();
    cl.addTo(cl.add(e,f));
    assertEquals("(6.5)", cl.toString());
  }
  
  /**
   * Tests all subtracting.
   */
  @Test
  void testSub()
  {
    cl = new Calculator();
    cl.addTo(cl.subtract(a, e));
    assertEquals("(3.0000i)", cl.toString());
    cl = new Calculator();
    cl.addTo(cl.subtract(f, b));
    assertEquals("(-2.5000 + 6.0000i)", cl.toString());
    cl = new Calculator();
    cl.addTo(cl.subtract(b, f));
    assertEquals("(2.5000 + 6.0000i)", cl.toString());
    cl = new Calculator();
    cl.addTo(cl.subtract(e, f));
    assertEquals("(3.5)", cl.toString());
    cl = new Calculator();
    cl.addTo(cl.subtract(a, b));
    assertEquals("(1.0000 - 3.0000i)", cl.toString());
    cl = new Calculator();
    cl.addTo(cl.subtract(j, j));
    assertEquals(zero, cl.toString());
    cl = new Calculator();
    cl.addTo(cl.subtract(b, c));
    assertEquals("(12.0000i)", cl.toString());
  }
 
  /**
   * Tests all multiplying.
   */
  @Test
  void testMultiply()
  {
    cl = new Calculator();
    cl.addTo(cl.multiply(a, e));
    String res2 = "(25.0000 + 15.0000i)";
    assertEquals(res2, cl.toString());
    cl = new Calculator();
    cl.addTo(cl.multiply(e, a));
    assertEquals(res2, cl.toString());
    cl = new Calculator();
    cl.addTo(cl.multiply(a, c));
    assertEquals("(38.0000 - 18.0000i)", cl.toString());
    cl = new Calculator();
    cl.addTo(cl.multiply(a, i));
    assertEquals(zero, cl.toString());
    cl = new Calculator();
    cl.addTo(cl.multiply(h, c));
    assertEquals("(-8.0000 + 12.0000i)", cl.toString());
  }
  
  /**
   * Tests all dividing.
   */
  @Test
  void testDivide()
  {
    cl = new Calculator();
    cl.addTo(cl.divide(a, e));
    assertEquals("(1.0000 + 0.6000i)", cl.toString());
    cl = new Calculator();
    cl.addTo(cl.divide(e, a));
    assertEquals("(1.0000 + 1.6667i)", cl.toString());
    cl = new Calculator();
    cl.addTo(cl.divide(a, b));
    assertEquals("(0.7308 - 0.3462i)", cl.toString());
  }

}
