package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IfIcmple extends IfInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xA4, new IfIcmple());
    }

    @Override
    public void execute(StackFrame frame) {
        Integer value1 = (Integer) frame.popOperand();
        Integer value2 = (Integer) frame.popOperand();
        if (value2 <= value1) {
            frame.incrementPc();
        } else {
            frame.addOffsetToPc(branchOffset);
        }

    }

}
