package cz.cvut.fit.brabepa1.run.interpret;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFileReader;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Method;
import cz.cvut.fit.brabepa1.run.interpret.instructions.Instruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstructionFactory;
import java.util.List;
import java.util.Set;
import org.reflections.Reflections;

/**
 *
 * @author pavel
 */
public class Main {

    public static void main(String[] args) {
        //Register all implementations of Instruction interface into JavaInstructionFactory
        Reflections reflections = new Reflections("cz.cvut.fit.brabepa1.run.interpret.instructions.impl");
        Set<Class<? extends JavaInstruction>> instructionImpls = reflections.getSubTypesOf(JavaInstruction.class);
        instructionImpls.stream().forEach(clazz -> {
            try {
                Class.forName(clazz.getCanonicalName());
            } catch (ClassNotFoundException ex) {
                System.out.println("WARNING\tCannot load class "+clazz.getCanonicalName());
            }
        });
        System.out.println("Loaded "+instructionImpls.size()+ " instructions.");

        ClassFile cf = ClassFileReader.readFromFile("test_files/TestBool.class");
        System.out.println(cf);

        System.out.println("_________________________________");
        int nameIndex = cf.methods[1].nameIndex;
        //System.out.println(nameIndex);
        //System.out.println(cf.constantPool.items[--nameIndex]);

        Method main = cf.methods[1];

        List<Instruction> instructions = JavaInstructionFactory.getInstance().createInstructions(main.codeAttribute.code);
        int nthByte = 0;
        int tmpPc = 0;
        for (Instruction i : instructions) {
            System.out.println(tmpPc + " | " + nthByte + ": " + i);
            nthByte += i.bytes();
            tmpPc++;
        }

        Instruction[] instructionsTmp = new Instruction[instructions.size()];
        instructionsTmp = instructions.toArray(instructionsTmp);

        if(instructionsTmp == null){
            System.out.println("Array is null");
        }else{
            System.out.println("instrs: "+instructionsTmp.length);
        }
        
        VirtualMachine vm = new VirtualMachine(instructionsTmp);
        CallTarget target = Truffle.getRuntime().createCallTarget(vm);
        target.call();
    }

}
