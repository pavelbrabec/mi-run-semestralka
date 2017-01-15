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

    private final int index;

    public ILoadX(int index) {
        this.index = index;
    }

    @Override
    public void execute(VirtualMachine vm) {
        Object value = vm.getValue(index);
        vm.stackPush(value);
        vm.incrementPc();
    }

    @Override
    public String toString() {
        return super.toString() + " " + index;
    }

}
