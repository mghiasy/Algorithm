public class LogCalc {
    //We know log(x) base b = log (x) base 10 /log (b) base 10
    //So log(x) base 2 = log (x) base 10 /log 2 base 10
    public static double calcLog (double x){
        return  Math.log(x)/Math.log(2);
    }
}
