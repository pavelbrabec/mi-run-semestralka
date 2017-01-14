package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class ILoadX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x1A, new ILoadX(0));
        JavaInstructionFactory.getInstance().registerInstruction(0x1B, new ILoadX(1));
        JavaInstructionFactory.getInstance().registerInstruction(0x1C, new ILoadX(2));
        JavaInstructionFactory.getInstance().registerInstruction(0x1D, new ILoadX(3));
    }

    private final int value;

    public ILoadX(int value) {
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
