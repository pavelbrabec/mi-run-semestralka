public class TestDyn {

  private int x;
  private TestDyn test;
  private long y;
  public TestDyn() {
    x = 3;
  }

  public static void main () {
    TestDyn t = new TestDyn();
    t.y = 8;
    t.test = null;
    t.test = new TestDyn();
    t.test.x = 5;
    t.test.y = 9;
  }
}
