import java.util.List;

public class ReverseArray {
    public static List<Integer> binReverse(List<Integer> arr, int lower, int upper){
        if(lower==upper) return arr;
        if (upper==lower+1) return reverse(arr,lower,upper);

        int mid =(upper+lower)/2;
        binReverse(arr,lower,mid-1);
        binReverse(arr,mid+1,upper);
        return arr;
    }

    private static List<Integer> reverse(List<Integer> arr,int lower,int upper) {
        int temp= arr.get(lower);
        arr.set(arr.get(upper),lower);
        arr.set(temp,upper);
        return arr;
    }
}
