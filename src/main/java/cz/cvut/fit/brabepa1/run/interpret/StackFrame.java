package cz.cvut.fit.brabepa1.run.interpret;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Method;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_UTF8;
import cz.cvut.fit.brabepa1.run.interpret.instructions.Instruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author pajcak
 */
public class StackFrame {

    private final StackFrame invoker;
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

    public StackFrame(StackFrame invoker, ClassFile classFile, Method method) {
        this.invoker = invoker;
        this.classFile = classFile;
        this.method = method;
        List<Instruction> insts = JavaInstructionFactory.getInstance()
                .createInstructions(method.codeAttribute.code);
        this.instructions = insts.toArray(new Instruction[insts.size()]);

        calculateBcAndPc();
        System.out.println("pc2bc " + Arrays.toString(pcToBc));
        System.out.println("bc2pc " + Arrays.toString(bcToPc));
    }

    private void calculateBcAndPc() {
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
        int bcPointer = pcToBc[pc];
        bcPointer += offset;
        pc = bcToPc[bcPointer];
    }

    public void setValue(int index, Object value) {
        values[index] = value;
    }

    public Object getValue(int index) {
        return values[index];
    }

    @Override
    public String toString() {
        return "Frame{\n"
                + "\tinvoker=" + invoker.classFile.constantPool
                        .getItem(invoker.method.nameIndex, CP_UTF8.class).getStringContent() + ",\n"
                + "\tpc=" + pc + ",\n"
                + "\tbc=" + pcToBc[pc] + ",\n"
                + "\tvalues=" + Arrays.toString(values) + ",\n"
                + "\toperandStack=" + Arrays.toString(operandStack.toArray()) + "\n"
                + "}";
    }
}
