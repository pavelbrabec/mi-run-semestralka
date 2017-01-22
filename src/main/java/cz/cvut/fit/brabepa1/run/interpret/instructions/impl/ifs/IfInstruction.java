package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;

/**
 *
 * @author pavel
 */
public abstract class IfInstruction extends JavaInstruction{

    protected int branchOffset;

    public IfInstruction() {
    }
    
    @Override
    public abstract void execute(StackFrame frame);

    @Override
    public int bytes() {
        return 3;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        branchOffset = branchoffset(bytecode[pointer+1], bytecode[pointer+2]);
    }

    @Override
    public String toString() {
        return super.toString() + " " + branchOffset;
    }
    
}
