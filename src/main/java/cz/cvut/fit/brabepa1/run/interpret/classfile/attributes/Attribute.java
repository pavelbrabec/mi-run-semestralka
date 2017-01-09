package cz.cvut.fit.brabepa1.run.interpret.classfile.attributes;

import cz.cvut.fit.brabepa1.run.interpret.classfile.*;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Field;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_UTF8;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class Attribute {

    protected short attrNameIndex;
    protected int attrLength;
    protected ClassFile classFile;
    protected Field field;
    
    //field - bcs of determining type of field, where e.g. Attr_ConstValue belongs to (more in attr_constValue)
    public Attribute(short attrNameIndex, DataInputStream dis, ClassFile classFile, Field field) {
        this.classFile = classFile;
        this.field = field;
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

    @Override
    public String toString() {
        String attrName = this.classFile.constantPool.getItem(attrNameIndex, CP_UTF8.class).getStringContent();
        return "Attr_" + attrName + "{" + "attrNameIndex=" + attrNameIndex +
                ", attrLength=" + attrLength;
    }
    
}
