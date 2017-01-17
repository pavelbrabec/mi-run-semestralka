package cz.cvut.fit.brabepa1.run.interpret;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.RootNode;
import cz.cvut.fit.brabepa1.run.interpret.instructions.Instruction;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author pavel
 */
public class VirtualMachine extends RootNode {

    private final LinkedList<Object> stack = new LinkedList<>();
    @Children
    private final Instruction[] instructions;
    private int pc = 0;
    private Object[] values = new Object[4];

    /*
     Pole pcToBc a bcToPc resi problem, branch offset u goto nebo if prikazu je 
     v bytech a ne v instrukcich. V konstruktoru si predpocitam hodnoty prevodu aby
     v runtimu jsem nemusel nic pocitat.
     */
    private int[] pcToBc;
    private int[] bcToPc;

    public VirtualMachine(Instruction[] instructions) {
        super(TruffleLanguage.class, null, null);
        this.instructions = instructions;
        pcToBc = new int[instructions.length];
        pcToBc[0] = 0;
        for (int i = 1; i < instructions.length; i++) {
            pcToBc[i] = pcToBc[i - 1] + instructions[i - 1].bytes();
        }

        //int bytCodeLenght = instructions.stream().mapToInt(Instruction::bytes).sum();
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

        System.out.println("pc2bc " + Arrays.toString(pcToBc));
        System.out.println("bc2pc " + Arrays.toString(bcToPc));
    }
    
    @Override
    @ExplodeLoop
    public Object execute(VirtualFrame vf) {
        while (pc < instructions.length) {
            Instruction ins = instructions[pc];
            System.out.println(ins.toString());
            ins.execute(this);
            System.out.println(this);
        }
        return null;
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
