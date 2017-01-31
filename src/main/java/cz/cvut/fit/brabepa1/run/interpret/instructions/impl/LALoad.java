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
public class LALoad extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x2f, new LALoad());
    }

    @Override
    public void execute(StackFrame frame) {
        Integer index = (Integer) frame.popOperand();
        ArrayRef arrRef = (ArrayRef)(frame.popOperand());
        Object elem = arrRef.getElem(index);
        frame.pushOperand(elem);
        frame.incrementPc();
    }
}
