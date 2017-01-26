
import java.io.IOException;

/**
 *
 * @author pavel
 */
public class TestInput {
    
    public static void main(String[] args) throws IOException {
        System.out.println("Insert character:");
        int a = System.in.read();
        System.out.println("Inserted character:");
        System.out.println(a);
    }
}
