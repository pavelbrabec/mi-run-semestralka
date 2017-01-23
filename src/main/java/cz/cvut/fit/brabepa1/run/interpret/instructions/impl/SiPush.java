package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class SiPush extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x11, new SiPush());
    }

    private int shortValue;

    public SiPush() {
    }

    @Override
    public void execute(StackFrame frame) {
        frame.pushOperand(shortValue);
        frame.incrementPc();
    }

    @Override
    public int bytes() {
        return 3;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        shortValue = ((bytecode[pointer+1] & 0xFF) << 8) + (bytecode[pointer+2] & 0xFF);
    }

    @Override
    public String toString() {
        return super.toString() + " " + shortValue;
    }
}
