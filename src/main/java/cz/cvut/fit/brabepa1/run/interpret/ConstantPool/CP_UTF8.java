package cz.cvut.fit.brabepa1.run.interpret.ConstantPool;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pajcak
 */
public class CP_UTF8 extends CP_Item {

    String string;

//    CONSTANT_Utf8_info
//        u1 tag;
//        u2 length;
//        u1 bytes[length];
    CP_UTF8(ConstantPool cp, Tag tag) {
        super(cp, tag); // --reads u1 tag
        try {
            short len = cp.input.readShort();
            byte[] bytes = new byte[len];
            for (int i = 0; i < len; i++) {
                bytes[i] = cp.input.readByte();
            }
            string = new String(bytes, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            System.out.println("ERROR\t" + CP_UTF8.class.getName() + ": exception: " + ex);
        }
    }

    @Override
    public String toString() {
        return "CP_UTF8 {tag=" + super.tag.name() + ", string=" + string + '}';
    }

}
