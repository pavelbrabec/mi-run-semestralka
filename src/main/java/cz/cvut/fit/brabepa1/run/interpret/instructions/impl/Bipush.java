package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Bipush extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x10, new Bipush());
    }

    private int byteValue;

    public Bipush() {
    }

    @Override
    public void execute(StackFrame frame) {
        frame.pushOperand(byteValue);
        frame.incrementPc();
    }

    @Override
    public int bytes() {
        return 2;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        byteValue = bytecode[pointer + 1];
    }

    @Override
    public String toString() {
        return super.toString() + " " + byteValue;
    }

}
