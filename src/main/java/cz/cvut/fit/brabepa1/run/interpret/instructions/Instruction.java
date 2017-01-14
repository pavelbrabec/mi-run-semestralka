package cz.cvut.fit.brabepa1.run.interpret.instructions;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;

/**
 *
 * @author pavel
 */
public interface Instruction {

    /**
     * Executes instruction in VM
     *
     * @param vm
     */
    public void execute(VirtualMachine vm);

    /**
     * lenght of instruction in bytes
     *
     * @return
     */
    public int bytes();

    
    public void setParameters(int pointer, byte[] bytecode);
        
}
