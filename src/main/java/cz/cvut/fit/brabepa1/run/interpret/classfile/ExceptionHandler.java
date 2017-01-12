package cz.cvut.fit.brabepa1.run.interpret.classfile;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class ExceptionHandler {

    public short startPc;
    public short endPc;
    public short handlerPc;
    
    //get it by calling ConstantPool.getItem(catchType, ConstantPoolClass.class);
    public short catchType; 

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

    @Override
    public String toString() {
        return "ExceptionHandler{" + "startPc=" + startPc + ", endPc=" + endPc + ", handlerPc=" + handlerPc + ", catchType=" + catchType + '}';
    }
}
