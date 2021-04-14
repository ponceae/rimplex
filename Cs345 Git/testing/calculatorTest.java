package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calculator.ImgNumber;
import calculator.Operator;
import calculator.Real;
import calculator.calculator;

class calculatorTest
{
  private ImgNumber a = new ImgNumber(5.0, 3.0, Operator.ADD); // + (5 + 3i)
  private ImgNumber b = new ImgNumber(4.0, 6.0, Operator.SUBTRACT); // - (4 + 6i)
  private ImgNumber c = new ImgNumber(4.0, -6.0, Operator.SUBTRACT); // - (4 - 6i)
  private ImgNumber d = new ImgNumber(0.0, 3.0, Operator.EMPTY); // (3i)
  private ImgNumber j = new ImgNumber(2, 3.0, Operator.EMPTY); // (3i)
  private Real e = new Real(5.0);
  private Real f = new Real(1.5);
  private Real g = new Real(6.4);
  private Real h = new Real(-2.0);
  private Real i = new Real(0.0);
  private calculator cl;
  
  @Test
  void testAdd()
  {
    cl = new calculator();
    cl.addTo(cl.add(a, e));
    assertEquals("(10.0000 + 3.0000i)", cl.toString());
    cl.addTo(cl.add(a, e));
    assertEquals("(10.0000 + 3.0000i) + (10.0000 + 3.0000i)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.add(e,a));
    assertEquals("(10.0000 + 3.0000i)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.add(a,i));
    assertEquals("(5.0000 + 3.0000i)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.add(d,e));
    assertEquals("(5.0000 + 3.0000i)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.add(e,f));
    assertEquals("(6.5)", cl.toString());
  }
  
  @Test
  void testSub()
  {
    cl = new calculator();
    cl.addTo(cl.subtract(a, e));
    assertEquals("(3.0000i)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.subtract(f, b));
    assertEquals("(-2.5000 + 6.0000i)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.subtract(b, f));
    assertEquals("(2.5000 + 6.0000i)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.subtract(e, f));
    assertEquals("(3.5)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.subtract(a, b));
    assertEquals("(1.0000 - 3.0000i)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.subtract(j, j));
    assertEquals("(0.0)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.subtract(b, c));
    assertEquals("(12.0000i)", cl.toString());
  }
 
  
  @Test
  void testMultiply()
  {
    cl = new calculator();
    cl.addTo(cl.multiply(a, e));
    assertEquals("(25.0000 + 15.0000i)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.multiply(e, a));
    assertEquals("(25.0000 + 15.0000i)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.multiply(a, c));
    assertEquals("(38.0000 - 18.0000i)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.multiply(a, i));
    assertEquals("(0.0)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.multiply(h, c));
    assertEquals("(-8.0000 + 12.0000i)", cl.toString());
  }
  
  @Test
  void testDivide()
  {
    cl = new calculator();
    cl.addTo(cl.divide(a, e));
    assertEquals("(1.0000 + 0.6000i)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.divide(e, a));
    assertEquals("(1.0000 + 1.6667i)", cl.toString());
    cl = new calculator();
    cl.addTo(cl.divide(a, b));
    assertEquals("(0.7308 - 0.3462i)", cl.toString());
  }

}
