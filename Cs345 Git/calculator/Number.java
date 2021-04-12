package calculator;

/**
 * Represents various number operations.
 * 
 * @author Chris Cleveland, Adrien Ponce
 * @version 4/12
 */
public interface Number
{    
    /**
     * @return the real part of the expression
     */
    public double getReal();
    
    /**
     * Sets this real number to value
     * 
     * @param value the value to set this to
     */
    public void setReal(double value);
    
    /**
     * @return the operator in the expression
     */
    public Operator getOperator();
    
    /**
     * Sets this operator to value
     * 
     * @param value the value to set this operator to
     */
    public void setOperator(Operator value);
    
    /**
     * @return a String representation of a number
     */
    public String toString();
}
