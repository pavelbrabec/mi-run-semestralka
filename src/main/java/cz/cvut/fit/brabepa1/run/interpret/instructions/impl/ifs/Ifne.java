package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Ifne extends IfInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x9A, new Ifne());
    }

    @Override
    public void execute(VirtualMachine vm) {
        int val1 = (Integer) vm.stackPop();
        if (val1 != 0) {
            vm.addOffsetToPc(branchOffset);
        } else {
            vm.incrementPc();
        }
    }

}
