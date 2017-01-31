package cz.cvut.fit.brabepa1.run.interpret.heap;

import cz.cvut.fit.brabepa1.run.interpret.exceptions.IndexOutOfBounds;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 *
 * @author pajcak
 */
public class ArrayRef {

    private int refs;
    private final long byteOffset;
    private int aType;
    private long count;

    public ArrayRef(int type, long count, long byteOffset) {
        this.refs = 0;
        this.aType = type;
        this.count = count;
        this.byteOffset = byteOffset;
    }

    public ArrayRef(long byteOffset) {
        this.byteOffset = byteOffset;
        boolean found = false;
        for (ArrayRef a : Heap.getInstance().arrayRefs) {
            if (a.byteOffset == this.byteOffset) {
                this.refs = a.refs;
                this.aType = a.aType;
                this.count = a.count;
                found = true;
                break;
            }
        }
        if (!found) {
            throw new UnsupportedOperationException(
                    "ArrayRef(offset) - Referential ArrayRef not found in heap!");
        }
    }

    public Object getInitValue() {
        switch (aType) {
            case 4: // boolean
                return false;
            case 8: // byte
                return (byte) 0;
            case 5: // char
                return '\u0000';
            case 9: // short
                return (short) 0;
            case 6: // float
                return new Float(0);
            case 10: // int
                return 0;
            case 7: // double
                return new Double(0);
            case 11: // long
                return new Long(0);
            default:
                throw new UnsupportedOperationException("ArrayRef - unsupported value type: " + aType);
        }
    }

    public long getByteOffset() {
        return byteOffset;
    }

    public int getaType() {
        return aType;
    }

    public long getCount() {
        return count;
    }

    public void setElem(long index, Object value) {
        if (index >= this.count || index < 0) {
            throw new IndexOutOfBounds(index);
        }

        long elemOffset = byteOffset + index * getTypeSize(aType);
        byte[] data = getBytesFromValue(value);
        Heap.getInstance().storeBytes(data, elemOffset);
        System.out.println("ArrayRef type=" + aType + ", count=" + count + ", at s" + byteOffset);
        System.out.println("Setting elem idx=" + index + " to val:" + value + " at pos " + elemOffset);
    }

    public Object getElem(long index) {
        if (index >= this.count || index < 0) {
            throw new IndexOutOfBounds(index);
        }

        long offset = byteOffset + index * getTypeSize(aType);
        byte[] bytes = Heap.getInstance().getBytes(offset, getTypeSize(aType));
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        switch (aType) {
            case 4: // boolean
                return buf.get(0);
            case 8: // byte
                return buf.get(0);
            case 5: // char
                return buf.getChar(0);
            case 9: // short
                return buf.getShort(0);
            case 6: // float
                return buf.getFloat(0);
            case 10: // int
                return buf.getInt(0);
            case 7: // double
                return buf.getDouble(0);
            case 11: // long
                return buf.getLong(0);
            default:
                throw new UnsupportedOperationException("ArrayRef - unsupported value type: " + aType);
        }

    }

    public byte[] getBytesFromValue(Object obj) {
        ByteBuffer data = null;
        byte[] res = new byte[getTypeSize(aType)];
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(obj);
            data = ByteBuffer.wrap(bos.toByteArray());

        } catch (IOException ex) {
            throw new UnsupportedOperationException("getBytesFromValue() -" + ex);
        }
        int typeSize = getTypeSize(aType);
        for (int i = typeSize - 1; i >= 0; i--) {
            res[typeSize - 1 - i] = data.get(data.capacity() - 1 - i);
        }
        return res;
    }

    public byte[] getInitData() {
        byte[] data = new byte[(int) count * getTypeSize(aType)];
        for (int i = 0; i < (int) count * getTypeSize(aType); i += getTypeSize(aType)) {
            byte[] valueData = getBytesFromValue(getInitValue());
            for (int j = 0; j < valueData.length; j++) {
                data[i + j] = valueData[j];
            }
        }
        return data;
    }

    public static int getTypeSize(int type) {
        switch (type) {
            case 4: // boolean
                return 1;
            case 8: // byte
                return 1;
            case 5: // char
                return 2;
            case 9: // short
                return 2;
            case 6: // float
                return 4;
            case 10: // int
                return 4;
            case 7: // double
                return 8;
            case 11: // long
                return 8;
            default:
                throw new UnsupportedOperationException("ArrayRef - unsupported value type: " + type);
        }
    }

    public static long getSize(int type, long count) {
        return count * getTypeSize(type);
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
        return "ArrayRef{refs=" + refs + ", elemCount=" + count
                + ", aType=" + aType + ", byteOffset=" + byteOffset + '}';
    }

}
