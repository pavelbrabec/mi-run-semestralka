package cz.cvut.fit.brabepa1.run.interpret;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFileReader;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import java.util.Set;
import org.reflections.Reflections;

/**
 *
 * @author pavel
 */
public class Main {

    public static void main(String[] args) throws Throwable {
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
//        ClassFile cf = ClassFileReader.lookupAndResolve("TestOutput");
        ClassFile cf = ClassFileReader.lookupAndResolve("TestInput");
        System.out.println(cf);
        System.out.println("_________________________________");

        boolean useTruffle = false;
        VirtualMachine vm = new VirtualMachine(cf);
        if (useTruffle) {
            CallTarget target = Truffle.getRuntime().createCallTarget(vm);
            target.call();
        } else {
            vm.execute(null);
        }
        System.out.println("Truffle: "+ useTruffle);
    }

}
