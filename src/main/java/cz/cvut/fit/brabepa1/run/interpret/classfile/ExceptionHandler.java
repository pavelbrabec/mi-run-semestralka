package cz.cvut.fit.brabepa1.run.interpret.classfile;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class ExceptionHandler {

    short startPc;
    short endPc;
    short handlerPc;
    
    //get it by calling ConstantPool.getItem(catchType, ConstantPoolClass.class);
    short catchType; 

    public ExceptionHandler(DataInputStream dis) {
        try {
            startPc = dis.readShort();
            endPc = dis.readShort();
            handlerPc = dis.readShort();
            catchType = dis.readShort();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + ExceptionHandler.class.getName() +
                    ": exception: " + ex);
        }
    }
    
}
