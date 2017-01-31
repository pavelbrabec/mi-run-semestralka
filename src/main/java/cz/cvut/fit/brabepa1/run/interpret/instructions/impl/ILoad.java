package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class ILoad extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x15, new ILoad());
    }

    private int index;

    public ILoad() {
    }

    @Override
    public void execute(StackFrame frame) {
        Object value = frame.getValue(index);
        frame.pushOperand(value);
        frame.incrementPc();
    }

    @Override
    public int bytes() {
        return 2;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        index=bytecode[pointer+1];
    }
    
    @Override
    public String toString() {
        return super.toString() + " " + index;
    }

}
