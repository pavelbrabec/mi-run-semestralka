package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class ALoadX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x2A, new ALoadX(0));
        JavaInstructionFactory.getInstance().registerInstruction(0x2B, new ALoadX(1));
        JavaInstructionFactory.getInstance().registerInstruction(0x2C, new ALoadX(2));
        JavaInstructionFactory.getInstance().registerInstruction(0x2D, new ALoadX(3));
    }

    private final int index;

    public ALoadX(int index) {
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
