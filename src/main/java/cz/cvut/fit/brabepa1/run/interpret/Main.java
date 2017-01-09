package cz.cvut.fit.brabepa1.run.interpret;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFileReader;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;

/**
 *
 * @author pavel
 */
public class Main {


    public static void main(String[] args) {
        
        ClassFile cf = ClassFileReader.readFromFile("test_files/Test2.class");
        System.out.println(cf);
    }
    
}
