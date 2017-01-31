package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 */
public class DStoreX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x47, new DStoreX(0));
        JavaInstructionFactory.getInstance().registerInstruction(0x48, new DStoreX(1));
        JavaInstructionFactory.getInstance().registerInstruction(0x49, new DStoreX(2));
        JavaInstructionFactory.getInstance().registerInstruction(0x4a, new DStoreX(3));
    }

    private final int index;

    public DStoreX(int index) {
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
