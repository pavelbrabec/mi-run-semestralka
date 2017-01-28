package cz.cvut.fit.brabepa1.run.interpret.classfile;

import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attribute;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Class;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_FieldRef;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.ConstantPool;
import cz.cvut.fit.brabepa1.run.interpret.exceptions.FieldNotFound;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html
 *
 * @author pavel
 */
public class ClassFile {

    public int magicNumber;
    public short minorVersion;
    public short majorVersion;
    public short constantPoolCount;
    public ConstantPool constantPool; //size=constantPoolCount-1
    public short accessFlags;
    public short thisClass;
    public short superClass;
    public short interfacesCount;
    public short[] interfaces;//size=interfacesCount; Each value in the interfaces array must be a valid index into the constant_pool table.
    public short fieldsCount;
    public Field[] fields;//size=fieldsCount
    public short methodsCount;
    public Method[] methods;//methods_count
    public short attributesCount;
    public Attribute[] attributes;//attributes_count
    //? public Instruction[] instructions;
    // Contains number of bytes of all fields of this class and its superclasses
    public long sizeInBytes;

    public ClassFile loadSuperClass() {
        if (superClass != 0) { // invalid CP index (this class has no superclass)
            return ClassFileReader.lookupAndResolve(
                    constantPool.getItem(superClass, CP_Class.class).getClassName());
        }
        return null;
    }

    public Field getFieldWithLookup(int fieldRefIdx) {
        CP_FieldRef fieldRef = constantPool.getItem(fieldRefIdx, CP_FieldRef.class);
        ClassFile cf = this;
        String fieldClassName = constantPool.getItem(fieldRef.classIndex, CP_Class.class).getClassName();
        if (!cf.getClassName().equals(fieldClassName)) {
            cf = ClassFileReader.lookupAndResolve(fieldClassName);
        }
        Field field = cf.getField(fieldRef.getNameAndType().getName(),
                fieldRef.getNameAndType().getDescriptor());
        while (field == null) { // in case that the field is in superclasses
            cf = cf.loadSuperClass();
            if (cf == null) { // no superclass -> field doesn't exist
                throw new FieldNotFound(fieldRef.getNameAndType().getName());
            }
            field = cf.getField(fieldRef.getNameAndType().getName(),
                    fieldRef.getNameAndType().getDescriptor());
        }
        return field;
    }

    public Field getField(String name, String descriptor) {
        for (Field f : fields) {
            if (f.getName().equals(name) && f.getDescriptor().equals(descriptor)) {
                return f;
            }
        }
        return null;

    }

    public Method getMethod(String name, String descriptor) {
        for (Method m : methods) {
            if (m.getName().equals(name) && m.getDescriptor().equals(descriptor)) {
                return m;
            }
        }
        return null;
    }

    public String getClassName() {
        return constantPool.getItem(thisClass, CP_Class.class).getClassName();
    }

    /**
     * The size is calculated in ClassFileReader's readFromFile method
     *
     * @return Size in bytes of all fields of this class and its superclasses
     */
    public long getSizeInBytes() {
        return sizeInBytes;
    }

    /**
     * @return Byte data of all fields in this class and the super class
     */
    public byte[] getByteData() {
        byte[] data = new byte[(int) sizeInBytes];
        int ptr = 0;
        ClassFile cf = this;
        while (cf != null) {
            for (Field field : cf.fields) {
                for (byte b : field.getByteFromData(field.getValue())) {
                    data[ptr++] = b;
                }
            }
            cf = cf.loadSuperClass();
        }
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClassFile other = (ClassFile) obj;

        return this.getClassName().equals(other.getClassName());
    }

    @Override
    public String toString() {
        String str = "ClassFile{"
                + "magicNumber=0x" + Integer.toHexString(magicNumber)
                + ", minorVersion=0x" + Integer.toHexString(minorVersion)
                + ", majorVersion=0x" + Integer.toHexString(majorVersion)
                + ", constantPoolSize=0x" + Integer.toHexString(constantPoolCount)
                + ", accessFlags=0x" + Integer.toHexString(accessFlags)
                + ", thisClass=0x" + Integer.toHexString(thisClass)
                + ", superClass=0x" + Integer.toHexString(superClass)
                + "}\n\t"
                + constantPool.toString() + '\n'
                + "\tInterfaces{interfacesCount=0x" + Integer.toHexString(interfacesCount) + "}\n";
        for (int i = 0; i < interfacesCount; i++) {
            str += "\t\t" + (i + 1) + ": ";
            str += "0x" + Integer.toHexString(interfaces[i]);
            str += '\n';
        }
        str += "\tFields{fieldsCount=0x" + fieldsCount + "}\n";
        for (int i = 0; i < fieldsCount; i++) {
            str += "\t\t" + (i + 1) + ": ";
            str += fields[i].toString();
            str += '\n';
        }
        str += "\tMethods{methodsCount=0x" + methodsCount + "}\n";
        for (int i = 0; i < methodsCount; i++) {
            str += "\t\t" + (i + 1) + ": ";
            str += methods[i].toString();
            str += '\n';
        }
        str += "\tAttributes{attributesCount=0x" + attributesCount + "}\n";
        for (int i = 0; i < attributesCount; i++) {
            str += "\t\t" + (i + 1) + ": ";
            str += attributes[i].toString();
            str += '\n';
        }
        return str;
    }
}
