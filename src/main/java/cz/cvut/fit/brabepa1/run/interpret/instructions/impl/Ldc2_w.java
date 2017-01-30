package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Double;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Item;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Item.Tag;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Long;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 */
public class Ldc2_w extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0x14, new Ldc2_w());
    }

    /**
     * Index to constant pool
     */
    private int cpIndex;

    @Override
    public void execute(StackFrame frame) {
        CP_Item item = frame.getClassFile().constantPool.getItem(cpIndex);
        if (item.tag == Tag.DOUBLE) {
            CP_Double dbl = (CP_Double)item;
            frame.pushOperand(dbl.value);
        } else { // LONG
            CP_Long lng = (CP_Long) item;
            frame.pushOperand(lng.value);
        }
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
