package cz.cvut.fit.brabepa1.run.interpret.instructions;

import java.util.List;

/**
 *
 * @author pavel
 */
public interface InstructionFactory {

    List<Instruction> createInstructions(byte[] bytecode);
    
}
