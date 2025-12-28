import java.lang.Math;

/**
 * This program finds the maximum sum of any subarray (contiguous window)
 * of a fixed size K using the Sliding Window technique.
 */
public class Main {
    public static void main(String[] args) {
        // Input array
        int[] array = {8, 1, 5, 1, 3, 2};

        // Window size
        int k = 3;

        // Call the function to compute max sum of subarray of size K
        int maxArraySize = maxSizeSumArray(array, k);

        // Print the result
        System.out.println("Max sum of size 3 = " + maxArraySize);
    }

    /**
     * Returns the maximum sum of any subarray of fixed size K.
     * Uses O(N) time by updating the window sum instead of recomputing.
     */
    static int maxSizeSumArray(int[] array, int k) {
        int length = array.length;

        int windowSum = 0; // stores sum of current window
        int maxSum = 0;    // stores maximum sum found so far

        // 1️⃣ Build first window of size K
        for (int i = 0; i < k; i++) {
            windowSum += array[i];
        }

        // Initialize maxSum with first window sum
        maxSum = windowSum;

        // 2️⃣ Slide the window one step at a time
        for (int i = k; i < length; i++) {
            // Add next element, remove first element of previous window
            windowSum = windowSum + array[i] - array[i - k];

            // Update maxSum if current windowSum is greater
            maxSum = Math.max(maxSum, windowSum);
        }

        // 3️⃣ Return the best (maximum) window sum
        return maxSum;
    }
}
