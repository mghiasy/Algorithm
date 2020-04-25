package sortroutines;

public class BubbleSort {
    public int[] bubbleSort(int[] arr){
        for(int i=0;i<arr.length;++i){
            //with every loop latest not sorted element will be sort
            //since i last elements are sorted => no need to check them
            //for(int j=0;j<arr.length-1;++j){
            for(int j=0;j<arr.length-i-1;++j){
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
