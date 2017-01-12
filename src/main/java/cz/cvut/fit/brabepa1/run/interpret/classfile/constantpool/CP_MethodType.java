package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class CP_MethodType extends CP_Item {

    public short descriptorIndex;

    CP_MethodType(ConstantPool cp, Tag tag) {
        super(cp, tag);
        try {
            descriptorIndex = cp.input.readShort();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_MethodType.class.getName() + ": exception: " + ex);
        }
    }

    @Override
    public String toString() {
        return "CP_MethodType {tag=" + super.tag.name() + ", descriptorIndex=" + descriptorIndex + '}';
    }

}
