package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 */
public class DConstX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x0e, new DConstX(0.0));
        JavaInstructionFactory.getInstance().registerInstruction(0x0f, new DConstX(1.0));
    }

    private final double value;

    public DConstX(double value) {
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
