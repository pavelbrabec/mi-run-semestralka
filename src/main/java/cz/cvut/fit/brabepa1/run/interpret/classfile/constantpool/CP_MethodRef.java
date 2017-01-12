package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class CP_MethodRef extends CP_Item {

    public short classIndex;
    public short nameAndTypeIndex;
    
    CP_MethodRef(ConstantPool cp, Tag tag) {
        super(cp, tag);
        try {
            classIndex = cp.input.readShort();
            nameAndTypeIndex = cp.input.readShort();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_MethodRef.class.getName() + ": exception: " + ex);
        }
    }

    @Override
    public String toString() {
        return "CP_MethodRef {tag=" + super.tag.name() + ", classIndex=" + classIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex + '}';
    }

    

}
