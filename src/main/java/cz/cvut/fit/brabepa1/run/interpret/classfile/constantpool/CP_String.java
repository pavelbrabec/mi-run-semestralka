package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class CP_String extends CP_Item {

    public short stringIndex;

    CP_String(ConstantPool cp, Tag tag) {
        super(cp, tag);
        try {
            stringIndex = cp.input.readShort();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_String.class.getName() + ": exception: " + ex);
        }
    }

    @Override
    public String toString() {
        return "CP_String {tag=" + super.tag.name() + ", stringIndex=" + stringIndex + '}';
    }

}
