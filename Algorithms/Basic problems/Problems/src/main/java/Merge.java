import java.util.ArrayList;
import java.util.List;

public class Merge {
    public static List<Integer> merge(int[] arr1, int[] arr2){
        List<Integer> newArr = new ArrayList<>();
        int counter1=0,  counter2=0;
        while (counter1 < arr1.length && counter2 < arr2.length){
                if(arr1[counter1] > arr2[counter2]){
                    newArr.add(arr2[counter2]);
                    counter2++;
                }
                else if(arr1[counter1] < arr2[counter2]){
                    newArr.add(arr1[counter1]);
                    counter1++;
                }
                else if(arr1[counter1] == arr2[counter2]){
                    newArr.add(arr2[counter2]);
                    newArr.add(arr1[counter1]);
                    counter1++;
                    counter2++;
                }
        }
        return newArr;
    }
}
