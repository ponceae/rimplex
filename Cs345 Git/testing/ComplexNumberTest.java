package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import MathComp.*;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Adrien Ponce
 * @version v1
 */
class ComplexNumberTest
{

  @Test
  void testAddRemove()
  {
    ComplexNumber expression = new ComplexNumber();

    ComplexValue a = new ComplexValue("x", 0, 1, Operator.SUBTRACT);
    ComplexValue b = new ComplexValue("x", 2, 1, Operator.ADD);
    ComplexValue c = new ComplexValue("x", 4, 1, Operator.EMPTY);
     
    expression.add(a);
    expression.add(b);
    expression.add(c);

    assertEquals(3, expression.size());
    
    expression.remove(c);
    expression.remove(b);
    expression.remove(a);

    assertEquals(0, expression.size());

  }

  @Test 
  void testEvaulate()
  {
    ComplexValue a = new ComplexValue("x", 0, 1, Operator.EMPTY);
    ComplexValue b = new ComplexValue("x", 2, 1, Operator.ADD);
    ComplexValue c = new ComplexValue("x", 4, 1, Operator.ADD);
    ComplexValue d = new ComplexValue("y", 100, 100, Operator.EMPTY);
    
    ArrayList<ComplexValue> nums = new ArrayList<>();
    nums.add(a);
    nums.add(b);
    nums.add(c);
    
    ComplexNumber expression = new ComplexNumber(nums);
    
    assertEquals("2x", expression.evaluate().toString());
    
    expression.add(d);
    assertEquals("Can't perform operation", expression.evaluate().toString());
    
  }
  
  @Test
  void testCanAdd()
  {
    
  }
  
  @Test
  void testCanSub()
  {
    
  }
}
