package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 * invoke instance method on object objectref and puts the result on the stack
 * (might be void); the method is identified by method reference index in
 * constant pool (indexbyte1 << 8 + indexbyte2)
 *
 */
public class InvokeSpecial extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xb7, new InvokeSpecial());
    }

    /**
     * Index to constant pool
     */
    private int cpIndex;

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
        cpIndex = branchoffset(bytecode[pointer + 1], bytecode[pointer + 2]);
    }

    @Override
    public String toString() {
        return super.toString() + " " + cpIndex;
    }

}
