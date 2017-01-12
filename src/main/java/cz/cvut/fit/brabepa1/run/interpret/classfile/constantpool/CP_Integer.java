package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class CP_Integer extends CP_Item {

    public int value;

    CP_Integer(ConstantPool cp, Tag tag) {
        super(cp, tag);
        try {
            value = cp.input.readInt();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_Integer.class.getName() + ": exception: " + ex);
        }
    }

    @Override
    public String toString() {
        return "CP_Integer {tag=" + super.tag.name() + ", value=" + value + '}';
    }

}
