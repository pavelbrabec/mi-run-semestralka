package cz.cvut.fit.brabepa1.run.interpret.exceptions;

/**
 * 
 * @author pajcak
 */
public class MethodNotFound extends InterpretRuntimeException{
      private final String methodName;

    public MethodNotFound(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "MethodNotFound{" + "methodName=" + methodName + '}';
    }

}
