package interpreter.ByteCode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode {
    private int offset;

    @Override
    public void init(ArrayList args) {
        this.offset = Integer.parseInt((String) args.get(0));
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println("STORE " + offset);
        virtualMachine.getRunTimeStack().store(offset);
    }
}