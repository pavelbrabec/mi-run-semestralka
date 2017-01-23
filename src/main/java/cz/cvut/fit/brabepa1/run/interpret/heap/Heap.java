package cz.cvut.fit.brabepa1.run.interpret.heap;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author pajcak
 */
public class Heap {

    private static Set<ObjectRef> objectRefs = new HashSet<ObjectRef>();

    private static long heapSize = 8192;

    //TODO implement old & young generation (division of the heap or just flags of heap objs)
    public static ObjectRef allocObject(ClassFile cf) {
        long byteOffset = allocBytes(cf.getSizeInBytes() + ObjectRef.SIZE_IN_BYTES);
        ObjectRef objRef = new ObjectRef(cf, byteOffset);
        // TODO save all the classfile (fields only?), byte by byte to the memory at byteOffset
        //    - do it here or in the objRef itself
        objectRefs.add(objRef);
        objRef.addReference();
        return objRef;
    }

    /**
     *
     * @param bytes number of bytes to allocate in the heap
     * @return The byte offset, where the allocated memory block starts
     */
    private static long allocBytes(long bytes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
