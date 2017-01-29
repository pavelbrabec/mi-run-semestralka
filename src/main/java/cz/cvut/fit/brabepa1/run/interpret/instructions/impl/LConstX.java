package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class LConstX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x09, new LConstX(0L));
        JavaInstructionFactory.getInstance().registerInstruction(0x0A, new LConstX(1L));
    }

    private final long value;

    public LConstX(long value) {
        this.value = value;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.pushOperand(value);
        frame.incrementPc();
    }

    @Override
    public String toString() {
        return super.toString() + " " + value;
    }
}
