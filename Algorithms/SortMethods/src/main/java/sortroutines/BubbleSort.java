package sortroutines;

import java.util.List;

public class BubbleSort {
    public static List<Integer> bubbleSort(List<Integer> arr){
        for(int i=0;i<arr.size();++i){
            //with every loop latest not sorted element will be sort
            //since i last elements are sorted => no need to check them
            boolean swapped = false;
            for(int j=0;j<arr.size()-i-1;++j){
                if(arr.get(i)>arr.get(j)){
                    swap(arr,j,j+1);
                    swapped= true;
                }
                if(swapped == false)
                    break;
            }
        }
        return arr;
    }
    public static void swap(List<Integer> arr,int i, int j){
        int temp= arr.get(i);
        arr.set(arr.get(j),i);
        arr.set(temp,j);
    }
}

