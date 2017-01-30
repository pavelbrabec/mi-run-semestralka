package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.heap.ObjectRef;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class AConstNull extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x01, new AConstNull());
    }

    @Override
    public void execute(StackFrame frame) {
        frame.pushOperand(new ObjectRef(null, -1L));
        frame.incrementPc();
    }

}
