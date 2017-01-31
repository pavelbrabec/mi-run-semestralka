package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.heap.ArrayRef;
import cz.cvut.fit.brabepa1.run.interpret.heap.Heap;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class NewArray extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xbc, new NewArray());
    }
    private int type;

    @Override
    public void execute(StackFrame frame) {
        Integer arraySize = (Integer)frame.popOperand();
        ArrayRef arrRef = Heap.getInstance().allocArray(type, arraySize);
        frame.pushOperand(arrRef);
        frame.incrementPc();
    }

    @Override
    public int bytes() {
        return 2;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        type = bytecode[pointer + 1];
    }
    
}
