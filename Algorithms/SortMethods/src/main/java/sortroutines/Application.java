package sortroutines;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Integer> arr= Arrays.asList(1,5,3,6,4,19,18) ;
        System.out.println("Sorted array :"+ ImprovedBubbleSort.bubbleSort(arr));
        List<Integer> sortedArr= Arrays.asList(1,2,3,4,5,6,7);
        System.out.println("Sorted array :"+ ImprovedBubbleSort.bubbleSort(arr));
    }
}
