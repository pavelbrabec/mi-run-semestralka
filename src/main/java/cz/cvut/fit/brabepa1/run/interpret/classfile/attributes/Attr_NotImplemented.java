package cz.cvut.fit.brabepa1.run.interpret.classfile.attributes;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class Attr_NotImplemented extends Attribute {

    byte[] body;

    public Attr_NotImplemented(short nameIndex, DataInputStream dis) {
        super(nameIndex, dis);
        body = new byte[attrLength];
        try {
            for (int i = 0; i < attrLength; i++) body[i] = dis.readByte();
            
        } catch (IOException ex) {
            System.out.println("ERROR\t" + Attr_NotImplemented.class.getName() +
                    ": exception: " + ex);
        }
        System.out.println("ERROR\t" + Attr_NotImplemented.class.getName() +
                "Just read attribute which is not implemented!");
    }

}
