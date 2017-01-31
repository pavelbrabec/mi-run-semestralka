package cz.cvut.fit.brabepa1.run.interpret;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFileReader;
import cz.cvut.fit.brabepa1.run.interpret.heap.Heap;
import cz.cvut.fit.brabepa1.run.interpret.instructions.JavaInstruction;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import org.reflections.Reflections;

/**
 *
 * @author pavel
 */
public class Main {

    private static String TRUFFLE = "-truffle";
    private static String DEBUG = "-debug";

    private static boolean contains(String[] args, String arg) {
        for (String a : args) {
            if (a.equalsIgnoreCase(arg)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Throwable {
        if (args.length == 0) {
            System.out.println("There is a mandatory paramameter class name!");
            return;
        }

        File classFile = new File(args[0] + ".class");
        if (!classFile.exists()) {
            System.out.println("File does not exists " + classFile.getAbsolutePath());
            return;
        }

        boolean useTruffle = false;
        if (contains(args, TRUFFLE)) {
            useTruffle = true;
        }

        if (contains(args, DEBUG)) {
            VirtualMachine.VM_DEBUG = true;
        }

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
        if (VirtualMachine.VM_DEBUG) {
            System.out.println("Loaded " + instructionImpls.size() + " instructions.");
        }

        ClassFile cf = ClassFileReader.lookupAndResolve(args[0]);
        if (VirtualMachine.VM_DEBUG) {
            System.out.println(cf);
        }

        VirtualMachine vm = new VirtualMachine(cf);
        if (useTruffle) {
            CallTarget target = Truffle.getRuntime().createCallTarget(vm);
            target.call();
        } else {
            vm.execute(null);
        }
        if (VirtualMachine.VM_DEBUG) {
            System.out.println("Truffle: " + useTruffle);
            System.out.println("\n" + Heap.getInstance());
        }
    }

}
