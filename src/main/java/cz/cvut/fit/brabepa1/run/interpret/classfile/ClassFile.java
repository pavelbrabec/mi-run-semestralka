package cz.cvut.fit.brabepa1.run.interpret.classfile;

import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attribute;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.ConstantPool;

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
            str += "\t\t" + i + ": ";
            str += "0x" + Integer.toHexString(interfaces[i]);
            str += '\n';
        }
        str += "\tFields{fieldsCount=0x" + fieldsCount + "}\n";
        for (int i = 0; i < fieldsCount; i++) {
            str += "\t\t" + i + ": ";
            str += fields[i].toString();
            str += '\n';
        }
        str += "\tMethods{methodsCount=0x" + methodsCount + "}\n";
        for (int i = 0; i < methodsCount; i++) {
            str += "\t\t" + i + ": ";
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
