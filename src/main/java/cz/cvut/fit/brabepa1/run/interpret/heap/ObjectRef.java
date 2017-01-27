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
    
    public Object getFieldValue(Field field) {
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
        
        int startPos = (int)this.byteOffset /*+ SIZE_IN_BYTES*/
                + inheritanceOffset + (int)field.getByteOffset();

        byte [] fieldData = new byte[(int)field.getSizeInBytes()];
        for (int i = startPos, j = 0; i < fieldData.length; i++, j++) {
            fieldData[j] = Heap.getInstance().memory[i];
        }
        return field.getDataFromBytes(fieldData);
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
