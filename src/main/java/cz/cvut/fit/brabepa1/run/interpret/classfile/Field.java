package cz.cvut.fit.brabepa1.run.interpret.classfile;

import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attribute;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_UTF8;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 *
 * @author pavel
 */
public class Field {

    public short accessFlags;
    public short nameIndex;
    public short descriptorIndex;
    public short attributesCount;
    public Attribute[] attributes;
    public ClassFile classFile;
    private Object value;
    private long byteOffsetInClass;

    public Field(DataInputStream dis, ClassFile classFile) {
        try {
            this.byteOffsetInClass = 0;
            this.classFile = classFile;
            accessFlags = dis.readShort();
            nameIndex = dis.readShort();
            descriptorIndex = dis.readShort();
            attributesCount = dis.readShort();
            attributes = new Attribute[attributesCount];
            for (int i = 0; i < attributesCount; i++) {
                attributes[i] = ClassFileReader.readAttribute(dis, classFile);
            }
        } catch (IOException ex) {
            System.out.println("ERROR\t" + Field.class.getName() + ": exception: " + ex);
        }
    }

    public String getName() {
        return classFile.constantPool.getItem(nameIndex, CP_UTF8.class).getStringContent();
    }

    public String getDescriptor() {
        return classFile.constantPool.getItem(descriptorIndex, CP_UTF8.class).getStringContent();
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return The number of bytes of all fields before this field in the class
     * E.g. If this is the first field in class it returns 0;
     */
    public long getByteOffset() {
        return byteOffsetInClass;
    }

    public void setByteOffset(long byteOffsetInClass) {
        this.byteOffsetInClass = byteOffsetInClass;
    }

    public long getSizeInBytes() {
        switch (getDescriptor().charAt(0)) {
            case 'B': // byte
                return 1;
            case 'Z': // boolean
                return 1;
            case 'C': // char
                return 2;
            case 'S': // short
                return 2;
            case 'F': // float
                return 4;
            case 'I': // int
                return 4;
            case 'J': // long
                return 8;
            case 'D': // double
                return 8;
            case 'L': // object reference
                return 8;
            case '[': // array reference
                throw new UnsupportedOperationException("Reference to array not implemented.");
            default:
                throw new UnsupportedOperationException("Invalid field descriptor!");
        }
    }

    public byte[] getByteFromData(Object data) {
        byte[] res;
        switch (getDescriptor().charAt(0)) {
            case 'B': // byte
                if (data == null) {
                    data = (byte) 0;
                }
                res = ByteBuffer.allocate(1).put((Byte) data).array();
                break;
            case 'Z': // boolean
                if (data == null) {
                    data = false;
                }
                boolean b = (Boolean) data;
                res = ByteBuffer.allocate(1).put((byte) (b ? 1 : 0)).array();
                break;
            case 'C': // char
                if (data == null) {
                    data = '\u0000';
                }
                res = ByteBuffer.allocate(2).putChar((Character) data).array();
                break;
            case 'S': // short
                if (data == null) {
                    data = (short) 0;
                }
                res = ByteBuffer.allocate(2).putShort((Short) data).array();
                break;
            case 'F': // float
                if (data == null) {
                    data = new Float(0);
                }
                res = ByteBuffer.allocate(4).putFloat((Float) data).array();
                break;
            case 'I': // int
                if (data == null) {
                    data = 0;
                }
                res = ByteBuffer.allocate(4).putInt(((Integer) data)).array();
                break;
            case 'J': // long
                if (data == null) {
                    data = new Long(0);
                }
                res = ByteBuffer.allocate(8).putLong((Long) data).array();
                break;
            case 'D': // double
                if (data == null) {
                    data = new Double(0);
                }
                res = ByteBuffer.allocate(8).putDouble((Double) data).array();
                break;
            case 'L': // object reference
                throw new UnsupportedOperationException("LABLABLALA!");
            case '[': // array reference
                throw new UnsupportedOperationException("Reference to array not implemented.");
            default:
                throw new UnsupportedOperationException("Invalid field descriptor!");
        }
        return res;
    }

    /**
     * Transforms given byte array into an object, using this field's'
     * descriptor!!
     *
     * @param bytes bytes to be converted to final Object
     * @return the Object generated from bytes[] array
     */
    public Object getDataFromBytes(byte[] bytes) {
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        switch (getDescriptor().charAt(0)) {
            case 'B':
                return buf.get(0);
            case 'Z':
                return buf.get(0);
            case 'C':
                return buf.getChar(0);
            case 'S':
                return buf.getShort(0);
            case 'F':
                return buf.getFloat(0);
            case 'I':
                return buf.getInt(0);
            case 'J':
                return buf.getLong(0);
            case 'D':
                return buf.getDouble(0);
            case 'L': // object reference
                throw new UnsupportedOperationException("LABLABLALA!");
            case '[': // array reference
                throw new UnsupportedOperationException("Reference to array not implemented.");
            default:
                throw new UnsupportedOperationException("Invalid field descriptor!");
        }
    }

    @Override
    public String toString() {
        String str = "Field{" + "accessFlags=" + accessFlags
                + ", nameIndex=" + nameIndex
                + ", descriptorIndex=" + descriptorIndex
                + ", attributesCount=" + attributesCount + '}';
        if (attributesCount > 0) {
            str += '\n';
        }
        for (int i = 0; i < attributesCount; i++) {
            str += "\t\t\t" + (i + 1) + ": ";
            str += attributes[i].toString();
            if (i < attributesCount - 1) {
                str += '\n';
            }
        }
        return str;
    }

}
