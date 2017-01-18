package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class IStore extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x36, new IStore());
    }
    
    private int value;

    public IStore() {
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        Integer index = (Integer) vm.stackPop();
        vm.setValue(index, value);
        vm.incrementPc();
    }

    @Override
    public int bytes() {
        return 2;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        value = bytecode[pointer+1];
    }

    @Override
    public String toString() {
        return super.toString() + "value="+value;
    }
    
}
