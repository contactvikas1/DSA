import java.lang.Math;
import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        String input = "abcdeacaef";
        System.out.println("Max Length of Unique Substring " + maxUniqueSubString(input));
    }

    /**
     * Returns the length of the longest contiguous substring that contains
     * all unique (non-repeating) characters.
     *
     * Uses the Sliding Window pattern with 2 pointers (left, right) and a HashMap
     * to track which characters are currently in the window.
     */
    static int maxUniqueSubString(String input) {

        // 1) Handle edge cases early (interview best practice)
        // If string is null or empty, answer is 0 because no substring exists.
        if (input == null || input.length() == 0) {
            return 0;
        }

        // 2) Map stores characters that are inside current window.
        // We store index only to support interview questions, but this solution
        // uses the map like a Set (only presence check matters for correctness here).
        Map<Character, Integer> map = new HashMap<>();

        int left = 0;        // start of the sliding window
        int maxLength = 0;   // best answer found so far

        // 3) Expand window by moving `right` from 0 → end of string
        for (int right = 0; right < input.length(); right++) {
            char c = input.charAt(right);

            // 4) If current character already exists in map, it means a duplicate
            // has appeared in the window. So we must shrink from the left until
            // that duplicate character is removed to make the window valid again.
            while (map.containsKey(c)) {
                map.remove(input.charAt(left)); // remove leftmost char from window
                left++;                        // move left pointer forward
            }

            // 5) Add current character into the window
            map.put(c, right);

            // 6) Update best window length
            // Window size is (right - left + 1)
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
