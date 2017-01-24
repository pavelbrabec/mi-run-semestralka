package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import com.sun.org.apache.bcel.internal.generic.CPInstruction;
import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Item;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_UTF8;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.ConstantPool;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pavel
 */
public class Ldc extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x12, new Ldc());
    }

    private int index;

    public Ldc() {
    }

    @Override
    public void execute(StackFrame frame) {
        CP_Item item = frame.getClassFile().constantPool.items[index];
        switch (item.tag) {
            case UTF8:
                CP_UTF8 tmp = (CP_UTF8)item;
                frame.pushOperand(tmp.string);
                break;
            default:
                throw new UnsupportedOperationException("Not supported, yet");
        }
        frame.incrementPc();
    }

    @Override
    public int bytes() {
        return 2;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        index = bytecode[pointer + 1];
    }

    @Override
    public String toString() {
        return super.toString() + " " + index;
    }
}
