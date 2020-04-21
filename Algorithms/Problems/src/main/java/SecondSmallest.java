public class SecondSmallest {
    //returns the second smallest element of the input array
    public static int secondSmallest(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Input array too small");
        }
        //implement
        int smallest = arr[0];
        int secondSmallest = arr[1];
        if (smallest > secondSmallest) {
            int temp = smallest;
            smallest = secondSmallest;
            secondSmallest = temp;
        }
        if (arr.length > 2) {
            for (int i = 2; i < arr.length; i++) {
                if (arr[i] < secondSmallest) {
                    if (arr[i] < smallest) {
                        secondSmallest = smallest;
                        smallest = arr[i];
                    }
                    secondSmallest = arr[i];
                }
            }
        }

        return secondSmallest;
    }
}
