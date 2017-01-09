package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

import java.io.DataInputStream;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_Item.Tag;
import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class ConstantPool {

    final int count;
    DataInputStream input;

    CP_Item[] items;

    public ConstantPool(int count, DataInputStream dis) {
        this.count = count;
        input = dis;
        items = new CP_Item[count];
        readItems();
    }

    public CP_Item getItem(int i) {
        return items[i - 1];
    }

    public <T extends CP_Item> T getItem(int i, Class<T> cl) {
        return cl.cast(getItem(i));
    }

    //read CP_items from input and store to items
    private void readItems() {
        for (int i = 0; i < this.count; i++) {
            Tag tag = null;
            try {
                tag = Tag.getTag(input.readByte());
            } catch (IOException ex) {
                System.out.println("ERROR\t" + ConstantPool.class.getName() + ": exception: " + ex);
                return;
            }
            switch (tag) {
                case CLASS:
                    items[i] = new CP_Class(this, tag);
                    break;
                case UTF8:
                    items[i] = new CP_UTF8(this, tag);
                    break;
                case STRING:
                    items[i] = new CP_String(this, tag);
                    break;
                case METHODREF:
                    items[i] = new CP_MethodRef(this, tag);
                    break;
                case FIELDREF:
                    items[i] = new CP_FieldRef(this, tag);
                    break;
                case INTERFACEMETHODREF:
                    items[i] = new CP_InterfaceMethodRef(this, tag);
                    break;
                case NAMEANDTYPE:
                    items[i] = new CP_NameAndType(this, tag);
                    break;
                case DOUBLE:
                    items[i] = new CP_Double(this, tag);
                    break;
                case INTEGER:
                    items[i] = new CP_Integer(this, tag);
                    break;
                case FLOAT:
                    items[i] = new CP_Float(this, tag);
                    break;
                case LONG:
                    items[i] = new CP_Long(this, tag);
                    break;
                case METHODHANDLE:
                    items[i] = new CP_MethodHandle(this, tag);
                    break;
                case INVOKEDYNAMIC:
                    items[i] = new CP_InvokeDynamic(this, tag);
                    break;
                case METHODTYPE:
                    items[i] = new CP_MethodType(this, tag);
                    break;
                default:
                    System.out.println("ERROR\t" + ConstantPool.class.getName() + ": Tag not found: " + tag);
                    break;
            }
        }
    }

    @Override
    public String toString() {
        String str = "ConstantPool {count=" + count + "}\n";
        for (int i = 0; i < items.length; i++) {
            str += "\t\t" + (i+1) + ": ";
            str += items[i].toString();
            if (i < items.length - 1 && items.length > 1) {
                str += ", ";
            }
            str += '\n';
        }
        return str;
    }

}
