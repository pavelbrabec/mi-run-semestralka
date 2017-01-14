package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.Instruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class LconstX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x09, new LconstX(0L));
        JavaInstructionFactory.getInstance().registerInstruction(0x0A, new LconstX(1L));
    }

    private final long value;

    public LconstX(long value) {
        this.value = value;
    }

    @Override
    public void execute(VirtualMachine vm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return super.toString() + " " + value;
    }
}
