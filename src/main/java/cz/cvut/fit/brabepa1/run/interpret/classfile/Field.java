package cz.cvut.fit.brabepa1.run.interpret.classfile;

import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attribute;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Class;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.ConstantPool;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavel
 */
class Field {

    short accessFlags;
    short nameIndex;
    short descriptorIndex;
    short attributesCount;
    Attribute[] attributes;

    public Field(DataInputStream dis, ConstantPool constantPool) {
        try {
            accessFlags = dis.readShort();
            nameIndex = dis.readShort();
            descriptorIndex = dis.readShort();
            attributesCount = dis.readShort();
            attributes = new Attribute[attributesCount];
            for (int i = 0; i < attributesCount; i++) {
                attributes[i] = ClassFileReader.readAttribute(dis, constantPool);
            }
        } catch (IOException ex) {
            System.out.println("ERROR\t" + Field.class.getName() + ": exception: " + ex);
        }
    }

    @Override
    public String toString() {
        return "Field{" + "accessFlags=" + accessFlags
                + ", nameIndex=" + nameIndex
                + ", descriptorIndex=" + descriptorIndex
                + ", attributesCount=" + attributesCount + '}';
    }

}
