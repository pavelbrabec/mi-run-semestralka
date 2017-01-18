package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.bool;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Iand extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x7e, new Iand());
    }

    @Override
    public void execute(VirtualMachine vm) {
        Integer value1 = (Integer) vm.stackPop();
        Integer value2 = (Integer) vm.stackPop();
        Integer result = value1 & value2;
        vm.stackPush(result);
        vm.incrementPc();
    }

}
