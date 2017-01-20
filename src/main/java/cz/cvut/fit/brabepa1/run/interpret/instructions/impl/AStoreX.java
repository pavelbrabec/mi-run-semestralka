package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.heap.ObjectRef;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class AStoreX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x4b, new AStoreX(0));
        JavaInstructionFactory.getInstance().registerInstruction(0x4c, new AStoreX(1));
        JavaInstructionFactory.getInstance().registerInstruction(0x4d, new AStoreX(2));
        JavaInstructionFactory.getInstance().registerInstruction(0x4e, new AStoreX(3));
    }

    private final int index;

    public AStoreX(int index) {
        this.index = index;
    }

    @Override
    public void execute(VirtualMachine vm) {
        ObjectRef value = (ObjectRef) vm.stackPop();
        vm.setValue(index, value);
        vm.incrementPc();
    }

    @Override
    public String toString() {
        return super.toString() + " " + index;
    }

}
