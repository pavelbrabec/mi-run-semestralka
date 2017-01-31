package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 */
public class LStore extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x37, new LStore());
    }

    private int valueIdx;

    public LStore() {
    }

    @Override
    public void execute(StackFrame frame) {
        Long value = (Long) frame.popOperand();
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
