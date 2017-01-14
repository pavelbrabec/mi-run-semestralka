package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IfIcmple extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xA4, new IfIcmple());
    }

    private int branchOffset;

    @Override
    public void execute(VirtualMachine vm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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
