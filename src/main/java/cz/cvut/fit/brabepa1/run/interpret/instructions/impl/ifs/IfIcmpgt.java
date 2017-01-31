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
        Integer val2 = (Integer) frame.popOperand(); // condition end (for cycle with <)
        Integer val1 = (Integer) frame.popOperand(); // incrementor (for cycle with <)
        if (val1 > val2) {
            frame.addOffsetToPc(branchOffset);
        } else {
            frame.incrementPc();
        }
    }

}
