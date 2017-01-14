package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Nop extends JavaInstruction{

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x0, new Nop());
    }

    @Override
    public void execute(VirtualMachine vm) {
    }
    
}
