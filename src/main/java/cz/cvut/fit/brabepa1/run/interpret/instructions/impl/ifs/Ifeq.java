package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Ifeq extends IfInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x99, new Ifeq());
    }

    @Override
    public void execute(StackFrame frame) {
        int val1 = (Integer) frame.popOperand();
        if (val1 == 0) {
            frame.addOffsetToPc(branchOffset);
        } else {
            frame.incrementPc();
        }
    }
}
