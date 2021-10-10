package interpreter.ByteCode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends ByteCode {
    private String labelName;

    @Override
    public void init(ArrayList args) {
        this.labelName = (String) args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println("LABEL " + labelName);

    }

    public String getLabelName() {
        return this.labelName;
    }
}