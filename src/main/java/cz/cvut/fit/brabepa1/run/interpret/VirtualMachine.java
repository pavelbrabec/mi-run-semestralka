package cz.cvut.fit.brabepa1.run.interpret;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Method;
import cz.cvut.fit.brabepa1.run.interpret.instructions.Instruction;
import java.util.Stack;

/**
 *
 * @author pavel
 */
public class VirtualMachine {

    private final Stack<StackFrame> stack = new Stack<>();

    public VirtualMachine(ClassFile cf) {
        Method main = cf.methods[1];
        stack.push(new StackFrame(stack, null, cf, main));
    }

    public Object run() {
        while (!stack.empty()) {
            StackFrame frame = stack.peek();
            Instruction instruction = frame.nextInstruction();
            if (instruction == null) {
                stack.pop();
            } else {
                System.out.println("==> " + instruction.toString() + " <==");
                instruction.execute(frame);
                System.out.println(frame);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "VirtualMachine{stack=\n" + stack + '}';
    }
}
