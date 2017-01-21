package cz.cvut.fit.brabepa1.run.interpret.heap;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;

/**
 *
 * @author pajcak
 */
public class ObjectRef {
    private int refs;
    private ClassFile cf;
    
    public ObjectRef(ClassFile cf) {
        this.refs = 0;
        this.cf = cf;
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
