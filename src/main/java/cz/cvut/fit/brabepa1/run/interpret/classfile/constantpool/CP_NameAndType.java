package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class CP_NameAndType extends CP_Item {

    public short nameIndex;
    public short descriptorIndex;
    
    CP_NameAndType(ConstantPool cp, Tag tag) {
        super(cp, tag);
        try {
            nameIndex = cp.input.readShort();
            descriptorIndex = cp.input.readShort();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_NameAndType.class.getName() + ": exception: " + ex);
        }
    }

    public String getName() {
        return constantPool.getItem(nameIndex, CP_UTF8.class).getStringContent();
    }

    public String getDescriptor() {
        return constantPool.getItem(descriptorIndex, CP_UTF8.class).getStringContent();
    }
    
    @Override
    public String toString() {
        return "CP_NameAndType {tag=" + super.tag.name() + ", nameIndex=" + nameIndex +
                ", descriptorIndex=" + descriptorIndex + '}';
    }

    

}
