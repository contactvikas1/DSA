import java.lang.Math;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 * This program prints the first negative number in every contiguous
 * window (subarray) of fixed size K using the Sliding Window pattern.
 */
public class Main {

    public static void main(String[] args) {
        // Input array containing both positive and negative numbers
        int[] array = {12, -1, -7, 8, -15, 30, 16, 28};

        // Window size K
        int k = 3;

        // Call method to compute first negative in each window of size K
        int[] result = firstNegativeNumEachArray(array, k);

        // Print the result array values
        System.out.println("First negative number in each window of size " + k + ":");

        // Loop through the result and print each value
        for (int i = 0; i < result.length; i++) {
            System.out.print(" " + result[i]);
        }
    }

    /**
     * Finds the first negative number in every window of size K.
     * Time Complexity: O(N)
     * Space Complexity: O(K) for storing negative indexes in deque
     *
     * @param array input integer array
     * @param k     fixed window size
     * @return      array of size (N - K + 1) containing first negative of each window
     */
    static int[] firstNegativeNumEachArray(int[] array, int k) {
        int length = array.length;

        // Result array stores answer for each valid window of size K
        int[] result = new int[length - k + 1];

        // Two pointers defining the current window
        int left = 0, right = 0;

        // Deque stores the INDEXES of negative numbers present in the window
        // We use indexes to check if they fall inside or outside the window
        Deque<Integer> deq = new ArrayDeque<>();

        // Slide the window until the right pointer reaches the end
        while (right < length) {

            // 1️⃣ If the current number is negative, store its index at the END
            // because we want to track negatives in the order they appear
            if (array[right] < 0) {
                deq.addLast(right);
            }

            // 2️⃣ When window size becomes exactly K
            if (right - left + 1 == k) {

                // 3️⃣ Remove indexes of negative numbers that are no longer in the window
                // If deque front index < left, it's outside the window → remove it
                while (!deq.isEmpty() && deq.peekFirst() < left) {
                    deq.removeFirst();
                }

                // 4️⃣ Store answer for this window:
                // If deque is empty → no negative → store 0
                // Else deque front holds index of first negative → use it
                result[left] = deq.isEmpty() ? 0 : array[deq.peekFirst()];

                // 5️⃣ Slide the window forward by moving left
                left++;
            }

            // 6️⃣ Expand the window by moving right
            right++;
        }

        return result;
    }
}

