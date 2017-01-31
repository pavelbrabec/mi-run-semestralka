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
    private final long byteOffset;

    public ObjectRef(ClassFile cf, long byteOffset) {
        this.refs = 0;
        this.classFile = cf;
        this.byteOffset = byteOffset;
    }

    public ObjectRef(long byteOffset) {
        this.byteOffset = byteOffset;
        this.classFile = null;
        boolean found = false;
        for (ObjectRef o : Heap.getInstance().objectRefs) {
            if (o.byteOffset == this.byteOffset) {
                this.classFile = o.classFile;
                this.refs = o.refs;
                found = true;
                break;
            }
        }
        if (this.classFile == null || !found) {
            throw new UnsupportedOperationException(
                    "ObjectRef(offset) - Referential ObjectRef not found in heap!");
        }
    }

    public void setFieldValue(Field field, Object value) {
        int startPos = getFieldMemoryOffset(field);
//        System.out.println("ObjectRef cf="+classFile.getClassName()+" at "+byteOffset);
//        System.out.println("Setting field("+field.getName()+") to val:" +value+" at pos "+startPos);
        byte[] data = field.getByteFromData(value);
        Heap.getInstance().storeBytes(data, startPos);
    }

    public Object getFieldValue(Field field) {
        int startPos = getFieldMemoryOffset(field);
        byte[] fieldData = Heap.getInstance().getBytes(startPos, field.getSizeInBytes());
        return field.getDataFromBytes(fieldData);
    }

    private int getFieldMemoryOffset(Field field) {
        if (classFile == null) {
            // souvisi to s tim, ze nejdriv to fieldu typu ObjectRef
            // priradim null (Objectreff s cf=null) a pozdeji tam hodim
            throw new UnsupportedOperationException("Doufal jsem, ze se tohle nestane :-D");
        }
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

        return (int) this.byteOffset /*+ SIZE_IN_BYTES*/
                + inheritanceOffset + (int) field.getByteOffset();
    }

    public long getByteOffset() {
        return byteOffset;
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
        return "ObjectRef{classFile=" + (classFile == null ? "null" : classFile.getClassName())
                + ", refs=" + refs + ", byteOffset=" + byteOffset + '}';
    }

}
