import java.util.TreeSet;

public class TreeSetSorting {
    public static TreeSet<Integer> treeSetSorting(int[] arr){
        // Declare a treeset
        TreeSet<Integer> ints = new TreeSet<Integer>();
        for(int i=0;i<arr.length;++i){
        ints.add(arr[i]);
        }

//        // Initialize treeset with predefined set in reverse order
//        // using descendingSet()
//        TreeSetSorting<Object> intsReverse = (TreeSetSorting<Object>)ints.descendingSet();
//
//        // Print the set
       System.out.println("Without descendingSet(): " + ints);
//        System.out.println("With descendingSet(): " + intsReverse);
        return ints;
    }
}
