import java.lang.Math;
import java.util.Stack;

/**
 * This program checks if a string containing brackets is valid.
 * A valid string must:
 * 1. Open brackets must be closed by the same type
 * 2. Brackets must close in the correct order
 * 3. Every close must have a matching open
 */
public class Main {
    public static void main(String[] args) {
        // Test cases to verify correctness
        System.out.println(isValid("{[()]}")); // expected: true
        System.out.println(isValid("{[(])}")); // expected: false
        System.out.println(isValid(""));       // expected: true (empty is valid)
    }

    /**
     * Validates bracket pairing using a Stack (LIFO).
     * We push opening brackets, and when we see a closing one,
     * we pop and check if it matches.
     *
     * @param s input string
     * @return true if valid, false otherwise
     */
    static boolean isValid(String s) {
        // Stack to store opening brackets encountered
        Stack<Character> stack = new Stack<>();

        // Convert string to char array and iterate through each character
        for (char c : s.toCharArray()) {

            // 1️⃣ If it's an opening bracket, push to stack
            if (c == '{' || c == '[' || c == '(') {
                System.out.println("Pushed opening bracket → " + c);
                stack.push(c);
            } 
            // 2️⃣ Else it's a closing bracket
            else {
                // If stack is empty, there's no opening bracket to match → invalid
                if (stack.isEmpty()) {
                    System.out.println("Invalid! Closing bracket " + c + " found but stack was empty.");
                    return false;
                }

                // Pop the most recent opening bracket
                char top = stack.pop();
                System.out.println("Popped " + top + " to match with closing → " + c);

                // 3️⃣ Check if popped bracket matches the current closing bracket
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
                if (c == ')' && top != '(') return false;
            }
        }

        // 4️⃣ After processing all characters, stack must be empty for valid pairing
        // If not empty, some opening brackets were never closed → invalid
        boolean valid = stack.isEmpty();
        System.out.println("Final stack empty? → " + valid);
        return valid;
    }
}

