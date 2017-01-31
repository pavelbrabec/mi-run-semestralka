package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 */
public class FStore extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x38, new FStore());
    }

    private int valueIdx;

    public FStore() {
    }

    @Override
    public void execute(StackFrame frame) {
        Float value = (Float) frame.popOperand();
        frame.setValue(valueIdx, value);
        frame.incrementPc();
    }

    @Override
    public int bytes() {
        return 2;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        valueIdx = bytecode[pointer + 1];
    }

    @Override
    public String toString() {
        return super.toString() + "valueIdx=" + valueIdx;
    }

}
