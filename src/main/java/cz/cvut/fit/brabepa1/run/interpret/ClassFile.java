package cz.cvut.fit.brabepa1.run.interpret;

import cz.cvut.fit.brabepa1.run.interpret.ConstantPool.ConstantPool;

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
    public short fields_count;
    public FieldInfo[] fieldInfo;//size=fields_count
    public short methods_count;
    public MethodInfo[] methodInfo;//methods_count
    public short attributes_count;
    public AttributeInfo[] AttributeInfo;//attributes_count
    //? public Instruction[] instructions;

    @Override
    public String toString() {
        return "ClassFile{" + 
                "magicNumber=0x"+Integer.toHexString(magicNumber) +
                ", minorVersion=0x" + Integer.toHexString(minorVersion) + 
                ", majorVersion=0x" + Integer.toHexString(majorVersion) +
                ", constantPoolSize=0x" + Integer.toHexString(constantPoolCount) +
                "}\n\t" +
                constantPool.toString();
    }
}
