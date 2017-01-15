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

    private final int index;

    public IStoreX(int index) {
        this.index = index;
    }

    @Override
    public void execute(VirtualMachine vm) {
        Object value = vm.stackPop();
        vm.setValue(index, value);
        vm.incrementPc();
    }

    @Override
    public String toString() {
        return super.toString() + " " + index;
    }

}
