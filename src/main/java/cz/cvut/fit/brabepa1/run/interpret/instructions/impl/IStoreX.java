package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IStoreX extends JavaInstruction {

    static {
        //TODO promyslet 36 istore
        JavaInstructionFactory.getInstance().registerInstruction(0x3b, new IStoreX(0));
        JavaInstructionFactory.getInstance().registerInstruction(0x3c, new IStoreX(1));
        JavaInstructionFactory.getInstance().registerInstruction(0x3d, new IStoreX(2));
        JavaInstructionFactory.getInstance().registerInstruction(0x3e, new IStoreX(3));
    }

    private final int index;

    public IStoreX(int index) {
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
