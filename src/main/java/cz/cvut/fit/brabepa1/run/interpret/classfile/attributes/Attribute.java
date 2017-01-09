package cz.cvut.fit.brabepa1.run.interpret.classfile.attributes;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class Attribute {

    protected short attrNameIndex;
    protected int attrLength;

    public Attribute(short attrNameIndex, DataInputStream dis) {
        this.attrNameIndex = attrNameIndex;
        try {
            this.attrLength = dis.readInt();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + Attribute.class.getName() + ": exception: " + ex);
        }
    }

    public static AttrType getType(String name) {
        AttrType type;
        try {
            type = AttrType.valueOf(name);
        } catch (IllegalArgumentException | NullPointerException ex) {
            type = AttrType.NotImplemented;
        }
        return type;
    }

    public enum AttrType {
        Code,
        ConstantValue,
        StackMapTable,
        Exceptions,
        NotImplemented
    }
}
