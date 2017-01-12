package cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 *
 * @author pajcak
 */
public class CP_Float extends CP_Item {

    public float value;

    CP_Float(ConstantPool cp, Tag tag) {
        super(cp, tag);
        try {
            byte[] byteVal = new byte[4];
            ByteBuffer.wrap(byteVal).putInt(cp.input.readInt()); // TODO needs to be tested!!
            value = ByteBuffer.wrap(byteVal).getFloat();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_Float.class.getName() + ": exception: " + ex);
        }
    }

    @Override
    public String toString() {
        return "CP_Float {tag=" + super.tag.name() + ", value=" + value + '}';
    }

}
