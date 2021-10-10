package interpreter.ByteCode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LitCode extends ByteCode {
    int value;
    String varName = "";

    @Override
    public void init(ArrayList args) {
        this.value = Integer.parseInt((String) args.get(0));
        if (args.size() == 2)
            this.varName = (String) args.get(1);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println("LIT " + value + " " + varName);
        virtualMachine.getRunTimeStack().push(value);
    }
}