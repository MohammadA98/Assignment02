package interpreter.virtualmachine;

import interpreter.ByteCode.ByteCode;
import interpreter.ByteCode.JumpCode;
import interpreter.ByteCode.LabelCode;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {

    private ArrayList<ByteCode> program;
    private static HashMap<String, Integer> addresses = new HashMap<>();

    public Program() {
        program = new ArrayList<>();
    }

    protected ByteCode getCode(int programCounter) {
        return this.program.get(programCounter);
    }

    private int getSize() {
        return this.program.size();
    }

    public void addCode(ByteCode byteCode) {
        this.program.add(byteCode);

        if (byteCode instanceof LabelCode) {
            addresses.put(((LabelCode) byteCode).getLabelName(), getSize());
        }
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter
     * HINT: make note what type of data-structure ByteCodes are stored in.
     * @param program
     */
    public void resolveAddress(Program program) {
        for (ByteCode byteCode : this.program) {

            if (byteCode instanceof JumpCode) {
                String labelName = ((JumpCode) byteCode).getLabelName();
                if (addresses.containsKey(labelName))
                    ((JumpCode) byteCode).setTarget(addresses.get(labelName));
            }

        }

    }
}
