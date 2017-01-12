package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class CP_Long extends CP_Item {

    public long value;

    CP_Long(ConstantPool cp, Tag tag) {
        super(cp, tag);
        try {
            value = cp.input.readLong();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_Long.class.getName() + ": exception: " + ex);
        }
    }

    @Override
    public String toString() {
        return "CP_Long {tag=" + super.tag.name() + ", value=" + value + '}';
    }

}
