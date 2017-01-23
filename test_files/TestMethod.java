/**
 * Trida slouzici pro testovani interpretu
 */
public class TestMethod {

    public static int foo(int x, int y) {
      return x + 3;
    }

    public static void main(String[] args) {
        int y = foo(1, 2);
        y = y + 2;
    }
}
