package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IStore extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x36, new IStore());
    }

    private int valueIdx;

    public IStore() {
    }

    @Override
    public void execute(StackFrame frame) {
        Integer value = (Integer) frame.popOperand();
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
