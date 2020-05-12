import java.util.*;

/**
 * n = 1 -> none
 * n = 2 -> none
 * n = 3 -> [2, 0, 0]
 * N = 4 -> {2, 0, 2, 0}, {1, 2, 1, 0}
 * N = 5 -> {2, 1, 2, 0, 0}
 * N = 6 -> None
 * N = 7 -> {3, 2, 1, 1, 0, 0, 0}
 * N = 8 -> {4, 2, 1, 0, 1, 0, 0, 0}
 * N = 9 -> {5, 2, 1, 0, 0, 1, 0, 0, 0}
 * N = 10 -> {6, 2, 1, 0, 0, 0, 1, 0, 0, 0}
 * N = 11 -> {7, 2, 1, 0, 0, 0, 0, 1, 0, 0, 0}
 * N = 12 -> {8, 2, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0}
 */
public class SelfAwareArrays {
    static Set<int[]> self_ware_arrays = new HashSet<>();

    /*
    Time complexity
    O(n^2).
     */
    public static void makeSelfArray(int size){
        int arr[] = new int [size];
        if(size < 4)
            System.out.println("this method works for numbers greater than 3");
        else {
            findSummands(size);
        }
    }
    static void findSummands(int n)
    {
        int[] p = new int[n]; // An array to store a partition
        int k = 0;  // Index of last element in a partition
        p[k] = n;  // Initialize first partition as number itself

        // This loop first prints current partition then generates next
        // partition. The loop stops when the current partition has all 1s
        while (true)
        {
            // print current partition
            if(p.length>1) {
                int[] a = new int[n];
                for (int i = 0; i < n; i++) {
                    a[i] = p[i];
                }
                permutationsSummands(n, a);
            }

            // Generate next partition

            // Find the rightmost non-one value in p[]. Also, update the
            // rem_val so that we know how much value can be accommodated
            int rem_val = 0;
            while (k >= 0 && p[k] == 1)
            {
                rem_val += p[k];
                k--;
            }

            // if k < 0, all the values are 1 so there are no more partitions
            if (k < 0)  return;

            // Decrease the p[k] found above and adjust the rem_val
            p[k]--;
            rem_val++;


            // If rem_val is more, then the sorted order is violated.  Divide
            // rem_val in different values of size p[k] and copy these values at
            // different positions after p[k]
            while (rem_val > p[k])
            {
                p[k+1] = p[k];
                rem_val = rem_val - p[k];
                k++;
            }

            // Copy rem_val to next position and increment position
            p[k+1] = rem_val;
            k++;
        }
    }

    /*
    Time complexity
    O(n^2 * n!).
     */
    private static void permutationsSummands(int n, int[] elements) {
        if(n == 1) {
            List l = new ArrayList();
            if(!self_ware_arrays.contains(elements) && validateArray(elements)){
                self_ware_arrays.add(elements);
                for (int a: elements) {
                    System.out.print(a + " ");
                }
                System.out.print('\n');
            }
        } else {
            for(int i = 0; i < n-1; i++) {
                permutationsSummands(n - 1, elements);
                if(n % 2 == 0) {
                    swap(elements, i, n-1);
                } else {
                    swap(elements, 0, n-1);
                }
            }
            permutationsSummands(n - 1, elements);
        }
    }

    /*
    Time complexity
    O(1).
     */
    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    /*
    Time complexity
    O(n).
     */
    private static boolean validateArray(int[] input) {
        boolean ok = false;
        for(int i = 0; i < input.length; i++) {
            if(!countNumbers(i, input[i], input))
                return false;
        }
        return true;
    }

    /*
    Time complexity
    O(n).
     */
    private static boolean countNumbers(int index, int indexCount, int[] input) {
        boolean ok = false;
        int count = 0;
        for (int a: input) {
            if(a==index)
                count++;
        }
        return count == indexCount;
    }

}
