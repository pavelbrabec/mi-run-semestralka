package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IfIcmpeq extends IfInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xA0, new IfIcmpne());
    }

    public IfIcmpeq() {
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        int val1 = (Integer) vm.stackPop();
        int val2 = (Integer) vm.stackPop();
        if (val1 != val2) {
            vm.incrementPc();
        } else {
            vm.addOffsetToPc(branchOffset);
        }
    }

}
