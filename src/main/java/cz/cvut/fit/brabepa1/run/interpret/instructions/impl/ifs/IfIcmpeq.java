package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IfIcmpeq extends IfInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x9F, new IfIcmpeq());
    }

    public IfIcmpeq() {
    }

    @Override
    public void execute(StackFrame frame) {
        int val1 = (Integer) frame.popOperand();
        int val2 = (Integer) frame.popOperand();
        if (val1 != val2) {
            frame.incrementPc();
        } else {
            frame.addOffsetToPc(branchOffset);
        }
    }

}
