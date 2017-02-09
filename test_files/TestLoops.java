/**
 * Trida slouzici pro testovani interpretu
 */
public class TestLoops {

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
        }
        for(int i=20;i>=0;i--){
        }
        int x = 0;
        while(x<20){
            x++;
        }
        do{
            x++;
        }while(x<30);
    System.out.println("Vystup: ");
    System.out.println(x);
    }
}
