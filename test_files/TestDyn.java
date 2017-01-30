public class TestDyn {

  private int x;
  private TestDyn test;
  public TestDyn() {
    x = 3;
  }

  public static void main () {
    TestDyn t = new TestDyn();
    t.test = null;
    t.test = new TestDyn();
    t.x = 5;
  }
}
