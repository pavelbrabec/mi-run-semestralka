package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class CP_Class extends CP_Item {

    public short nameIndex;

    CP_Class(ConstantPool cp, Tag tag) {
        super(cp, tag);
        try {
            nameIndex = cp.input.readShort();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_Class.class.getName() + ": exception: " + ex);
        }
    }

    public String getClassName() {
        return constantPool.getItem(nameIndex, CP_UTF8.class).getStringContent();
    }

    @Override
    public String toString() {
        return "CP_Class {tag=" + super.tag.name() + ", nameIndex=" + nameIndex + '}';
    }

}
