package interpreter.ByteCode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode {

    @Override
    public void init(ArrayList args) {

    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println("RETURN");
        virtualMachine.setProgramCounter(virtualMachine.popReturnAddress());
    }
}