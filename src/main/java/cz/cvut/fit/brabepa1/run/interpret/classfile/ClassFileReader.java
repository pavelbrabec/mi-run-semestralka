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

    
// TODO think about some global access to all loaded classfiles
//    private static ArrayList<ClassFile> classFiles = null;
//
//    public static ClassFile getClassFile(int index) {
//        if (classFiles == null || classFiles.isEmpty()) {
//            System.out.println("ERROR\t" + ClassFileReader.class.getName() + 
//                    " No classfile read yet! -> EXITING...");
//            System.exit(1);
//            return null; // only or netbeans checker
//        } else {
//            return classFiles.get(index);
//        }
//    }
    
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
                cf.fields[i] = new Field(fis, cf);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR\tFile not found: " + path);
        } catch (IOException ex) {
            System.out.println("ERROR\tUnexpected IOException occured: " + ex.getMessage());
        }
        
        return cf; 
    }

    public static Attribute readAttribute(DataInputStream dis, ClassFile classFile) {
        short attrNameIndex;
        try {
            attrNameIndex = dis.readShort();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + ClassFileReader.class.getName()
                    + "- readAttribute(): exception: " + ex);
            return null;
        }
        String type = classFile.constantPool.getItem(attrNameIndex, CP_UTF8.class)
                        .getStringContent();
        AttrType valueOf = Attribute.getType(type);

        switch (valueOf) {
            case Code:
                return new Attr_Code(attrNameIndex, dis, classFile, null);
//            TODO add more cases epending on the content of Attribute.Type
//            TODO add more cases epending on the content of Attribute.Type
//            TODO add more cases epending on the content of Attribute.Type
            default:
                return new Attr_NotImplemented(attrNameIndex, dis, classFile, null);
        }
    }
}
