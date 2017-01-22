package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IfIcmpgt extends IfInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xA3, new IfIcmpgt());
    }

    public IfIcmpgt() {
    }

    @Override
    public void execute(StackFrame frame) {
        Integer val1 = (Integer) frame.popOperand();
        Integer val2 = (Integer) frame.popOperand();
        if (val2 > val1) {
            frame.incrementPc();
        } else {
            frame.addOffsetToPc(branchOffset);
        }
    }

}
