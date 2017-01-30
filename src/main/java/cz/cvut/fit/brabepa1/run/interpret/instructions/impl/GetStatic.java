package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Field;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class GetStatic extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xb2, new GetStatic());
    }

    /**
     * Index to constant pool
     */
    private int cpIndex;

    @Override
    public void execute(StackFrame frame) {
//        Field field = frame.getClassFile().getFieldWithLookup(cpIndex);
//        frame.pushOperand(field.getValue());
        //skip instruction
//        frame.incrementPc();
throw new UnsupportedOperationException();
    }

    @Override
    public int bytes() {
        return 3;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        cpIndex = branchoffset(bytecode[pointer + 1], bytecode[pointer + 2]);
    }

    @Override
    public String toString() {
        return super.toString() + " " + cpIndex;
    }

}
