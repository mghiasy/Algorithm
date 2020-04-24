import java.util.ArrayList;
import java.util.List;

public class Merge {
    public static List<Integer> merge(int[] arr1, int[] arr2){
        List<Integer> newArr = new ArrayList<>();
        int counter1=0,  counter2=0;
        int size=arr2.length;
            while (counter1 < arr1.length && counter2<arr2.length){
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
        if(counter1<arr1.length-1) {
            for(int i=counter1;i<arr1.length;i++){
                newArr.add(arr1[i]);
            }
        }
        if(counter2<arr2.length-1) {
            for(int i=counter2;i<arr2.length;i++){
                newArr.add(arr2[i]);
            }
        }
        return newArr;
    }
}
