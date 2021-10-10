package interpreter.ByteCode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode {
    private int poppedValues;

    @Override
    public void init(ArrayList args) {
        this.poppedValues = Integer.parseInt((String) args.get(0));
    }

    public String toString(){
        return "PopCode{"+
                "value="+ poppedValues+
                "}";
    }
    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println("POP " + poppedValues);
        if (this.poppedValues > virtualMachine.getFrameSize())
            this.poppedValues = virtualMachine.getFrameSize();

        while (this.poppedValues > 0) {
            this.poppedValues--;
            virtualMachine.getRunTimeStack().pop();
        }
    }
}