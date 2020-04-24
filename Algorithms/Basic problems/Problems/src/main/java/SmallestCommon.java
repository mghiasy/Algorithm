import java.util.ArrayList;
import java.util.List;

public class SmallestCommon {
    //find the smallest positive integer z with the property that both x and y are factors of z

    //least common multiple (lcm)= product divided by their greatest common divisor (gcd)
    //  lcm(a, b) = ab/gcd(a,b).
    public static int smallestCommon(int x, int y) { //implement
        //Calc GCD
        List<Integer> xFactors= new ArrayList<>();
        List<Integer> yFactors= new ArrayList<>();
        int gcd=1;
        for (int i=2;i<=x/2;i++){
            if(x % i==0){
                xFactors.add(i);
            }
        }
        xFactors.add(x);
        for (int i=2;i<=y/2;i++){
            if(y % i==0){
                yFactors.add(i);
            }
        }
        yFactors.add(y);
        exitPoint: for(int i=xFactors.size()-1; i>=0;i--){
            int xx= xFactors.get(i);
            for (int j =yFactors.size()-1;j>=0;j--){
                int yy= yFactors.get(j);
                //if(xFactors.get(i)==yFactors.get(j)){
                if(xx==yy){
                    gcd=xFactors.get(i);
                    break exitPoint;
                }
            }
        }
        return x*y/gcd;
        //with recursive : return gcd(a-b, b);

    }
}
