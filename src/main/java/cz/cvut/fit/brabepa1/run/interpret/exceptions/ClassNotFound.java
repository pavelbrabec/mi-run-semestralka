package cz.cvut.fit.brabepa1.run.interpret.exceptions;

/**
 * 
 * @author pajcak
 */
public class ClassNotFound extends InterpretException{
      private final String className;

    public ClassNotFound(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "ClassNotFound{" + "className=" + className + '}';
    }

}
