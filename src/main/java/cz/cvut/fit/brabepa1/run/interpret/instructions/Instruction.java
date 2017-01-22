package cz.cvut.fit.brabepa1.run.interpret.instructions;

import com.oracle.truffle.api.nodes.NodeInterface;
import cz.cvut.fit.brabepa1.run.interpret.StackFrame;

/**
 *
 * @author pavel
 */
public interface Instruction extends NodeInterface{

    /**
     * Executes instruction in frame
     *
     * @param frame
     */
    public void execute(StackFrame frame);

    /**
     * lenght of instruction in bytes
     *
     * @return
     */
    public int bytes();

    
    public void setParameters(int pointer, byte[] bytecode);
        
}
