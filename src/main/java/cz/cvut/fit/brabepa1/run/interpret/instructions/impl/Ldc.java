package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Float;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Integer;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Item;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_String;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_UTF8;
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
        CP_Item item = frame.getClassFile().constantPool.getItem(index);
        switch (item.tag) {
            case STRING:
                CP_String tmp = (CP_String) item;
                CP_UTF8 utf8 = (CP_UTF8) frame.getClassFile().constantPool.getItem(tmp.stringIndex);
                frame.pushOperand(utf8.string);
                break;
            case INTEGER:
                CP_Integer i = (CP_Integer) item;
                frame.pushOperand(i.value);
                break;
            case FLOAT:
                CP_Float f = (CP_Float) item;
                frame.pushOperand(f.value);
                break;
            default:
                throw new UnsupportedOperationException("Not supported, yet\t" + item.tag);
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
