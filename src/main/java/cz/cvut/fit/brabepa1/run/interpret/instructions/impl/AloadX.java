package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class AloadX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x2A, new ILoadX(0));
        JavaInstructionFactory.getInstance().registerInstruction(0x2B, new ILoadX(1));
        JavaInstructionFactory.getInstance().registerInstruction(0x2C, new ILoadX(2));
        JavaInstructionFactory.getInstance().registerInstruction(0x2D, new ILoadX(3));
    }

    @Override
    public void execute(StackFrame frame) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
