package cz.cvut.fit.brabepa1.run.interpret.heap;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;

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
}
