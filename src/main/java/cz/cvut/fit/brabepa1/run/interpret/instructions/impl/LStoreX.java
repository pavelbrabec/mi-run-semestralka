package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 */
public class LStoreX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x3f, new LStoreX(0));
        JavaInstructionFactory.getInstance().registerInstruction(0x40, new LStoreX(1));
        JavaInstructionFactory.getInstance().registerInstruction(0x41, new LStoreX(2));
        JavaInstructionFactory.getInstance().registerInstruction(0x42, new LStoreX(3));
    }

    private final int index;

    public LStoreX(int index) {
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        Object value = frame.popOperand();
        frame.setValue(index, value);
        frame.incrementPc();
    }

    @Override
    public String toString() {
        return super.toString() + " " + index;
    }

}
