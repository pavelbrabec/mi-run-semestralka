package cz.cvut.fit.brabepa1.run.interpret;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFileReader;
import cz.cvut.fit.brabepa1.run.interpret.exceptions.MethodNotFound;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                System.out.println("WARNING\tCannot load class " + clazz.getCanonicalName());
            }
        });
        System.out.println("Loaded " + instructionImpls.size() + " instructions.");

//        ClassFile cf = ClassFileReader.lookupAndResolve("TestDyn");
//        ClassFile cf = ClassFileReader.lookupAndResolve("TestMath");
//        ClassFile cf = ClassFileReader.lookupAndResolve("TestLoops");
//        ClassFile cf = ClassFileReader.lookupAndResolve("Test");
        ClassFile cf = ClassFileReader.lookupAndResolve("TestFields");
        System.out.println(cf);
        System.out.println("_________________________________");

        try {
            VirtualMachine vm = new VirtualMachine(cf);
            vm.run();
        } catch (MethodNotFound ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
