package cz.cvut.fit.brabepa1.run.interpret.classfile;

import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attr_Code;
import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attr_NotImplemented;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.ConstantPool;
import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attribute;
import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attribute.AttrType;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_UTF8;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author pavel
 */
public class ClassFileReader {

    public static ClassFile readFromFile(String path) {
        ClassFile cf = new ClassFile();
        try (DataInputStream fis = new DataInputStream(new FileInputStream(path))) {
            cf.magicNumber = fis.readInt();
            cf.minorVersion = fis.readShort();
            cf.majorVersion = fis.readShort();
            cf.constantPoolCount = fis.readShort(); // count - 1 is the # items in constantPool [by oracle doc]
            cf.constantPool = new ConstantPool(cf.constantPoolCount - 1, fis);
            cf.accessFlags = fis.readShort();
            cf.thisClass = fis.readShort();
            cf.superClass = fis.readShort();
            cf.interfacesCount = fis.readShort();
            cf.interfaces = new short[cf.interfacesCount];
            for (int i = 0; i < cf.interfacesCount; i++) {
                cf.interfaces[i] = fis.readShort();
            }
            cf.fieldsCount = fis.readShort();
            cf.fields = new Field [cf.fieldsCount];
            for (int i = 0; i < cf.fieldsCount; i++) {
                cf.fields[i] = new Field(fis, cf.constantPool);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR\tFile not found: " + path);
        } catch (IOException ex) {
            System.out.println("ERROR\tUnexpected IOException occured: " + ex.getMessage());
        }
        return cf;
    }

    public static Attribute readAttribute(DataInputStream dis, ConstantPool constantPool) {
        short attrNameIndex;
        try {
            attrNameIndex = dis.readShort();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + ClassFileReader.class.getName()
                    + "- readAttribute(): exception: " + ex);
            return null;
        }
        String type = constantPool.getItem(attrNameIndex, CP_UTF8.class).getStringContent();
        AttrType valueOf = Attribute.getType(type);

        switch (valueOf) {
            case Code:
                return new Attr_Code(attrNameIndex, dis);
//            TODO add more cases epending on the content of Attribute.Type
//            TODO add more cases epending on the content of Attribute.Type
//            TODO add more cases epending on the content of Attribute.Type
            default:
                return new Attr_NotImplemented(attrNameIndex, dis);
        }
    }
}
