/**
 * Trida slouzici pro testovani interpretu
 */
public class TestConstr {

    public void method() {}

    public int instvar;
    public TestConstr() {
      int x = 2;
//      instvar = 3;
    }

    public static void main(String[] args) {
      TestConstr t = new TestConstr();
//      t.method();
    }
}
