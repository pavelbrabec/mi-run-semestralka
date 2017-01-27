package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.heap.ObjectRef;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class AReturn extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xb0, new AReturn());
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getInvoker().pushOperand((ObjectRef)frame.popOperand());
        // discard decreases reference so this allows to left the reference on its orig. value
        ((ObjectRef)frame.getInvoker().peekOperand()).addReference();
        frame.discard();
    }

}
