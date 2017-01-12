package cz.cvut.fit.brabepa1.run.interpret.classfile.attributes;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Field;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavel
 */
public class Attr_SourceFile extends Attribute{
    
    public short sourcefile_index;

    public Attr_SourceFile(short attrNameIndex, DataInputStream dis, ClassFile classFile, Field field) {
        super(attrNameIndex, dis, classFile, field);
        try {
            sourcefile_index = dis.readShort();
        } catch (IOException ex) {
            Logger.getLogger(Attr_SourceFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
