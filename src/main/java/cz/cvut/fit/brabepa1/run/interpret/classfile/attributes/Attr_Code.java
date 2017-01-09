package cz.cvut.fit.brabepa1.run.interpret.classfile.attributes;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pajcak
 */
public class Attr_Code extends Attribute {
    short maxStack;
    short maxLocals;
    int codeLength;
    byte code[];
    short exceptionTableLength;
//    excptionTable
    short attributesCount;
    Attribute[] attributes;
    
    public Attr_Code(short nameIndex, DataInputStream dis) {
        super(nameIndex, dis);
        // TODO Replace with the underwear :)
        for (int i = 0; i < attrLength; i++) {
            try {
                dis.readByte();
            } catch (IOException ex) {
                Logger.getLogger(Attr_Code.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        maxStack =   dis.readShort();
//        maxLocals =  dis.readShort();
//        codeLength = dis.readInt();
//        code = new byte [codeLength];
//        for (int i = 0; i < codeLength; i++) code[i] = dis.readByte();
//        exceptionTableLength = dis.readShort();
////        exceptionTable = new ExceptionHandler[exceptionTableLength];
    }

    
}
