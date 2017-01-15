/**
 * Trida slouzici pro testovani interpretu
 */
public class TestLoops {

    public static void main(String[] args) {
        int x=0;
        for(int i=0;i<10;i++){
            x++;
        }
        while(x<20){
            x++;
        }
        do{
            x++;
        }while(x<30);
    }
}
