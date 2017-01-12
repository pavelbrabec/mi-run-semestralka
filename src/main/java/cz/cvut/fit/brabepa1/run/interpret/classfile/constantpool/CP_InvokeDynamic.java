package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class CP_InvokeDynamic extends CP_Item {

    public short bootstrapMethodAttrIndex;
    public short nameAndTypeIndex;
    
    CP_InvokeDynamic(ConstantPool cp, Tag tag) {
        super(cp, tag);
        try {
            bootstrapMethodAttrIndex = cp.input.readShort();
            nameAndTypeIndex = cp.input.readShort();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_InvokeDynamic.class.getName() + ": exception: " + ex);
        }
    }

    @Override
    public String toString() {
        return "CP_InvokeDynamic {tag=" + super.tag.name() + 
                ", bootstrapMethodAttrIndex=" + bootstrapMethodAttrIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex + '}';
    }

    

}
