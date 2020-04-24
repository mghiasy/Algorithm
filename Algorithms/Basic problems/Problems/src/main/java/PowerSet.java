import java.util.List;
import java.util.Set;

public class PowerSet {
    //Given a List L without duplicate elements, return another list that consists of all subsets of L (without duplicates)
    public static List<Set<Integer>> powerSet(List list) {
        //implement
        for(int i=0;i<list.size()-1;i++){

        }
        return null;
    }

    //Recursive method
    //public static Set<Set<Integer>> powerSet(Set<Integer> originalSet) {
    //        Set<Set<Integer>> sets = new HashSet<Set<Integer>>();
    //        if (originalSet.isEmpty()) {
    //            sets.add(new HashSet<Integer>());
    //            return sets;
    //        }
    //        List<Integer> list = new ArrayList<Integer>(originalSet);
    //        Integer head = list.get(0);
    //        Set<Integer> rest = new HashSet<Integer>(list.subList(1, list.size()));
    //        for (Set<Integer> set : powerSet(rest)) {
    //            Set<Integer> newSet = new HashSet<Integer>();
    //            newSet.add(head);
    //            newSet.addAll(set);
    //            sets.add(newSet);
    //            sets.add(set);
    //        }
    //        return sets;
    //    }
}
