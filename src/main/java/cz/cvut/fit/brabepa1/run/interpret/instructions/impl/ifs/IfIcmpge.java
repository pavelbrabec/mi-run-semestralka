package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IfIcmpge extends IfInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xA2, new IfIcmpge());
    }
    
    public IfIcmpge() {
    }

    @Override
    public void execute(VirtualMachine vm) {
        Integer val1 = (Integer)vm.stackPop();
        Integer val2 = (Integer)vm.stackPop();
        if(val2 >= val1){
            vm.incrementPc();
        }else{
            vm.addOffsetToPc(branchOffset);
        }
    }

}
