package cz.cvut.fit.brabepa1.run.interpret;

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
        //Register all implementation of Instruction interface into JavaInstructionFactory
        Reflections reflections = new Reflections("cz.cvut.fit.brabepa1.run.interpret.instructions.impl");
        Set<Class<? extends JavaInstruction>> instructions = reflections.getSubTypesOf(JavaInstruction.class);
        instructions.stream().forEach(clazz -> {
            try {
                Class.forName(clazz.getCanonicalName());
            } catch (ClassNotFoundException ex) {
                System.out.println("WARNING\tCannot load class "+clazz.getCanonicalName());
            }
        });
        System.out.println("Loaded "+instructions.size()+ " instructions.");

        ClassFile cf = ClassFileReader.readFromFile("test_files/TestLoops.class");
        System.out.println(cf);

        System.out.println("_________________________________");
        int nameIndex = cf.methods[1].nameIndex;
        //System.out.println(nameIndex);
        //System.out.println(cf.constantPool.items[--nameIndex]);

        Method main = cf.methods[1];

        List<Instruction> instrs = JavaInstructionFactory.getInstance().createInstructions(main.codeAttribute.code);
        int nthByte = 0;
        for (Instruction i : instrs) {
            System.out.println(nthByte + ": " + i);
            nthByte += i.bytes();
        }
        
        VirtualMachine vm = new VirtualMachine(instrs);
        vm.run();
    }

}
