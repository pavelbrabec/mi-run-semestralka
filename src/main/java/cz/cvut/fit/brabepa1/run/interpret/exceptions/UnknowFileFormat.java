package cz.cvut.fit.brabepa1.run.interpret.exceptions;

/**
 *
 * @author pavel
 */
public class UnknowFileFormat extends InterpretRuntimeException {

    public UnknowFileFormat() {
        super();
    }

    public UnknowFileFormat(int magicNumber) {
        super(""+magicNumber);
    }
    
}
