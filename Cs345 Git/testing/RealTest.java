package testing;

import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import org.junit.jupiter.api.Test;

class RealTest
{

  private Real a = new Real(5.0);
  private Real b = new Real(1.5);
  private Real c = new Real(6.7);
  private Real d = new Real(12.6);
  
  @Test
  void testAdd()
  {
    Real sumAB = a.add(b);
    assertEquals(6.5, sumAB.getReal());
    sumAB = sumAB.add(b);
    assertEquals(8.0, sumAB.getReal());
    
  }

}
