package cz.cvut.fit.brabepa1.run.interpret.instructions;

import cz.cvut.fit.brabepa1.run.interpret.VirtualMachine;

/**
 *
 * @author pavel
 */
public abstract class JavaInstruction implements Instruction {

    /**
     * Metoda provede instrukci ve VM.
     * @param vm 
     */
    @Override
    public abstract void execute(VirtualMachine vm);
        
    /**
     * Metoda nemusi byt implementovanu u jedno bytovych instrukci.
     * @return delka instrukce v bytech 
     */
    @Override
    public int bytes() {
        return 1;
    }

    /**
     * Metoda nemusi byt implementovanu u jedno bytovych instrukci.
     * Metoda nastavi parametry instrukci delsich nez 1 byte.
     * @param pointer
     * @param bytecode 
     */
    @Override
    public void setParameters(int pointer, byte[] bytecode) {
    }

    /**
     * Vraci jmeno tridy, takze jmeno instrukce.
     * @return 
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
    
    protected final int branchoffset(byte b1, byte b2){
        System.out.println("br offset");
        System.out.println(b1 + "\t" +Integer.valueOf(b1));
        System.out.println(b2 + "\t" +Integer.valueOf(b2));
        System.out.println(b1 << 8 | b2);
        return b1 << 8 | b2;
    }
    
}