package cz.cvut.fit.brabepa1.run.interpret;

import cz.cvut.fit.brabepa1.run.interpret.ConstantPool.CP_Item;
import cz.cvut.fit.brabepa1.run.interpret.ConstantPool.ConstantPool;
import cz.cvut.fit.brabepa1.run.interpret.exceptions.MagicNumberMismatch;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author pavel
 */
public class ClassFileReader {

    public static ClassFile readFromFile(String path){
        ClassFile cf = new ClassFile();
        try(DataInputStream fis = new DataInputStream(new FileInputStream(path))){
            cf.magicNumber = fis.readInt();
            cf.minorVersion = fis.readShort();
            cf.majorVersion = fis.readShort();
            cf.constantPoolCount = fis.readShort(); // count - 1 is the # items in constantPool [by oracle doc]
            cf.constantPool = new ConstantPool(cf.constantPoolCount - 1, fis);
            
        }catch(FileNotFoundException ex){
            System.out.println("ERROR\tFile not found: "+ path);
        }catch(IOException ex){
            System.out.println("ERROR\tUnexpected IOException occured: "+ ex.getMessage());
        }
        return cf;
    }
    
    
}
