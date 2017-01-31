package cz.cvut.fit.brabepa1.run.interpret;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.RootNode;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Method;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_UTF8;
import cz.cvut.fit.brabepa1.run.interpret.exceptions.MethodNotFound;
import cz.cvut.fit.brabepa1.run.interpret.instructions.Instruction;

/**
 *
 * @author pavel
 */
public class VirtualMachine extends RootNode {

    //@CompilerDirectives.CompilationFinal
    public static boolean VM_DEBUG = false;

    @Children
    private final StackFrame[] stack = new StackFrame[1000];

    public int stackPointer = 0;

    public VirtualMachine(ClassFile cf) throws MethodNotFound {
        super(TruffleLanguage.class, null, null);
        Method main = null;
        for (Method m : cf.methods) {
            if (cf.constantPool.getItem(m.nameIndex, CP_UTF8.class)
                    .getStringContent().equals("main")) {
                main = m;
                break;
            }
        }
        if (main == null) {
            throw new MethodNotFound("main");
        }
        stackPush(new StackFrame(this, null, cf, main));
    }

    public void stackPush(StackFrame sf) {
        stack[stackPointer] = sf;
        stackPointer++;
    }

    public boolean stackIsEmpty() {
        return stackPointer == 0;
    }

    public StackFrame stackPeek() {
        if (stackIsEmpty()) {
            throw new RuntimeException("Peek on empty stack");
        }
        return stack[stackPointer - 1];
    }

    public StackFrame stackPop() {
        if (stackIsEmpty()) {
            throw new RuntimeException("Pop on empty stack");
        }
        stackPointer--;
        StackFrame frame = stack[stackPointer];
        //stack[stackPointer] = null;
        return frame;
    }

    @ExplodeLoop
    @Override
    public Object execute(VirtualFrame vf) {
        while (!stackIsEmpty()) {
            StackFrame frame = stackPeek();
            Instruction instruction = frame.nextInstruction();
            if (instruction == null) {
                stackPop();
            } else {
                if (VM_DEBUG) {
                    System.out.println("Before " + frame);
                    System.out.println("==> " + instruction.toString() + " <==");
                }
                instruction.execute(frame);
                if (VM_DEBUG) {
                    System.out.println("After " + frame);
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "VirtualMachine{stack=\n" + stack + '}';
    }
}
