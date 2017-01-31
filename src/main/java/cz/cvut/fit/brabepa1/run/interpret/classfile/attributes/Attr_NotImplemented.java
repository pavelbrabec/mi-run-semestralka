package cz.cvut.fit.brabepa1.run.interpret.classfile.attributes;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Field;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class Attr_NotImplemented extends Attribute {

    public byte[] body;

    public Attr_NotImplemented(short nameIndex, DataInputStream dis, ClassFile classFile, Field field) {
        super(nameIndex, dis, classFile, field);
        body = new byte[attrLength];
        try {
            for (int i = 0; i < attrLength; i++) {
                body[i] = dis.readByte();
            }
        } catch (IOException ex) {
            System.out.println("ERROR\t" + Attr_NotImplemented.class.getName()
                    + ": exception: " + ex);
        }
        if (VirtualMachine.VM_DEBUG) {
            System.out.println("WARNING\t" + Attr_NotImplemented.class.getName()
                    + "Just read attribute which is not implemented!");
        }
    }

    @Override
    public String toString() {
        return super.toString() + '}';
    }

}
