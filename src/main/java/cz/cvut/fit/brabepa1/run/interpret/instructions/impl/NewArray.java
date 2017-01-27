package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class NewArray extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xbc, new NewArray());
    }

    @Override
    public void execute(StackFrame frame) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int bytes() {
        return 2;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        //TODO set parametr
    }
    
}
