package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import com.oracle.truffle.api.CompilerDirectives;
import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Class;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Item;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_MethodRef;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_UTF8;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;
import java.io.IOException;

/**
 * invoke virtual method on object objectref and puts the result on the stack
 * (might be void); the method is identified by method reference index in
 * constant pool (indexbyte1 << 8 + indexbyte2) @author pavel
 *
 */
public class InvokeVirtual extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xb6, new InvokeVirtual());
    }

    private static String OUTPUT = "java/io/PrintStream";
    private static String INPUT = "java/io/InputStream";

    /**
     * Index to constant pool
     */
    private int cpIndex;

    @Override
    @CompilerDirectives.TruffleBoundary
    public void execute(StackFrame frame) {
        CP_Item item = frame.getClassFile().constantPool.items[cpIndex - 1];
        switch (item.tag) {
            case METHODREF:
                CP_MethodRef mrf = (CP_MethodRef) item;
                CP_Class cpClass = (CP_Class) frame.getClassFile().constantPool.items[mrf.classIndex - 1];
                CP_UTF8 name = (CP_UTF8) frame.getClassFile().constantPool.items[cpClass.nameIndex - 1];
                if (OUTPUT.equalsIgnoreCase(name.string)) {
                    Object value = frame.popOperand();
                    System.out.println("VirtualMachine-output:\t" + value);
                } else if (INPUT.equalsIgnoreCase(name.string)) {
                    try {
                        Integer i = System.in.read();
                        frame.pushOperand(i);
                    } catch (IOException ex) {
                        System.out.println("Can not read from input.");
                        ex.printStackTrace();
                    }
                } else {
//                    used to kill all other method invocation which were not OUTPUT or INPUT
//                    throw new UnsupportedOperationException("Class " + name.string + " is not supported for output");
                }
                break;
            default:
                throw new UnsupportedOperationException("Not supported, yet");
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
