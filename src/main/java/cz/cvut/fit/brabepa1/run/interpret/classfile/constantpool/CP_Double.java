package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class CP_Double extends CP_Item {

    double value;

    CP_Double(ConstantPool cp, Tag tag) {
        super(cp, tag);
        try {
            value = cp.input.readDouble();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_Double.class.getName() + ": exception: " + ex);
        }
    }

    @Override
    public String toString() {
        return "CP_Double {tag=" + super.tag.name() + ", value=" + value + '}';
    }

}
