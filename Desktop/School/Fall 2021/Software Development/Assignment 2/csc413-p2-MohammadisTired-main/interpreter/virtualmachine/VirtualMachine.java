package interpreter.virtualmachine;
import java.util.Stack;
import interpreter.ByteCode.ByteCode;
import interpreter.ByteCode.PopCode;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private final Program  program;
    private int            programCounter;
    private boolean        isRunning;

    public VirtualMachine(Program program) {
        this.program = program;
    }
    public void executeProgram() {
        programCounter = 0;
        runTimeStack = new RunTimeStack();
        returnAddress = new Stack<>();
        isRunning = true;

        ByteCode bc = new PopCode();
        System.out.println(bc);
        while (isRunning) {
            ByteCode code = program.getCode(programCounter);
            code.execute(this);
            programCounter++;
            System.out.println("\n");
            runTimeStack.frames();
            runTimeStack.size();
            runTimeStack.getFrameSize();
        }
    }

    public void toggleIsRunning() {
        isRunning = false;
    }

    public void pushReturnAddress() {
        returnAddress.push(programCounter);
    }

    public int popReturnAddress() {
        return returnAddress.pop();
    }

    public void setProgramCounter(int i) {
        this.programCounter = i;
    }

    public RunTimeStack getRunTimeStack() {
        return runTimeStack;
    }

    public int getFrameSize () {
        return this.runTimeStack.getFrameSize();
    }




}

