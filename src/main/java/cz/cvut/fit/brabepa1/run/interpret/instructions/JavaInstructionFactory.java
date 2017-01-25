package cz.cvut.fit.brabepa1.run.interpret.instructions;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pavel
 */
public class JavaInstructionFactory implements InstructionFactory {

    private static JavaInstructionFactory instance;

    private final Map<Integer, Instruction> instructions = new HashMap<>();

    private JavaInstructionFactory() {
    }

    public static synchronized JavaInstructionFactory getInstance() {
        if (instance == null) {
            instance = new JavaInstructionFactory();
        }
        return instance;
    }

    public void registerInstruction(int opCode, Instruction instr) {
        instructions.put(opCode, instr);
    }

    private Instruction getInstruction(int opCode) {
        Instruction instr = instructions.get(opCode);
        if (instr == null) {
            throw new IllegalArgumentException("Unknown instruction with opCode " + Integer.toHexString(opCode));
        }
        return instr;
    }

    @Override
    public List<Instruction> createInstructions(byte[] bytecode) {
        printHexStrings(bytecode);
        int[] bc = new int[bytecode.length];
        for (int i = 0; i < bytecode.length; i++) {
            bc[i] = bytecode[i] & 0xFF;
        }
        List<Instruction> instrs = new ArrayList<>();
        int pointer = 0;
        while (pointer < bc.length) {
            Instruction instr = getInstruction(bc[pointer]);
            if (instr.bytes() > 1) {
                Class clazz = instr.getClass();
                try {
                    instr = (Instruction) clazz.getConstructor().newInstance();
                } catch (ReflectiveOperationException ex) {
                    System.out.println("ERROR\tAll instructions must have public empty constructor");
                }
                instr.setParameters(pointer, bytecode);
            }
            instrs.add(instr);
            pointer += instr.bytes();
        }
        return instrs;
    }

    //only for debugging
    private void printHexStrings(byte[] bc) {
        if (VirtualMachine.VM_DEBUG) {
            System.out.print("Method code: ");
            for (int i : bc) {
                System.out.print(Integer.toHexString(i) + " ");
            }
            System.out.println("");
        }
    }

}
