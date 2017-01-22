package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Return extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xB1, new Return());
    }

    @Override
    public void execute(StackFrame frame) {
        throw new UnsupportedOperationException("Not supported yet.");  
    }

}
