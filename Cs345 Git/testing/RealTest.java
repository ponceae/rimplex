package testing;

import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the real number class.
 * 
 * @author Chris Cleveland & Adrien Ponce
 * @version 4/12/21
 */
class RealTest 
{

  private Real a = new Real(5.0);
  private Real b = new Real(1.5);
  private Real d = new Real(-12.0);
  private Real e = new Real(2.0);
  private Real f = new Real(0.0);

  /**
   * Tests the constructor.
   */
  @Test
  void testConstructor()
  {
    assertEquals(5.0, a.getReal());
    assertEquals('+', a.getOperator().getOperator());
    
  }

  /**
   * Tests adding.
   */
  @Test
  void testAdd()
  {
    Real sumAB = a.add(b);
    assertEquals(6.5, sumAB.getReal());
    sumAB = sumAB.add(b);
    assertEquals(8.0, sumAB.getReal());
    sumAB = sumAB.add(d);
    assertEquals(-4.0, sumAB.getReal());
  }

  /**
   * Tests subtracting.
   */
  @Test
  void testSubtract()
  {
    Real ab = a.subtract(e);
    assertEquals(3.0, ab.getReal());
    ab = ab.subtract(b);
    assertEquals(1.5, ab.getReal());
    ab = ab.subtract(d);
    assertEquals(13.5, ab.getReal());
    ab = ab.subtract(f);
    assertEquals(13.5, ab.getReal());
  }
  
  /**
   * Tests mulitplying.
   */
  @Test
  void testMultiplication()
  {
    Real ab = a.multiply(e);
    assertEquals(10.0, ab.getReal());
    ab = ab.multiply(b);
    assertEquals(15.0, ab.getReal());
    ab = ab.multiply(d);
    assertEquals(-180.0, ab.getReal());
    ab = ab.multiply(f);
    assertEquals(0.0, ab.getReal());
    Real twoZero = new Real(0.0, Operator.EMPTY);
    Real otherZero = new Real(0.0, Operator.EMPTY);
    Real zero = twoZero.multiply(otherZero);
    assertEquals(0.0, twoZero.multiply(otherZero).getReal());
    assertEquals(0.0, otherZero.multiply(twoZero).getReal());
    zero.setReal(8.0);
    assertEquals(8.0, zero.getReal());
    zero.setOperator(Operator.ADD);
    assertEquals(Operator.ADD, zero.getOperator());
    zero.swapSign();
    assertEquals("-8.0", zero.toString());
  }
  
  /**
   * Tests dividing.
   */
  @Test
  void testDivide()
  {
    Real ab = d.divide(e);
    assertEquals(-6.0, ab.getReal());
  }
  
  /**
   * Tests squaring.
   */
  @Test
  void testSqured()
  {
    Real ab = a.squared(2);
    assertEquals(25.0, ab.getReal());
    ab = ab.squared(2);
    assertEquals(625.0, ab.getReal());
    ab = a.squared(3);
    assertEquals(125.0, ab.getReal());
  }
}
