package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.math;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 */
public class Ineg extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x74, new Ineg());
    }

    @Override
    public void execute(StackFrame frame) {
        Integer value = (Integer) frame.popOperand();
        frame.pushOperand(value * -1);
        frame.incrementPc();
    }

}
