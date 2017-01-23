
/**
 * Trida slouzici pro testovani interpretu
 */
public class TestPrimes {

    //todo
    public static int sqrt(int n){
        if(n < 4) {
            return n;
        }else{
            return n/2;
        }
    }
    
    public static int isPrime(int n){
        int sqrt = sqrt(n);
        for(int i=2;i<sqrt;i++){
            if(n%i==0){
                return 1;
            }
        }
        return 0;
    }
    
    public static void main(String[] args) {
        int primesFound = 0;
        for (int i = 0; i < 1000; i++) {
            if(isPrime(i)==1){
                primesFound++;
            }
        }
        //System.out.println(primesFound);
    }
}
