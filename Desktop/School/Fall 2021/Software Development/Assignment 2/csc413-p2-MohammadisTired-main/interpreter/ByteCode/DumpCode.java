package interpreter.ByteCode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode {
    boolean isRunning;

    @Override
    public void init(ArrayList args) {
        if ((String) args.get(0) == "Running") {
            isRunning = true;
        } else {
            isRunning = false;
        }
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.getRunTimeStack().dump(virtualMachine);
    }
}