package testing;

import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import org.junit.jupiter.api.Test;

class RealTest
{

  private Real a = new Real(5.0);
  private Real b = new Real(1.5);
  private Real c = new Real(6.4);
  private Real d = new Real(-12.0);
  private Real e = new Real(2.0);
  private Real f = new Real(0.0);

  @Test
  void testConstructor()
  {
    assertEquals(5.0, a.getReal());
    assertEquals('+', a.getOperator());
  }

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

  @Test
  void testSubtract()
  {
    Real AB = a.subtract(e);
    assertEquals(3.0, AB.getReal());
    AB = AB.subtract(b);
    assertEquals(1.5, AB.getReal());
    AB = AB.subtract(d);
    assertEquals(13.5, AB.getReal());
    AB = AB.subtract(f);
    assertEquals(13.5, AB.getReal());
  }
}
