package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.heap.ArrayRef;
import cz.cvut.fit.brabepa1.run.interpret.heap.Heap;
import cz.cvut.fit.brabepa1.run.interpret.heap.ObjectRef;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class AStore extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x3a, new AStore());
    }

    private int index;

    public AStore() {
    }

    @Override
    public void execute(StackFrame frame) {
        ArrayRef value = (ArrayRef) frame.popOperand();
        frame.setValue(index, value);
        frame.incrementPc();
    }

    @Override
    public int bytes() {
        return 2;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        index = bytecode[pointer + 1];
    }

    @Override
    public String toString() {
        return super.toString() + " index: " + index;
    }

}
