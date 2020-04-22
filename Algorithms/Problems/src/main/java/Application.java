import java.util.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("App");
//        for (int i = 0; i < 20; ++i) {
//            System.out.println(generate(i));
//        }

        int[] array ={2,9,4,6,12,3,14};
        System.out.println("SumFound result: " +SumFound.sumFound(array,10));
        List<Integer> listArray = Arrays.asList(3, 9, 4, 6, 12, 2, 14);
        System.out.println("SumFound result list: " +SumFound.sumFound(listArray,10));

        System.out.println("Second smallest result : " +SecondSmallest.secondSmallest(array));
        int[] sortedArray ={2,3,6,12,18,20};
        System.out.println("FindElement result : " +FindElement.find(sortedArray,11));
        long sequence=GenerateSequence.generate(5);
        System.out.println("Generate sequence result : " + sequence);

        System.out.println("LCM ="+SmallestCommon.smallestCommon(10,12));

    }
}
