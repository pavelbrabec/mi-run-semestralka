package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.math;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;
import cz.cvut.fit.brabepa1.run.interpret.instructions.impl.bool.Iand;

/**
 *
 * @author pavel
 */
public class Iadd extends JavaInstruction{

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x60, new Iadd());
    }

    @Override
    public void execute(VirtualMachine vm) {
        Integer value1 = (Integer) vm.stackPop();
        Integer value2 = (Integer) vm.stackPop();
        Integer result = value1 + value2;
        vm.stackPush(result);
        vm.incrementPc();
    }

}
