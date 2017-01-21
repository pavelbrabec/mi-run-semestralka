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
        ObjectRef objRef = new ObjectRef(cf);
        // ...
        objRef.addReference();
        return null;
    }
}
