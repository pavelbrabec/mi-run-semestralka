package cz.cvut.fit.brabepa1.run.interpret.classfile;

import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attr_Code;
import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attr_LineNumberTable;
import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attr_NotImplemented;
import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attr_SourceFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.ConstantPool;
import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attribute;
import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attribute.AttrType;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_UTF8;
import cz.cvut.fit.brabepa1.run.interpret.exceptions.ClassNotFound;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pavel & pajcak
 */
public class ClassFileReader {

    private static List<String> classpaths = new ArrayList<String>() {
        {
            add("java_classpath/");
            add("test_files/");
            add("test_files/class_fields/");
        }
    };
    private static Map<String, ClassFile> classFiles = new HashMap<String, ClassFile>();

    public static ClassFile lookupAndResolve(String cfName) {
        ClassFile cf = classFiles.get(cfName);
        if (cf != null) {
            return cf;
        }

        for (String classpath : classpaths) {
            File f = new File(classpath + cfName + ".class");
            if (f.exists() && !f.isDirectory() && f.canRead()) {
                cf = readFromFile(f.getPath());
                classFiles.put(cfName, cf);
                System.out.println("INFO\tLoaded class:" + classpath + cfName + ".class");
                return cf;
            }
        }
        throw new ClassNotFound(cfName + ".class");
    }

    private static ClassFile readFromFile(String path) {
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
            
            cf.sizeInBytes = 0;
            cf.fieldsCount = fis.readShort();
            cf.fields = new Field[cf.fieldsCount];
            for (int i = 0; i < cf.fieldsCount; i++) {
                cf.fields[i] = new Field(fis, cf);
                cf.fields[i].setByteOffset(cf.sizeInBytes);
                cf.sizeInBytes += cf.fields[i].getSizeInBytes();
            }
            cf.methodsCount = fis.readShort();
            cf.methods = new Method[cf.methodsCount];
            for (int i = 0; i < cf.methodsCount; i++) {
                cf.methods[i] = new Method(fis, cf);
            }
            cf.attributesCount = fis.readShort();
            cf.attributes = new Attribute[cf.attributesCount];
            for (int i = 0; i < cf.attributesCount; i++) {
                cf.attributes[i] = ClassFileReader.readAttribute(fis, cf);
            }

            if (cf.superClass != 0) {
                cf.sizeInBytes += cf.loadSuperClass().getSizeInBytes();
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
            case LineNumberTable:
                return new Attr_LineNumberTable(attrNameIndex, dis, classFile, null);
            case SourceFile:
                return new Attr_SourceFile(attrNameIndex, dis, classFile, null);
            default:
                return new Attr_NotImplemented(attrNameIndex, dis, classFile, null);
        }
    }

}
