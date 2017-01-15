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
    private List<Instruction> instructions;
    private int pc = 0;
    private Object[] values = new Object[4];


    /*
     Dobudoucna bude potreba rozsirit nebo mozna setovat pres settry
     */
    public VirtualMachine(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public void run() {
        while (pc < instructions.size()) {
            Instruction ins = instructions.get(pc);
            ins.execute(this);
            System.out.println(ins.toString());
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
        addOffsetToPc(1);
    }

    public void addOffsetToPc(int offset) {
        pc += offset;
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
                + "\tvalues=" + Arrays.toString(values) + ",\n"
                + "\tstack=" + Arrays.toString(stack.toArray()) + "\n"
                + "}";
    }

}
