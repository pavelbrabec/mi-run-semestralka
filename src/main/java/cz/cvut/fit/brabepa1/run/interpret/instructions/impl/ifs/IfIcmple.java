package cz.cvut.fit.brabepa1.run.interpret.instructions.impl.ifs;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IfIcmple extends IfInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xA4, new IfIcmple());
    }

    @Override
    public void execute(VirtualMachine vm) {
        Integer value1 = (Integer)vm.stackPop();
        Integer value2 = (Integer)vm.stackPop();
        if(value1 <= value2){
            vm.incrementPc();
        }else{
            vm.addOffsetToPc(branchOffset);
        }
        
    }

}
