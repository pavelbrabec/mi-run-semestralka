package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.math;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Irem extends JavaInstruction{

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x70, new Irem());
    }

    @Override
    public void execute(VirtualMachine vm) {
        Integer value1 = (Integer) vm.stackPop();
        Integer value2 = (Integer) vm.stackPop();
        Integer result = value2 % value1;
        vm.stackPush(result);
        vm.incrementPc();
    }
}
