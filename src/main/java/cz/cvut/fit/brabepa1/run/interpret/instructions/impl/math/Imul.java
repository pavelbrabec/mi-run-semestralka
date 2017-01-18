package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.math;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Imul extends JavaInstruction{

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x68, new Imul());
    }

    @Override
    public void execute(VirtualMachine vm) {
        Integer value1 = (Integer) vm.stackPop();
        Integer value2 = (Integer) vm.stackPop();
        Integer result = value1 * value2;
        vm.stackPush(result);
        vm.incrementPc();
    }
}
