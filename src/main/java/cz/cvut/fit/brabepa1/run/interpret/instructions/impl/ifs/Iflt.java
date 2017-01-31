package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 */
public class Iflt extends IfInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x9b, new Iflt());
    }

    @Override
    public void execute(StackFrame frame) {
        int val1 = (Integer) frame.popOperand();
        if (val1 < 0) {
            frame.addOffsetToPc(branchOffset);
        } else {
            frame.incrementPc();
        }
    }
}
