package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 * goes to another instruction at branchoffset (signed short constructed from
 * unsigned bytes branchbyte1 << 8 + branchbyte2) @author pavel
 *
 */
public class Goto extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xA7, new Goto());
    }

    private int branchoffset;

    public Goto() {
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.addOffsetToPc(branchoffset);
    }

    @Override
    public int bytes() {
        return 3;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        branchoffset = branchoffset(bytecode[pointer + 1], bytecode[pointer + 2]);
    }
    
    @Override
    public String toString() {
        return super.toString() + " " + branchoffset;
    }

}
