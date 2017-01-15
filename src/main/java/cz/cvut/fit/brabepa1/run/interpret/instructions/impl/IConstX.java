package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 *
 */
public class IConstX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(2, new IConstX(-1));
        JavaInstructionFactory.getInstance().registerInstruction(3, new IConstX(0));
        JavaInstructionFactory.getInstance().registerInstruction(4, new IConstX(1));
        JavaInstructionFactory.getInstance().registerInstruction(0x5, new IConstX(2));
        JavaInstructionFactory.getInstance().registerInstruction(0x6, new IConstX(3));
        JavaInstructionFactory.getInstance().registerInstruction(0x7, new IConstX(4));
        JavaInstructionFactory.getInstance().registerInstruction(0x8, new IConstX(5));
    }

    private final int value;

    public IConstX(int value) {
        this.value = value;
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.stackPush(value);
        vm.incrementPc();
    }

    @Override
    public String toString() {
        return super.toString() + " " + value;
    }
}
