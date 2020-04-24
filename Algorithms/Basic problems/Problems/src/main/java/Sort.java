import java.util.ArrayList;
import java.util.LinkedList;

public class Sort {
    public static void sort(ArrayList<Integer> list) {
        //implement
        for(int i=0;i<list.size()-1;i++){
            for(int j=i+1;j<list.size()-1;j++){
                if(list.get(i)>list.get(j)) {
                    int temp = list.get(i);
                    list.set(i,list.get(j));
                    list.set(j,temp);
                }
            }
        }
    }

    //sorting is to be done in-place
    public static void sort(LinkedList<Integer> list) {
        //implement

    }
}
