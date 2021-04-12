package calculator;

public interface Number
{    
    public double getReal();
    public void setReal(double d);
    public Operator getOperator();
    public void setOperator(Operator op);
    public String toString();
}
