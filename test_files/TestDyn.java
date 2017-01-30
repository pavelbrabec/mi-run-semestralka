public class TestDyn {

  private int x;
  private TestDyn test;
  public TestDyn() {
    x = 3;
  }

  public static void main () {
    TestDyn t = new TestDyn();
    t.test = new TestDyn();
    t.test.x = 5;
  }
}
