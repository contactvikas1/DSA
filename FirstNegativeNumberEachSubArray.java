import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) {

        // Input array containing both positive and negative numbers
        int[] array = {2, -1, -15, 4, 5, -7, 9, -3, 4};

        // Fixed sliding window size
        int k = 3;

        // Call function to compute first negative in every window of size K
        int[] result = firstNrgFixSizeArray(array, k);

        // Print results for each window
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    /**
     * Returns an array where each element is the first negative number
     * in every contiguous window of fixed size K.
     * Uses Sliding Window pattern + Deque to track valid negative indices.
     */
    static int[] firstNrgFixSizeArray(int[] array, int k) {

        // Edge case validation:
        // - array empty
        // - K is zero or negative
        // - K is greater than array size (invalid window)
        if (array.length == 0 || k <= 0 || k > array.length) {
            return new int[]{}; // return empty result
        }

        // Result size = total windows = N - K + 1
        int[] result = new int[array.length - k + 1];

        // 'left' marks the start index of the current window
        int left = 0;

        // Deque will store only indices of negative numbers that lie within the current window
        Deque<Integer> de = new ArrayDeque<>();

        // Expand window using 'right'
        for (int right = 0; right < array.length; right++) {

            // If current number is negative, store its index in deque
            if (array[right] < 0) {
                de.addLast(right);
            }

            // When window reaches size K, process it
            if (right - left + 1 == k) {

                // Remove indices from deque that are outside the window (less than 'left')
                while (!de.isEmpty() && de.peekFirst() < left) {
                    de.removeFirst();
                }

                // If deque is empty → no negative in window → store 0
                // Else → take the value at deque front → that is first negative in this window
                result[left] = de.isEmpty() ? 0 : array[de.peekFirst()];

                // Slide window forward
                left++;
            }
        }

        // Return computed results
        return result;
    }
}

