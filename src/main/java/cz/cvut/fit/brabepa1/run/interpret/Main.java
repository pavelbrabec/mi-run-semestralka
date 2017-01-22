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
                System.out.println("WARNING\tCannot load class " + clazz.getCanonicalName());
            }
        });
        System.out.println("Loaded " + instructionImpls.size() + " instructions.");

//        ClassFile cf = ClassFileReader.lookupAndResolve("TestDyn");
//        ClassFile cf = ClassFileReader.lookupAndResolve("TestMath");
//        ClassFile cf = ClassFileReader.lookupAndResolve("TestLoops");
        ClassFile cf = ClassFileReader.lookupAndResolve("Test");
        System.out.println(cf);
        System.out.println("_________________________________");

        VirtualMachine vm = new VirtualMachine(cf);
        vm.run();
    }
}
