package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs;


import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;
import cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs.IfIcmplt;
import cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs.IfInstruction;

/**
 *
 * @author pavel
 */
public class IfIcmpgt extends IfInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xA3, new IfIcmpgt());
    }

    public IfIcmpgt() {
    }

    @Override
    public void execute(VirtualMachine vm) {
        Integer val1 = (Integer) vm.stackPop();
        Integer val2 = (Integer) vm.stackPop();
        if (val2 > val1) {
            vm.incrementPc();
        } else {
            vm.addOffsetToPc(branchOffset);
        }
    }

}
