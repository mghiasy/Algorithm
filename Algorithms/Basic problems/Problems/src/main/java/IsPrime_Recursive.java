public class IsPrime_Recursive {
    static int i=1;
    public static boolean isPrime(int n, int i){
        if(n<=1)
            return false;
        if(n % i == 0)
            return true;
        return isPrime(n,++i);

    }
}
