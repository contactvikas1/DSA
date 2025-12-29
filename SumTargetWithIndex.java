import java.lang.Math;
import java.util.Map;
import java.util.HashMap;

/**
 * This program finds two indexes in an array whose values add up to a target number.
 * It uses a HashMap to store numbers and their indexes for fast lookup.
 */
public class Main {
    public static void main(String[] args) {
        // Input array of integers
        int[] array = {2, 9, 7, 15};

        // Target sum we want to find using two numbers from the array
        int target = 9;

        // Call the Two Sum function
        int[] result = sumTwo(array, target);

        // If result is found, print the indexes and confirmation
        if (result != null) {
            System.out.println("Indexes found: " + result[0] + " and " + result[1] 
                + " → Their sum gives " + target);
        } else {
            // If no valid pair is found
            System.out.println("No pair found that adds up to target " + target);
        }
    }

    /**
     * Finds two array indexes whose values add up to target.
     * Time Complexity: O(N)
     * Space Complexity: O(N) due to HashMap storage
     *
     * @param array  input array of integers
     * @param target desired sum
     * @return       array containing 2 indexes if found, otherwise null
     */
    static int[] sumTwo(int[] array, int target) {
        // HashMap stores: Key = array value, Value = index of that value
        Map<Integer, Integer> map = new HashMap<>();

        // Loop through the array once
        for (int i = 0; i < array.length; i++) {
            // Compute the value needed to reach target
            int complement = target - array[i];

            // If complement exists in map, we found our pair
            if (map.containsKey(complement)) {
                // Return stored index of complement + current index i
                return new int[]{map.get(complement), i};
            }

            // Otherwise, store the current number and its index for future checks
            map.put(array[i], i);
        }

        // No valid pair found
        return null;
    }
}

