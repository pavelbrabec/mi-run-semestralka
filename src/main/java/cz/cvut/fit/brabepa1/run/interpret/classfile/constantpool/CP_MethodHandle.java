package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class CP_MethodHandle extends CP_Item {

    
    public byte referenceKind;
    public short referenceIndex;

    CP_MethodHandle(ConstantPool cp, Tag tag) {
        super(cp, tag);
        try {
            referenceKind = cp.input.readByte();
            referenceIndex = cp.input.readShort();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_MethodHandle.class.getName() + ": exception: " + ex);
        }
    }

    @Override
    public String toString() {
        return "CP_MethodHandle {tag=" + super.tag.name() + ", referenceKind=" + referenceKind + 
                ", referenceIndex=" + referenceIndex + '}';
    }

}
