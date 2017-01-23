package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IReturn extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xac, new IReturn());
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getInvoker().pushOperand((Integer)frame.popOperand());
        frame.discard();
    }

}
