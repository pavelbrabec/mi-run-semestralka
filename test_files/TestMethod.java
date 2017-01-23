/**
 * Trida slouzici pro testovani interpretu
 */
public class TestMethod {

    public static void foo() {
        int x = 0;
        int y = 0;
        x = 2;
        y = x;
    }

    public static void main(String[] args) {
        foo();
    }
}
