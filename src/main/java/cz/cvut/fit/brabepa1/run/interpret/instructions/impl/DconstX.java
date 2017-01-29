package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 */
public class DconstX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x0e, new DconstX(0.0));
        JavaInstructionFactory.getInstance().registerInstruction(0x0f, new DconstX(1.0));
    }

    private final double value;

    public DconstX(double value) {
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
