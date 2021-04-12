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
  private Real e = new Real(5.0);
  private Real f = new Real(1.5);
  private Real g = new Real(6.4);
  private Real h = new Real(-12.0);
  private calculator cl;
  
  @Test
  void testAdd()
  {
    cl = new calculator();
    cl.addTo(cl.add(a, e));
    assertEquals("(10.00 + 3.00i)", cl.toString());
    cl.addTo(cl.add(a, e));
    assertEquals("(10.00 + 3.00i)", cl.toString());
  }

}
