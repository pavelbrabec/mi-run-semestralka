package cz.cvut.fit.brabepa1.run.interpret.instructions.impl;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFileReader;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Class;
import cz.cvut.fit.brabepa1.run.interpret.heap.Heap;
import cz.cvut.fit.brabepa1.run.interpret.heap.ObjectRef;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;

/**
 *
 * @author pajcak
 */
public class New extends JavaInstruction {

    static {
        JavaInstructionFactory.getInstance().registerInstruction(0xbb, new New());
    }

    private int objIndex;

    public New() {
    }
    
// create object on the heap (with ref count) and then push its ref onto the stack
    @Override
    public void execute(VirtualMachine vm) {
        String className = vm.getClassFile().constantPool
                .getItem(objIndex, CP_Class.class).getClassName();
        ClassFile cf = ClassFileReader.lookupAndResolve(className);
        ObjectRef objRef = Heap.allocObject(cf);
        vm.stackPush(objRef);
        vm.incrementPc();
    }

    @Override
    public int bytes() {
        return 3;
    }

    @Override
    public void setParameters(int pointer, byte[] bytecode) {
        objIndex = branchoffset(bytecode[pointer + 1], bytecode[pointer + 2]);
    }

    @Override
    public String toString() {
        return super.toString() + " " + objIndex;
    }

}
