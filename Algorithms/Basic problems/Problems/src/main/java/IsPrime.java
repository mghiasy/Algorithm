public class IsPrime {
    //use Iterative method
    public static boolean isPrime(int n){
        if(n==2)
            return true;
        if(n % 2 == 0 || n ==1)
            return false;
        //dont count even numbers
        //every Divisor of n is smaller root of n => this loop goes n^1/2 times
        for(int i=3; i*i <= n; i=i+2){
            if(n%i ==0) return false;
        }
        return true;
    }
}
