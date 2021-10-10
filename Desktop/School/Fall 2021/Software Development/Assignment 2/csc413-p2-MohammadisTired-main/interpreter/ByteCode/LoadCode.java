package interpreter.ByteCode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LoadCode extends ByteCode {
    private int offset;
    private String varName;

    @Override
    public void init(ArrayList args) {
        this.offset = Integer.parseInt((String) args.get(0));
        this.varName = (String) args.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println("LOAD " + offset + " " + varName);
        virtualMachine.getRunTimeStack().load(offset);
    }
}