package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class ILoadX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x1A, new ILoadX(0));
        JavaInstructionFactory.getInstance().registerInstruction(0x1B, new ILoadX(1));
        JavaInstructionFactory.getInstance().registerInstruction(0x1C, new ILoadX(2));
        JavaInstructionFactory.getInstance().registerInstruction(0x1D, new ILoadX(3));
    }

    private final int index;

    public ILoadX(int index) {
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        Object value = frame.getValue(index);
        frame.pushOperand(value);
        frame.incrementPc();
    }

    @Override
    public String toString() {
        return super.toString() + " " + index;
    }

}
