package cz.cvut.fit.brabepa1.run.interpret.exceptions;

/**
 *
 * @author pajcak
 */
public class IndexOutOfBounds extends InterpretRuntimeException {

    private final Number index;

    public IndexOutOfBounds(Number index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "IndexOutOfBounds{" + "index=" + index + '}';
    }

}
