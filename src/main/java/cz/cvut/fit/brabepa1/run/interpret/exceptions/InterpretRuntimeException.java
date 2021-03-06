package cz.cvut.fit.brabepa1.run.interpret.exceptions;

/**
 * Obecna runtime vyjimka pro interpret.
 * 
 * @author pajcak
 */
public class InterpretRuntimeException extends RuntimeException {

    public InterpretRuntimeException() {
    }

    public InterpretRuntimeException(String message) {
        super(message);
    }

    public InterpretRuntimeException(Throwable cause) {
        super(cause);
    }

    public InterpretRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    

}
