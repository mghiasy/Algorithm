import java.util.List;

public class ReverseArray {
    public static List<Integer> binReverse(List<Integer> arr, int lower, int upper){
        if(lower>=upper) return arr;
        reverse(arr,lower,upper);
        binReverse(arr,++lower,--upper);
        return arr;
    }

    private static List<Integer> reverse(List<Integer> arr,int lower,int upper) {
        int temp= arr.get(lower);
        int upValue=arr.get(upper);
        arr.set(lower,arr.get(upper));
        arr.set(upper,temp);
        return arr;
    }
}
