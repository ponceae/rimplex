package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import MathComp.*;


/**
 * Tests the methods in the ComplexValue class.
 * 
 * @author Adrien Ponce
 * @version v1
 */
class ComplexValueTest
{
  private ComplexValue empty = new ComplexValue();
  // real var exp    operator     imaginary
  private ComplexValue a = new ComplexValue(0, "", 1, Operator.SUBTRACT, -6);
  private ComplexValue b = new ComplexValue(2, "", 1, Operator.ADD, 4);
  private ComplexValue c = new ComplexValue(-2, "", 1, Operator.EMPTY, 0);
  private ComplexValue d = new ComplexValue(0, "", 1, Operator.EMPTY, 0);
  private ComplexValue e = new ComplexValue(-146, "", 1, Operator.SUBTRACT, -56);
  private ComplexValue f = new ComplexValue(7, "", 1, Operator.SUBTRACT, 6);
  private ComplexValue g = new ComplexValue(-2, "", 1, Operator.ADD, 5); 

  /**
   * Checks that the setters and getters are free of error.
   */
  @Test
  void testSettersGetters()
  {
    String x = "x";
    a.setReal(3);
    assertEquals(3, a.getReal());
    b.setImaginary(8);
    assertEquals(8, b.getImaginary());
    c.setOperator(Operator.SUBTRACT);
    c.setImaginary(-5);
    assertEquals('-', c.getOperator().getOperator());
    d.setReal(2);
    d.setExponent(2);
    assertEquals(2, d.getExponent());  
    e.setVariable(x);
    assertEquals(x, e.getVariable());  
  }

  /**
   * Tests the add method.
   */
  @Test
  void testAdd()
  {
    assertEquals("(2 - 2i)", a.add(b).toString());
    assertEquals("(-144 - 52i)", b.add(e).toString());
    assertEquals("(5 - 6i)", c.add(f).toString());
    assertEquals("(-2 + 5i)", d.add(g).toString());
    assertEquals("", d.add(d).toString());
    assertEquals("(-2)", c.add(d).toString());
  }

  /**
   * Tests the subtract method.
   */
  @Test
  void testSubtract()
  {
    assertEquals("(-2 - 10i)", a.subtract(b).toString());
    assertEquals("(148 + 60i)", b.subtract(e).toString());
    assertEquals("(-9 + 6i)", c.subtract(f).toString());
    assertEquals("(2 - 5i)", d.subtract(g).toString());
    assertEquals("", d.subtract(d).toString());
  }
  
  /**
   * Tests the multiply method.
   */
  @Test
  void testMultiply()
  {
    
  }
}
