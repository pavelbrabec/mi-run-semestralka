package cz.cvut.fit.brabepa1.run.interpret.classfile;

import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attribute;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_UTF8;
import cz.cvut.fit.brabepa1.run.interpret.heap.ObjectRef;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
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
            initValue();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + Field.class.getName() + ": exception: " + ex);
        }
    }

    private void initValue() {
        switch (getDescriptor().charAt(0)) {
            case 'B': // byte
                value = (byte) 0;
                break;
            case 'Z': // boolean
                value = false;
                break;
            case 'C': // char
                value = '\u0000';
                break;
            case 'S': // short
                value = (short) 0;
                break;
            case 'F': // float
                value = new Float(0);
                break;
            case 'I': // int
                value = 0;
                break;
            case 'J': // long
                value = new Long(0);
                break;
            case 'D': // double
                value = new Double(0);
                break;
            case 'L': // object reference
                value = new Long(-1); // only its offset in heap
                break;
            case '[': // array reference
                throw new UnsupportedOperationException("Reference to array not implemented.");
            default:
                throw new UnsupportedOperationException("Invalid field descriptor!");
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

    public byte[] getByteFromData(Object input) {
        byte[] res = null;
        ByteBuffer data = null;
        Object obj = null;
        if (input instanceof ObjectRef) {
            obj = ((ObjectRef)input).getByteOffset();
        } else {
            obj = input;
        }// TODO else if array
        
        // potreba, protoze v obj muze bejt "int" a ten se blbe convertuje napr na byte nebo short
        // Toto muze nastat napr. kdyz do boolean promenne priradim 1 
        //   -> vyvola to instrukci "BiPush 1", ktera pushuje integer na stack
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(obj);
            data = ByteBuffer.wrap(bos.toByteArray());
        } catch (IOException ex) {
            throw new UnsupportedOperationException("getByteFromData() -" + ex);
        }

        switch (getDescriptor().charAt(0)) {
            case 'B': // byte
                res = new byte[]{data.get(data.capacity() - 1)};
                break;
            case 'Z': // boolean
                res = new byte[]{data.get(data.capacity() - 1)};
                break;
            case 'C': // char
                res = new byte[]{
                    data.get(data.capacity() - 2),
                    data.get(data.capacity() - 1)
                };
                break;
            case 'S': // short
                res = new byte[]{
                    data.get(data.capacity() - 2),
                    data.get(data.capacity() - 1)
                };
                break;
            case 'F': // float
                res = new byte[]{
                    data.get(data.capacity() - 4),
                    data.get(data.capacity() - 3),
                    data.get(data.capacity() - 2),
                    data.get(data.capacity() - 1)
                };
                break;
            case 'I': // int
                res = new byte[]{
                    data.get(data.capacity() - 4),
                    data.get(data.capacity() - 3),
                    data.get(data.capacity() - 2),
                    data.get(data.capacity() - 1)
                };
                break;
            case 'J': // long
                res = new byte[]{
                    data.get(data.capacity() - 8),
                    data.get(data.capacity() - 7),
                    data.get(data.capacity() - 6),
                    data.get(data.capacity() - 5),
                    data.get(data.capacity() - 4),
                    data.get(data.capacity() - 3),
                    data.get(data.capacity() - 2),
                    data.get(data.capacity() - 1)
                };
                break;
            case 'D': // double
                res = new byte[]{
                    data.get(data.capacity() - 8),
                    data.get(data.capacity() - 7),
                    data.get(data.capacity() - 6),
                    data.get(data.capacity() - 5),
                    data.get(data.capacity() - 4),
                    data.get(data.capacity() - 3),
                    data.get(data.capacity() - 2),
                    data.get(data.capacity() - 1)
                };
                break;
            case 'L': // object reference
                res = new byte[]{
                    data.get(data.capacity() - 8),
                    data.get(data.capacity() - 7),
                    data.get(data.capacity() - 6),
                    data.get(data.capacity() - 5),
                    data.get(data.capacity() - 4),
                    data.get(data.capacity() - 3),
                    data.get(data.capacity() - 2),
                    data.get(data.capacity() - 1)
                };
                break;
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
                return new ObjectRef(buf.getLong(0));
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
