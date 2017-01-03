package cz.cvut.fit.brabepa1.run.interpret;

/**
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html
 * 
 * @author pavel
 */
public class ClassFile {
    
    public int magicNumber;
    public int minorVersion;
    public int majorVersion;
    public int constantPoolCount;
    public ConstantPoolInfo[] constantPoolInfo; //size=constantPoolCount-1
    public int accessFlags;
    public int thisClass;
    public int superClass;
    public int interfacesCount;
    public int[] interfaces;//size=interfacesCount; Each value in the interfaces array must be a valid index into the constant_pool table.
    public int fields_count;
    public FieldInfo[] fieldInfo;//size=fields_count
    public int methods_count;
    public MethodInfo[] methodInfo;//methods_count
    public int attributes_count;
    public AttributeInfo[] AttributeInfo;//attributes_count
    //? public Instruction[] instructions;

    @Override
    public String toString() {
        return "ClassFile{" + 
                "magicNumber=0x"+Integer.toHexString(magicNumber) +
                ", minorVersion=0x" + Integer.toHexString(minorVersion) + 
                ", majorVersion=0x" + Integer.toHexString(majorVersion) +
                ", constantPoolSize=0x" + Integer.toHexString(constantPoolCount) +
                '}';
    }
}
