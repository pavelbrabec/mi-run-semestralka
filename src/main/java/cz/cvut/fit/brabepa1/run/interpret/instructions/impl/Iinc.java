package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Iinc extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x84, new Iinc());
    }

    private int index;
    private int constant;

    public Iinc() {
    }

    public void setCnst(int cnst) {
        this.constant = cnst;
    }

    public void setIdx(int idx) {
        this.index = idx;
    }

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
        index = bytecode[pointer + 1];
        constant = bytecode[pointer + 2];
    }

    @Override
    public String toString() {
        return super.toString() + " " + index+ " " + constant;
    }

}
