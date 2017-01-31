package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 */
public class FStoreX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x43, new FStoreX(0));
        JavaInstructionFactory.getInstance().registerInstruction(0x44, new FStoreX(1));
        JavaInstructionFactory.getInstance().registerInstruction(0x45, new FStoreX(2));
        JavaInstructionFactory.getInstance().registerInstruction(0x46, new FStoreX(3));
    }

    private final int index;

    public FStoreX(int index) {
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
