/**
 *
 * @author pajcak
 */

public class TestArrays {
  private int    size;
  private byte    [] bytes;
  private boolean [] bools;
  private char    [] chars;
  private short   [] shorts;
  private float   [] floats;
  private int     [] ints;
  private long    [] longs;
  private double  [] doubles;

  public TestArrays () {
    size = 3;
	  bytes   = new byte [size];
	  bools   = new boolean [size];
	  chars   = new char [size];
	  shorts  = new short [size];
	  floats  = new float [size];
	  ints    = new int [size];
	  longs   = new long [size];
	  doubles = new double [size];
  }

    public static void main(String[] args) {
      TestArrays obj = new TestArrays();
      for (int i = 0 ; i < obj.size; i++) {
        obj.bytes[i]   = 9;
        obj.bools[i]   = true;
        obj.chars[i]   = 'c';
        obj.shorts[i]  = 8;
        obj.floats[i]  = 1.23f;
        obj.ints[i]    = i+1;
        obj.longs[i]   = 7L;
        obj.doubles[i] = 3.45;
      }
    }

}
