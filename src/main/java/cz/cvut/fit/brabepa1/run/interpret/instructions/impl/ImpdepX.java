package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class ImpdepX extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xFF, new ImpdepX());
        JavaInstructionFactory.getInstance().registerInstruction(0xFF, new ImpdepX());
    }

    @Override
    public void execute(VirtualMachine vm) {
    }

}
