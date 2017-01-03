package cz.cvut.fit.brabepa1.run.interpret;

/**
 *
 * @author pavel
 */
public class Main {


    public static void main(String[] args) {
        
        ClassFile cf = ClassFileReader.readFromFile("Test.class");
        System.out.println(cf);
    }
    
}
