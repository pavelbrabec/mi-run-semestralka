/**
 * Trida slouzici pro testovani interpretu
 */
public class TestBool {

    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
        
        boolean x = a || b;
        boolean y = a && b;
        boolean z = a | b;
        boolean zz = a & b;
        
        boolean k = !a;
        boolean l = (a == b);
        boolean m = (a != b);
    }
}
