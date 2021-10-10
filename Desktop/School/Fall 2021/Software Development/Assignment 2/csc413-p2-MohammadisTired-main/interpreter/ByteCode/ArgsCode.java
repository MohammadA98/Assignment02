package interpreter.ByteCode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode {
    private int numOfArg;

    @Override
    public void init(ArrayList args) {
        numOfArg = Integer.parseInt((String) args.get(0));
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println("ARGS " + numOfArg);
        virtualMachine.getRunTimeStack().newFrameAt(numOfArg);
    }
}