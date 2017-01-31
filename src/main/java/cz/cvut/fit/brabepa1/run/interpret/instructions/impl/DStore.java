package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 */
public class DStore extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x39, new DStore());
    }

    private int valueIdx;

    public DStore() {
    }

    @Override
    public void execute(StackFrame frame) {
        Double value = (Double) frame.popOperand();
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
