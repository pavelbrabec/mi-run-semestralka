package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Field;
import cz.cvut.fit.brabepa1.run.interpret.heap.ObjectRef;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

public class PutField extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xb5, new PutField());
    }

    /**
     * Index to constant pool
     */
    private int cpIndex;

    @Override
    public void execute(StackFrame frame) {
        Field field = frame.getClassFile().getFieldWithLookup(cpIndex);
        Object value = frame.popOperand();
        ObjectRef objRef = (ObjectRef)(frame.popOperand());
//        System.out.println(Heap.getInstance());
        objRef.setFieldValue(field, value);
//        System.out.println(Heap.getInstance());
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
