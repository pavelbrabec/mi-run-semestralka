package cz.cvut.fit.brabepa1.run.interpret.instructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
        printHexStrings(bytecode);
        List<Instruction> instrs = new ArrayList<>();
        int pointer = 0;
        while (pointer < bc.length) {
            Instruction instr = getInstruction(bc[pointer]);
            if (instr.bytes() > 1) {
                instr=instr.newInstance();
                instr.setParameters(pointer, bytecode);
            }
            instrs.add(instr);
            pointer += instr.bytes();
        }
        return instrs;
    }

    //only for debugging
    private void printHexStrings(byte[] bc) {
        for (int i : bc) {
            System.out.print(Integer.toHexString(i) + " ");
        }
        System.out.println("");
    }

}
