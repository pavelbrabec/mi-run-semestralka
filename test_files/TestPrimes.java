
/**
 * Trida slouzici pro testovani interpretu
 */
/**
 *
 * @author pavel
 */
public class TestPrimes {

    public static int sqrt(int n) {
        if (n <= 5) {
            return n;
        } else {
            int tmp = n / 2;
            while ((tmp / 2) * (tmp / 2) >= n) {
                tmp /= 2;
            }
            return tmp;
        }
    }
    
    public static int isPrime(int n){
        if(n==0 || n==1){
            return 0;
        }
        if(n==2 || n==3 ){
            return 1;
        }
        int sqrt = sqrt(n);
        for(int i=2;i<sqrt;i++){
            if(n%i==0){
                return 0;
            }
        }
        return 1;
    }
    
    public static void main(String[] args) {
        int primesFound = 0;
        for (int i = 1; i < 100000; i++) {
            if(isPrime(i)==1){
                //System.out.println(i);
                primesFound++;
            }
        }
        System.out.println("--------");
        System.out.println(primesFound);
    }
}
