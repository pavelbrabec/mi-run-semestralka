package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IfIcmpne extends IfInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xA0, new IfIcmpne());
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
