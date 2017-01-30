package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 *
 */
public class FConstX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x0b, new FConstX(0.0f));
        JavaInstructionFactory.getInstance().registerInstruction(0x0c, new FConstX(1.0f));
        JavaInstructionFactory.getInstance().registerInstruction(0x0d, new FConstX(2.0f));
    }

    private final float value;

    public FConstX(float value) {
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
