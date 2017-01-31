package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.heap.ArrayRef;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 *
 */
public class IAStore extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x4f, new IAStore());
    }

    @Override
    public void execute(StackFrame frame) {
        Integer value = (Integer) frame.popOperand();
        Integer index = (Integer) frame.popOperand();
        ArrayRef arrRef = (ArrayRef)(frame.popOperand());
        arrRef.setElem(index, value);
        frame.incrementPc();
    }
}
