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

    public Set<ObjectRef> objectRefs;
    public Set<ArrayRef> arrayRefs;

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
        arrayRefs = new HashSet<ArrayRef>();
//        heapSize = 8192;
        heapSize = 20;
        memory = new byte[heapSize];
        heapPtr = 0;
    }

    //TODO - GC - implement old & young generation (division of the heap or just flags of heap objs)
    public ObjectRef allocObject(ClassFile cf) {
        System.out.println("HEAP--ALLOC OBJ (size="+cf.getSizeInBytes()+"): " + Arrays.toString(cf.getByteData()));
        long byteOffset = allocBytes(cf.getSizeInBytes()/* + ObjectRef.SIZE_IN_BYTES*/);
        ObjectRef objRef = new ObjectRef(cf, byteOffset);

        storeBytes(cf.getByteData(), byteOffset);
        
        objectRefs.add(objRef);
        objRef.addReference();
        return objRef;
    }
    
    public ArrayRef allocArray(int type, long count) {
        System.out.println("HEAP--ALLOC ARR (type=" + type + ", count=" + count + ")");
        long byteOffset = allocBytes(ArrayRef.getSize(type, count));
        ArrayRef arrRef = new ArrayRef(type, count, byteOffset);

        storeBytes(arrRef.getInitData(), byteOffset);
        
        arrayRefs.add(arrRef);
        arrRef.addReference();
        return arrRef;
    }

    public void storeBytes(byte [] data, long offset) {
        System.out.println("HEAP--STORE_BYTES(size="+data.length+", off="+offset+"):"
                +Arrays.toString(data));
        int ptr = (int)offset;
        for (byte b : data) {
            memory[ptr++] = b;
        }
    }
    
    public byte[] getBytes(long offset, long length) {
        byte[] res = new byte[(int)length];
        for (int i = (int)offset, j = 0; i < (int)offset + length; i++, j++) {
            res[j] = memory[i];
        }
        return res;
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
        char [] occupiedBytes = new char[heapSize];
        for (int i = 0; i < heapSize; i++) {
            if (i < heapPtr) occupiedBytes[i] = 'x';
            else occupiedBytes[i] = ' ';
        }
        return "Heap{" + "#objRefs=" + objectRefs.size() + ", heapSize=" + heapSize +
                ", heapPtr=" + heapPtr + "}\n"
                + "  memory: " + Arrays.toString(memory) + "\n"
                + "occupied: " + Arrays.toString(occupiedBytes);
    }
    
    
}
