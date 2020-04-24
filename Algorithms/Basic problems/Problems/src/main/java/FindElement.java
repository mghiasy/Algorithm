public class FindElement {
    //Suppose the array arr is already known to be in sorted order, Determine whether z is in arr
    public static boolean find(int[] arr, int z) {
        //implement
        for(int i=0;i<arr.length;i++){
            System.out.println(i);
            if(arr[i]>z){
                return false;
            }
            else{
                if(arr[i]==z){
                    return true;
                }
            }
        }
        return false;
    }
}
