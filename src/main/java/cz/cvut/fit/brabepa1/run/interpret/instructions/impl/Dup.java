package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Dup extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x59, new Dup());
    }

    public Dup() {
    }

    @Override
    public void execute(VirtualMachine vm) {
        Object value = vm.stackPop();
        vm.stackPush(value);
        vm.stackPush(value);
        vm.incrementPc();
    }
}
