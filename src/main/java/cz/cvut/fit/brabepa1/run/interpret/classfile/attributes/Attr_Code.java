package cz.cvut.fit.brabepa1.run.interpret.classfile.attributes;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFileReader;
import cz.cvut.fit.brabepa1.run.interpret.classfile.ExceptionHandler;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Field;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author pajcak
 */
public class Attr_Code extends Attribute {

    public short maxStack;
    public short maxLocals;
    public int codeLength;
    public byte code[];
    public short exceptionTableLength;
    public ExceptionHandler[] exceptionTable;
    public short attributesCount;
    public Attribute[] attributes;

    public Attr_Code(short nameIndex, DataInputStream dis, ClassFile classFile, Field field) {
        super(nameIndex, dis, classFile, field);
        try {
            maxStack = dis.readShort();
            maxLocals = dis.readShort();
            codeLength = dis.readInt();
            code = new byte[codeLength];
            for (int i = 0; i < codeLength; i++) {
                code[i] = dis.readByte();
            }
            exceptionTableLength = dis.readShort();
            exceptionTable = new ExceptionHandler[exceptionTableLength];
            for (int i = 0; i < exceptionTableLength; i++) {
                exceptionTable[i] = new ExceptionHandler(dis);
            }
            attributesCount = dis.readShort();
        } catch (IOException ex) {
            System.out.println("ERROR\t" + Attr_Code.class.getName()
                    + ": exception: " + ex);
        }
        attributes = new Attribute[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = ClassFileReader.
                    readAttribute(dis, classFile);
        }
    }

    @Override
    public String toString() {
        String str = super.toString() + ", maxStack=" + maxStack
                + ", maxLocals=" + maxLocals
                + ", codeLength=" + codeLength
                + ", exceptionTableLength=" + exceptionTableLength
                + ", attributesCount=" + attributesCount + '}';
        //TODO print code, exceptionTable and attributes Content
        if (attributesCount > 0) {
            str += "\n\t\t\t\tAttributes\n";
        }
        for (int i = 0; i < attributesCount; i++) {
            str += "\t\t\t\t\t" + (i + 1) + ": ";
            str += attributes[i].toString();
            if (i < attributesCount - 1) {
                str += '\n';
            }
        }
        if (exceptionTableLength > 0) {
            str += "\n\t\t\t\tExceptionTable\n";
        }
        for (int i = 0; i < exceptionTableLength; i++) {
            str += "\t\t\t\t\t" + (i + 1) + ": ";
            str += exceptionTable[i].toString();
            if (i < exceptionTableLength - 1) {
                str += '\n';
            }
        }
        return str;
    }
}
