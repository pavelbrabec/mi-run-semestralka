package cz.cvut.fit.brabepa1.run.interpret.exceptions;

/**
 *
 * @author pajcak
 */
public class OutOfMemory extends InterpretRuntimeException {

    private final String decription;

    public OutOfMemory(String desc) {
        this.decription = desc;
    }

    @Override
    public String toString() {
        return "OutOfMemory{" + "description=" + decription + '}';
    }
}
