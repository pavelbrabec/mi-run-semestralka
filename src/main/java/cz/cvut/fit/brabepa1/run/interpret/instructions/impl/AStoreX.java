package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class AStoreX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x4b, new AStoreX(0));
        JavaInstructionFactory.getInstance().registerInstruction(0x4c, new AStoreX(1));
        JavaInstructionFactory.getInstance().registerInstruction(0x4d, new AStoreX(2));
        JavaInstructionFactory.getInstance().registerInstruction(0x4e, new AStoreX(3));
    }

    private final int index;

    public AStoreX(int index) {
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
