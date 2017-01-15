package cz.cvut.fit.brabepa1.run.interpret;

import cz.cvut.fit.brabepa1.run.interpret.instructions.Instruction;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author pavel
 */
public class VirtualMachine {

    private final LinkedList<Object> stack = new LinkedList<>();
    private final List<Instruction> instructions;
    private int pc = 0;
    private final Object[] values = new Object[4];

    /*
    Pole pcToBc a bcToPc resi problem, branch offset u goto nebo if prikazu je 
    v bytech a ne v instrukcich. V konstruktoru si predpocitam hodnoty prevodu aby
    v runtimu jsem nemusel nic pocitat.
    */
    private final int[] pcToBc;
    private final int[] bcToPc;


    /*
     Dobudoucna bude potreba rozsirit nebo mozna setovat pres settry
     */
    public VirtualMachine(List<Instruction> instructions) {
        this.instructions = instructions;

        pcToBc = new int[instructions.size()];
        pcToBc[0] = 0;
        for (int i = 1; i < instructions.size(); i++) {
            pcToBc[i] = pcToBc[i-1] + instructions.get(i-1).bytes();
        }

        int bytCodeLenght = instructions.stream().mapToInt(Instruction::bytes).sum();
        bcToPc = new int[bytCodeLenght];
        for (int i = 0; i < bcToPc.length; i++) {
            bcToPc[i] = -1;
        }
        int tmpPc = 0;
        int bcPointer = 0;
        do {
            bcToPc[bcPointer] = tmpPc;
            bcPointer += instructions.get(tmpPc).bytes();
            tmpPc++;
        } while (tmpPc < instructions.size());

        System.out.println("pc2bc " + Arrays.toString(pcToBc));
        System.out.println("bc2pc " + Arrays.toString(bcToPc));
    }

    public void run() {
        while (pc < instructions.size()) {
            Instruction ins = instructions.get(pc);
            System.out.println(ins.toString());
            ins.execute(this);
            System.out.println(this);
        }
    }

    public Object stackPeek() {
        return stack.peek();
    }

    public Object stackPop() {
        return stack.pop();
    }

    public void stackPush(Object o) {
        stack.push(o);
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
        return "VirtualMachine{\n"
                + "\tpc=" + pc + ",\n"
                + "\tbc=" + pcToBc[pc] + ",\n"
                + "\tvalues=" + Arrays.toString(values) + ",\n"
                + "\tstack=" + Arrays.toString(stack.toArray()) + "\n"
                + "}";
    }

}
