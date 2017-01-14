package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IStoreX extends JavaInstruction {

    static {
        //TODO promyslet 36 istore
        JavaInstructionFactory.getInstance().registerInstruction(0x3b, new IStoreX(0));
        JavaInstructionFactory.getInstance().registerInstruction(0x3c, new IStoreX(1));
        JavaInstructionFactory.getInstance().registerInstruction(0x3d, new IStoreX(2));
        JavaInstructionFactory.getInstance().registerInstruction(0x3e, new IStoreX(3));
    }

    private final int value;

    public IStoreX(int value) {
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
