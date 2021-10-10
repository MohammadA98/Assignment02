package interpreter.ByteCode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends JumpCode {
    private String labelName;
    private int target;

    @Override
    public void init(ArrayList args) {
        this.labelName = (String) args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println("CALL " + labelName + " " + target);
        virtualMachine.pushReturnAddress();
        virtualMachine.setProgramCounter(target - 1);
    }

    public void setTarget (int target) {
        this.target = target;
    }

    public String getLabelName() {
        return this.labelName;
    }

    public int getTarget() {
        return this.target;
    }
}