package cz.cvut.fit.brabepa1.run.interpret.heap;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.exceptions.OutOfMemory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author pajcak
 */
public class Heap {

    private Set<ObjectRef> objectRefs;

    private static final int MAX_HEAP_SIZE = 65536;
    // int because Java supports maximum of 2^31-1 array constructs for byte[]
    private int heapSize;
    public byte[] memory;
    // points to the free byte after the allocated area ( == size of already allocated area)
    private int heapPtr;

    private static Heap instance = null;

    public static Heap getInstance() {
        if (instance == null) {
            instance = new Heap();
        }
        return instance;
    }

    private Heap() {
        objectRefs = new HashSet<ObjectRef>();
        heapSize = 8192;
        memory = new byte[heapSize];
        heapPtr = 0;
    }

    //TODO - GC - implement old & young generation (division of the heap or just flags of heap objs)
    public ObjectRef allocObject(ClassFile cf) {
        System.out.println("CLASS_BYTE_ALLOC("+cf.getSizeInBytes()+"): " + Arrays.toString(cf.getByteData()));
        long byteOffset = allocBytes(cf.getSizeInBytes()/* + ObjectRef.SIZE_IN_BYTES*/);
        ObjectRef objRef = new ObjectRef(cf, byteOffset);

        storeBytes(cf.getByteData(), byteOffset);
        
        objectRefs.add(objRef);
        objRef.addReference();
        return objRef;
    }

    public void storeBytes(byte [] data, long offset) {
        int ptr = (int)offset;
        for (byte b : data) {
            memory[ptr++] = b;
        }
    }
    
    /**
     *
     * @param bytes number of bytes to allocate in the heap
     * @return The byte offset, where the allocated memory block starts
     */
    private long allocBytes(long bytes) {
        long storeAddr = heapPtr;
        boolean heapSizeChanged = false;
        while (heapPtr + bytes > heapSize) { // not >= bcs heapPtr points already to the free byte
            heapSize *= 2;
            if (heapSize > MAX_HEAP_SIZE) {
                throw new OutOfMemory("Heap max size (" + MAX_HEAP_SIZE + ") overflowed!");
            }
            heapSizeChanged = true;
        }
        if (heapSizeChanged) {
            memory = Arrays.copyOf(memory, heapSize);
        }
        heapPtr += bytes;
        return storeAddr;
    }

    @Override
    public String toString() {
        return "Heap{" + "#objRefs=" + objectRefs.size() + ", heapSize=" + heapSize +
                ", heapPtr=" + heapPtr + "}\n\t mem: " + Arrays.toString(memory);
    }
    
    
}
