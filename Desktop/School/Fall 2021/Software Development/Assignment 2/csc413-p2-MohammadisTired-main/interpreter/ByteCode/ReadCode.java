package interpreter.ByteCode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

import java.util.Scanner;

public class ReadCode extends ByteCode {

    @Override
    public void init(ArrayList args) {

    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println("READ");
        System.out.print("Please enter an integer: ");
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();
        virtualMachine.getRunTimeStack().push(value);
    }
}