package cz.cvut.fit.brabepa1.run.interpret.classfile;

import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attribute;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author pavel
 */
public class Field {

    short accessFlags;
    short nameIndex;
    short descriptorIndex;
    short attributesCount;
    Attribute[] attributes;

    public Field(DataInputStream dis, ClassFile classFile) {
        try {
            accessFlags = dis.readShort();
            nameIndex = dis.readShort();
            descriptorIndex = dis.readShort();
            attributesCount = dis.readShort();
            attributes = new Attribute[attributesCount];
            for (int i = 0; i < attributesCount; i++) {
                attributes[i] = ClassFileReader.readAttribute(dis, classFile);
            }
        } catch (IOException ex) {
            System.out.println("ERROR\t" + Field.class.getName() + ": exception: " + ex);
        }
    }

    @Override
    public String toString() {
        String str = "Field{" + "accessFlags=" + accessFlags
                + ", nameIndex=" + nameIndex
                + ", descriptorIndex=" + descriptorIndex
                + ", attributesCount=" + attributesCount + '}';
        if (attributesCount > 0) str += '\n';
        for (int i = 0; i < attributesCount; i++) {
            str += "\t\t\t" + (i+1) + ": ";
            str += attributes[i].toString();
            if (i < attributesCount - 1) str += '\n';
        }
        return str;
    }

}
