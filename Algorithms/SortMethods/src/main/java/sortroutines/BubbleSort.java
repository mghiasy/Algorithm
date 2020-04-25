package sortroutines;

public class BubbleSort {
    public int[] bubbleSort(int[] arr){
        for(int i=0;i<arr.length;++i){
            for(int j=0;j<arr.length-1;++j){
                if(arr[i]>arr[j]){
                    swap(arr,j,j+1);
                }
            }
        }
        return arr;
    }
    public void swap(int[] arr,int i, int j){
        int temp= arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
