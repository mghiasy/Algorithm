

import java.util.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("App");

        int[] array ={2,9,4,6,12,3,14};
        List<Integer> list= Arrays.asList(2,9,4,6,12,3);
        System.out.println("Reversed result: " + ReverseArray.binReverse(list,0,array.length-1));

    }
}
