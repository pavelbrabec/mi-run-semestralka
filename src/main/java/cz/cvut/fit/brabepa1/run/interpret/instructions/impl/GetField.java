package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Field;
import cz.cvut.fit.brabepa1.run.interpret.heap.Heap;
import cz.cvut.fit.brabepa1.run.interpret.heap.ObjectRef;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 */
public class GetField extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xb4, new GetField());
    }

    /**
     * Index to constant pool
     */
    private int cpIndex;

    @Override
    public void execute(StackFrame frame) {
        Field field = frame.getClassFile().getFieldWithLookup(cpIndex);
        ObjectRef objRef = (ObjectRef)(frame.popOperand());
        frame.pushOperand(objRef.getFieldValue(field)); 
        frame.incrementPc();
    }

    @Override
    public int bytes() {
        return 3;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        cpIndex = branchoffset(bytecode[pointer + 1], bytecode[pointer + 2]);
    }

    @Override
    public String toString() {
        return super.toString() + " " + cpIndex;
    }

}
