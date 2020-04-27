import java.util.TreeSet;

public class TreeSetSorting {
    public static TreeSet<Integer> treeSetSorting(int[] arr){
        // Declare a treeset
        TreeSet<Integer> ints = new TreeSet<Integer>();
        for(int i=0;i<arr.length;++i){
        ints.add(arr[i]);
        }
        System.out.println("Without descendingSet(): " + ints);
        return ints;
    }
}
