package cz.cvut.fit.brabepa1.run.interpret;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFileReader;

/**
 *
 * @author pavel
 */
public class Main {

    public static void main(String[] args) {

        ClassFile cf = ClassFileReader.readFromFile("test_files/Test.class");
        System.out.println(cf);
    }

}
