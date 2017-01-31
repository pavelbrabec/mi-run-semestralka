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
public class LAStore extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x50, new LAStore());
    }

    @Override
    public void execute(StackFrame frame) {
        Object value = frame.popOperand();
        Integer index = (Integer) frame.popOperand();
        ArrayRef arrRef = (ArrayRef)(frame.popOperand());
        arrRef.setElem(index, value);
        frame.incrementPc();
    }
}
