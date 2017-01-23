package cz.cvut.fit.brabepa1.run.interpret.classfile;

import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attr_Code;
import cz.cvut.fit.brabepa1.run.interpret.classfile.attributes.Attribute;
import cz.cvut.fit.brabepa1.run.interpret.classfile.constantpool.CP_UTF8;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class Method {

    public short accessFlags;
    public short nameIndex;
    public short descriptorIndex;
    public short attributesCount;
    public Attribute[] attributes;
    public Attr_Code codeAttribute;
    public ClassFile classFile;
    
    public Method(DataInputStream dis, ClassFile classFile) {
        try {
            this.classFile = classFile;
            codeAttribute = null;
            accessFlags = dis.readShort();
            nameIndex = dis.readShort();
            descriptorIndex = dis.readShort();
            attributesCount = dis.readShort();
            attributes = new Attribute[attributesCount];
            for (int i = 0; i < attributesCount; i++) {
                attributes[i] = ClassFileReader.readAttribute(dis, classFile);
                if (attributes[i] instanceof Attr_Code) {
                    codeAttribute = (Attr_Code) attributes[i];
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR\t" + Method.class.getName() + ": exception: " + ex);
        }
    }

    public String getName() {
        return classFile.constantPool.getItem(nameIndex, CP_UTF8.class).getStringContent();
    }
    
    public String getDescriptor() {
        return classFile.constantPool.getItem(descriptorIndex, CP_UTF8.class).getStringContent();
    }
    
    public int getArgumentCount() {
        String dsc = getDescriptor();
        String args = dsc.substring(dsc.indexOf("(") + 1, dsc.indexOf(")"));
        args = args.replaceAll("L\\S+;", "L");
        args = args.replace('[', ' ');
//        System.out.println("ARGUMETNLIST: ("+args+")");
        return args.replace(" ", "").length();
    }
    
    @Override
    public String toString() {
        String str = "Method{" + "accessFlags=" + accessFlags
                + ", nameIndex=" + nameIndex
                + ", descriptorIndex=" + descriptorIndex
                + ", attributesCount=" + attributesCount + '}';
        if (attributesCount > 0) {
            str += '\n';
        }
        for (int i = 0; i < attributesCount; i++) {
            str += "\t\t\t" + (i + 1) + ": ";
            str += attributes[i].toString();
            if (i < attributesCount - 1) str += '\n';
        }
        return str;
    }

}
