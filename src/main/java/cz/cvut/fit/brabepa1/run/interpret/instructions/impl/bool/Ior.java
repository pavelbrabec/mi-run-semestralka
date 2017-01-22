package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.bool;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Ior extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x80, new Ior());
    }

    @Override
    public void execute(StackFrame frame) {
        Integer value1 = (Integer) frame.popOperand();
        Integer value2 = (Integer) frame.popOperand();
        Integer result = value1 | value2;
        frame.pushOperand(result);
        frame.incrementPc();
    }

}
