package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IfIcmplt extends IfInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xA1, new IfIcmplt());
    }

    public IfIcmplt() {
    }

    @Override
    public void execute(StackFrame frame) {
        Integer val1 = (Integer) frame.popOperand();
        Integer val2 = (Integer) frame.popOperand();
        if (val2 < val1) {
            frame.incrementPc();
        } else {
            frame.addOffsetToPc(branchOffset);
        }
    }

}
