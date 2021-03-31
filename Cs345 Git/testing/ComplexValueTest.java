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
  private ComplexValue a = new ComplexValue( "x",0, 1, Operator.SUBTRACT);
  private ComplexValue b = new ComplexValue( "x",2, 1, Operator.ADD);
  private ComplexValue c = new ComplexValue( "",-2, 1, Operator.EMPTY);
  private ComplexValue d = new ComplexValue( "a",0, 1, Operator.EMPTY);
  private ComplexValue e = new ComplexValue( "b", -146,1, Operator.SUBTRACT);
  private ComplexValue f = new ComplexValue( "x",7, 1, Operator.SUBTRACT);
  private ComplexValue g = new ComplexValue( "a",-2, 1, Operator.ADD);  
  private ComplexValue h = new ComplexValue( "x",2, 1, Operator.ADD);  
  private ComplexValue i = new ComplexValue( "x",-2, 1, Operator.ADD);  

  /**
   * Checks that the setters and getters are free of error.
   */
  @Test
  void testSettersGetters()
  {
    String x = "x";
    a.setReal(3);
    assertEquals(3, a.getReal()); 
    c.setOperator(Operator.SUBTRACT);
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
    assertEquals("2x", a.add(b).toString());
    assertEquals("Cant perform operation", b.add(e).toString());
    assertEquals("9x", a.add(b).add(f).toString());
    assertEquals("0", b.add(i).toString());
  }

  /**
   * Tests the subtract method.
   */
  @Test
  void testSubtract()
  {
    assertEquals("-2x", a.subtract(b).toString());
    assertEquals("Cant perform operation", b.subtract(e).toString());
    assertEquals("0", b.subtract(h).toString());
  }
  
  /**
   * Tests the multiply method.
   */
  @Test
  void testMultiply()
  {
    
  }
}
