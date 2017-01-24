package cz.cvut.fit.brabepa1.run.interpret.exceptions;

/**
 *
 * @author pajcak
 */
public class FieldNotFound extends InterpretRuntimeException {

    private final String methodName;

    public FieldNotFound(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "FieldNotFound{" + "FieldName=" + methodName + '}';
    }

}
