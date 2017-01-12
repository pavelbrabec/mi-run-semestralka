package cz.cvut.fit.brabepa1.run.interpret.classfile.attributes;

import cz.cvut.fit.brabepa1.run.interpret.classfile.ClassFile;
import cz.cvut.fit.brabepa1.run.interpret.classfile.Field;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavel
 */
public class Attr_LineNumberTable extends Attribute{
    
    short line_number_table_length;
    LineNumberTable[] line_number_table;

    public Attr_LineNumberTable(short attrNameIndex, DataInputStream dis, ClassFile classFile, Field field) {
        super(attrNameIndex, dis, classFile, field);
        try {
            line_number_table_length = dis.readShort();
            line_number_table = new LineNumberTable[line_number_table_length];
            for(int i=0;i<line_number_table_length;i++){
                short startPc = dis.readShort();
                short lineNumber = dis.readShort();
                line_number_table[i]= new LineNumberTable(startPc, lineNumber);
            }
        } catch (IOException ex) {
            Logger.getLogger(Attr_LineNumberTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "Attr_LineNumberTable{" + "line_number_table_length=" + line_number_table_length +
                ", line_number_table=" + Arrays.toString(line_number_table) + '}';
    }  

}

class LineNumberTable{
    
    short startPc;
    short lineNumber;

    public LineNumberTable(short startPc, short lineNumber) {
        this.startPc = startPc;
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return "LineNumberTable{" + "startPc=" + startPc + ", lineNumber=" + lineNumber + '}';
    }
     
}
