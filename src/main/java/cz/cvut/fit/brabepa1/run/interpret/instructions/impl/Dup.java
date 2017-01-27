package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.heap.ObjectRef;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Dup extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x59, new Dup());
    }

    public Dup() {
    }

    @Override
    public void execute(StackFrame frame) {
        Object value = frame.peekOperand();
        if (value instanceof ObjectRef) {
            ((ObjectRef)value).addReference();
        }
        frame.pushOperand(value);
        frame.incrementPc();
    }
}
