package cz.cvut.fit.brabepa1.run.interpret.ConstantPool;

import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class CP_NameAndType extends CP_Item {

    short nameIndex;
    short descriptorIndex;
    
    CP_NameAndType(ConstantPool cp, Tag tag) {
        super(cp, tag);
        try {
            nameIndex = cp.input.readShort();
            descriptorIndex = cp.input.readShort();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_NameAndType.class.getName() + ": exception: " + ex);
        }
    }

    @Override
    public String toString() {
        return "CP_NameAndType {tag=" + super.tag.name() + ", nameIndex=" + nameIndex +
                ", descriptorIndex=" + descriptorIndex + '}';
    }

    

}
