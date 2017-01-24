package cz.cvut.fit.brabepa1.run.interpret;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Method;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_UTF8;
import cz.cvut.fit.brabepa1.run.interpret.heap.ObjectRef;
import cz.cvut.fit.brabepa1.run.interpret.instructions.Instruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author pajcak
 */
public class StackFrame{

    private final StackFrame invoker;
    private final Stack<StackFrame> stackRef;
    private final ClassFile classFile;
    private final Method method;
    private int pc = 0;
    private final Instruction[] instructions;
    private Object[] values = new Object[4];
    private final LinkedList<Object> operandStack = new LinkedList<>();
    /*
     Pole pcToBc a bcToPc resi problem, branch offset u goto nebo if prikazu je 
     v bytech a ne v instrukcich. V konstruktoru si predpocitam hodnoty prevodu aby
     v runtimu jsem nemusel nic pocitat.
     */
    private int[] pcToBc;
    private int[] bcToPc;

    public StackFrame(Stack<StackFrame> stackRef, StackFrame invoker, ClassFile classFile, Method method) {
        this.invoker = invoker;
        this.classFile = classFile;
        this.method = method;
        this.stackRef = stackRef;
        List<Instruction> insts = JavaInstructionFactory.getInstance()
                .createInstructions(method.codeAttribute.code);
        this.instructions = insts.toArray(new Instruction[insts.size()]);

        setupBcAndPc();
//        System.out.println("pc2bc " + Arrays.toString(pcToBc));
//        System.out.println("bc2pc " + Arrays.toString(bcToPc));
        printInstructionSet();
    }

    private void setupBcAndPc() {
        pcToBc = new int[instructions.length];
        pcToBc[0] = 0;
        for (int i = 1; i < instructions.length; i++) {
            pcToBc[i] = pcToBc[i - 1] + instructions[i - 1].bytes();
        }

        int byteCodeLenght = 0;
        for (Instruction ins : instructions) {
            byteCodeLenght += ins.bytes();
        }
        bcToPc = new int[byteCodeLenght];
        for (int i = 0; i < bcToPc.length; i++) {
            bcToPc[i] = -1;
        }
        int tmpPc = 0;
        int bcPointer = 0;
        do {
            bcToPc[bcPointer] = tmpPc;
            bcPointer += instructions[tmpPc].bytes();
            tmpPc++;
        } while (tmpPc < instructions.length);

    }

    public Instruction nextInstruction() {
        if (pc < instructions.length) {
            return instructions[pc];
        }
        return null;
    }

    public StackFrame getInvoker() {
        return invoker;
    }

    public ClassFile getClassFile() {
        return classFile;
    }

    public Method getMethod() {
        return method;
    }

    public Object peekOperand() {
        return operandStack.peek();
    }

    public Object popOperand() {
        return operandStack.pop();
    }

    public void pushOperand(Object o) {
        operandStack.push(o);
    }

    public void incrementPc() {
        pc++;
    }

    public void addOffsetToPc(int offset) {
        if (pc < instructions.length) { // frame is not discarded yet
            int bcPointer = pcToBc[pc];
            bcPointer += offset;
            pc = bcToPc[bcPointer];
        }
    }

    public void setValue(int index, Object value) {
        if (index >= values.length) {
            values = Arrays.copyOf(values, index * 2);
        }
        values[index] = value;
    }

    public Object getValue(int index) {
        return values[index];
    }

    public Stack<StackFrame> getStackRef() {
        return stackRef;
    }

    // used mostly by <X>RETURN instructions
    public void discard() {
        for (int i = 0; i < operandStack.size(); i++) {
            Object o = operandStack.pop();
            // TODO if there are more heap types then ObjectRef, release them too
            if (o instanceof ObjectRef) {
                ((ObjectRef) o).release();
            }
        }
        // the next call to nextInstruction() will certainly return null (OK)
        pc = instructions.length;
    }

    @Override
    public String toString() {
        String invk = null;
        if (invoker != null) {
            invk = invoker.classFile.constantPool
                    .getItem(invoker.method.nameIndex, CP_UTF8.class).getStringContent();
        }
        if (pc >= instructions.length) {
            return "Frame(#" + System.identityHashCode(this) + ") -- DISCARDED";
        }
        return "Frame(#" + System.identityHashCode(this) + "){\n"
                + "\tinvoker=" + invk + ",\n"
                + "\tpc=" + pc + ",\n"
                + "\tbc=" + pcToBc[pc] + ",\n"
                + "\tvalues=" + Arrays.toString(values) + ",\n"
                + "\toperandStack=" + Arrays.toString(operandStack.toArray()) + "\n"
                + "}";
    }

    private void printInstructionSet() {
        System.out.println("Instruction set (instr | offset):");
        for (int i = 0; i < instructions.length; i++) {
            System.out.println((i + 1) + ". | " + pcToBc[i] + ": " + instructions[i]);
        }
    }
    
    
}
