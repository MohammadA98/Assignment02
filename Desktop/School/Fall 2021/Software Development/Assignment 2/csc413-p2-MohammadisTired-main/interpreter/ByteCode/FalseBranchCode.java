package interpreter.ByteCode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends JumpCode {
    private String labelName;
    private int target;

    @Override
    public void init(ArrayList args) {
        labelName = (String) args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println("FALSEBRANCH " + labelName + " " + target);
        if (virtualMachine.getRunTimeStack().pop() == 0) {
            virtualMachine.setProgramCounter(target - 1);
        }

    }


    public void setTarget (int target) {
        this.target = target;
    }

    public String getLabelName() {
        return this.labelName;
    }

}