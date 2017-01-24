package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class CP_FieldRef extends CP_Item {

    public short classIndex;
    public short nameAndTypeIndex;
    
    CP_FieldRef(ConstantPool cp, Tag tag) {
        super(cp, tag);
        try {
            classIndex = cp.input.readShort();
            nameAndTypeIndex = cp.input.readShort();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_FieldRef.class.getName() + ": exception: " + ex);
        }
    }

    public CP_NameAndType getNameAndType() {
        return constantPool.getItem(nameAndTypeIndex, CP_NameAndType.class);
    }
    @Override
    public String toString() {
        return "CP_FieldRef {tag=" + super.tag.name() + ", classIndex=" + classIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex + '}';
    }

    

}
