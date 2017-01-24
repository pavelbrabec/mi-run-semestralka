package cz.cvut.fit.brabepa1.run.interpret;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Method;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_UTF8;
import cz.cvut.fit.brabepa1.run.interpret.exceptions.MethodNotFound;
import cz.cvut.fit.brabepa1.run.interpret.instructions.Instruction;
import java.util.Stack;

/**
 *
 * @author pavel
 */
public class VirtualMachine {

    private final Stack<StackFrame> stack = new Stack<>();

    public VirtualMachine(ClassFile cf) {
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
        stack.push(new StackFrame(stack, null, cf, main));
    }

    public Object run() {
        while (!stack.empty()) {
            StackFrame frame = stack.peek();
            Instruction instruction = frame.nextInstruction();
            if (instruction == null) {
                stack.pop();
            } else {
                System.out.println("Before " + frame);
                System.out.println("==> " + instruction.toString() + " <==");
                instruction.execute(frame);
                System.out.println("After " + frame);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "VirtualMachine{stack=\n" + stack + '}';
    }
}
