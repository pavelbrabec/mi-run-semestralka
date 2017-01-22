package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.math;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Isub extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x64, new Isub());
    }

    @Override
    public void execute(StackFrame frame) {
        Integer value1 = (Integer) frame.popOperand();
        Integer value2 = (Integer) frame.popOperand();
        Integer result = value2 - value1;
        frame.pushOperand(result);
        frame.incrementPc();
    }
}
