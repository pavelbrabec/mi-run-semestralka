package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.StackFrame;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFileReader;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Method;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Class;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_MethodRef;
import cz.cvut.fit.brabepa1.run.interpret.heap.ObjectRef;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 * invoke instance method on object objectref and puts the result on the stack
 * (might be void); the method is identified by method reference index in
 * constant pool (indexbyte1 << 8 + indexbyte2)
 *
 */
public class InvokeSpecial extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xb7, new InvokeSpecial());
    }

    /**
     * Index to constant pool
     */
    private int cpIndex;

    @Override
    public void execute(StackFrame frame) {
        CP_MethodRef methodRef = frame.getClassFile().constantPool
                .getItem(cpIndex, CP_MethodRef.class);
        String className = frame.getClassFile().constantPool.getItem(
                methodRef.classIndex, CP_Class.class).getClassName();
        ClassFile cf = ClassFileReader.lookupAndResolve(className);
        Method invokedMethod = cf.getMethod(methodRef.getNameAndType().getName(),
                methodRef.getNameAndType().getDescriptor());
        
        int argCount = invokedMethod.getArgumentCount();
        StackFrame newFrame = new StackFrame(frame.getVM(), frame, cf, invokedMethod);
        // objectRef is stored in local var. 0 (this loop is 1 step longer than in invokeStatic)
        for (int i = argCount; i >= 0; i--) {
            newFrame.setValue(i, frame.popOperand());
        }
        frame.getVM().stackPush(newFrame);
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
