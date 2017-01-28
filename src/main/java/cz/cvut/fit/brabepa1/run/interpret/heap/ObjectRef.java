package cz.cvut.fit.brabepa1.run.interpret.heap;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Field;

/**
 *
 * @author pajcak
 */
public class ObjectRef {
    public static final int SIZE_IN_BYTES = 4 + 4 + 4;
    private int refs;
    private ClassFile classFile;
    private long byteOffset;
    
    public ObjectRef(ClassFile cf, long byteOffset) {
        this.refs = 0;
        this.classFile = cf;
        this.byteOffset = byteOffset;
    }
    
    public void setFieldValue(Field field, Object value) {
        int startPos = getFieldMemoryOffset(field);
        byte [] data = field.getByteFromData(value);
        Heap.getInstance().storeBytes(data, startPos);
    }
    
    public Object getFieldValue(Field field) {
        int startPos = getFieldMemoryOffset(field);
        byte [] fieldData = Heap.getInstance().getBytes(startPos, field.getSizeInBytes());
        return field.getDataFromBytes(fieldData);
    }
    
    private int getFieldMemoryOffset(Field field) {
        // if the field is in superclass, add additional offset,
        // since every field's offset is relative only to its class (not subclasses)
        // TODO does this really occur? Isn't that question for instruction set?
        int inheritanceOffset = 0;
        ClassFile cf = this.classFile;
        while (!field.classFile.equals(cf)) {
            for (Field f : cf.fields) {
                inheritanceOffset += f.getSizeInBytes();
            }
            cf = cf.loadSuperClass();
        }
        
        return (int)this.byteOffset /*+ SIZE_IN_BYTES*/
                + inheritanceOffset + (int)field.getByteOffset(); 
    }
    
    /**
     * Increases reference counter by one on this object
     */
    public void addReference() {
        this.refs++;
    }
    
    /**
     * Decreases reference counter by one on this object
     */
    public void release() {
        this.refs--;
    }

    @Override
    public String toString() {
        return "ObjectRef{classFile=" + classFile.getClassName() + ", refs=" + refs + ", byteOffset=" + byteOffset + '}';
    }
    
}
