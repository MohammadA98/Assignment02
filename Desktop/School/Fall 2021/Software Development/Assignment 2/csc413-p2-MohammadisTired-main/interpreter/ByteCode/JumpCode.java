package interpreter.ByteCode;



public abstract class JumpCode extends ByteCode {

    abstract public void setTarget(int n);
    abstract public String getLabelName();}