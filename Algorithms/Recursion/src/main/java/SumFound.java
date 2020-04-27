import java.util.List;

public class SumFound {
    //determine whether arr contains two distinct numbers x and y so that x + y = z
    public static boolean sumFound(int[] arr, int z) {
        if (arr == null || arr.length < 2) {
            return false;
        }
        //implement
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == z) {
                    return true;
                }
            }
        }
        return false;
    }

    //determine whether there is a subset of L the sum of whose values is k
    public static boolean sumFound(List list, int k) { //implement
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                sum = (Integer)list.get(i) + (Integer)list.get(j);
                if (sum == k) {
                    return true;
                }
            }
        }
        return false;
    }
}
