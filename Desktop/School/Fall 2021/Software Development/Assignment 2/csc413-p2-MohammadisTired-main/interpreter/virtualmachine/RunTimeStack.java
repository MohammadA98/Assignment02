package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.Stack;

public class RunTimeStack {

    private final ArrayList<Integer> runTimeStack;
    private final Stack<Integer>     framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        framePointer.add(0);
    }

    public void dump(){

    }
    public int push(int i) {
        runTimeStack.add(i);
        return i;
    }

    public int peek() {
        if(runTimeStack.isEmpty()){
            return 0;
        }
        return runTimeStack.get(runTimeStack.size() - 1);
    }


    public int pop() {
        if(runTimeStack.size() == 0){
            System.out.println("Empty runTimeStack.");
            System.exit(-2);
        }
        return runTimeStack.remove(runTimeStack.size() - 1);
    }


    public int store(int offset) {
        if(runTimeStack.isEmpty()){
            System.out.println("Empty runTimeStack.");
            System.exit(-4);
        }
        int n = pop();
        runTimeStack.add(framePointer.peek() - offset, n);
        return n;
    }

    public int load(int offset) {
        if(runTimeStack.isEmpty()){
            System.out.println("Empty runTimeStack.");
            System.exit(-5);
        }
        int n = runTimeStack.get(framePointer.peek() - offset);
        push(n);
        return n;
    }
    public void dump(VirtualMachine virtualMachine) {
        System.out.println(virtualMachine.getFrameSize());
    }

    public void newFrameAt(int offset) {
        framePointer.push(runTimeStack.size() - offset);
    }




    public int frames() {
        return framePointer.peek();
    }

    public int getFrameSize() {
        return runTimeStack.size() - framePointer.peek();
    }



    public int size() {
        return runTimeStack.size();
    }
}
