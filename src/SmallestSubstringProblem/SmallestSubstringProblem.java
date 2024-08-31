package SmallestSubstringProblem;

import main.AbstractProblem;

import java.util.*;

/**
 * <p>Given a string and a set of characters, return the shortest substring containing all the characters in the set.</p>
 *
 * <p>For example, given the string "figehaeci" and the set of characters {a, e, i}, you should return "aeci".</p>
 *
 * <p>If there is no substring containing all the characters in the set, return null.</p>
 */

public class SmallestSubstringProblem extends AbstractProblem {
    public static void main(String[] args) {
        SmallestSubstringProblem p = new SmallestSubstringProblem();
        System.out.println(p.solve());
    }

    String s = "figehaeci";
    String c = "aei";

    int ptr = 0;

    @Override
    protected String naiveSolution() {
        return findSubstring();
    }

    @Override
    protected String GivenSolution() {
        return GFG.smallestWindow(s, c);
    }

    private String findSubstring() {
        if (c.isEmpty()) {
            return "";
        } else if (s.isEmpty()) {
            return null;
        }

        String result = null;
        LinkedList<Character> chars = new LinkedList<>();
        for (char ch : c.toCharArray()) {
            chars.add(ch);
        }

        HashMap<Character, Integer> seen = new HashMap<>();
        ptr = 0;
        for (int i = 0; i < s.length(); i++) {
            if (result == null) {
                // fill 'seen' until it is the same size as c, set result to substring between ptr and i
                // always reset seen value to most recently seen character
                char temp = s.charAt(i);
                if (chars.contains(temp)) {
                    seen.put(temp, i);
                    if (seen.size() == 1) {
                        ptr = i;    // set beginning of substring
                    } else if (seen.size() == chars.size()) {
                        result = s.substring(ptr, i + 1);
                    }
                }
            } else {
                // update seen values
                if (seen.containsKey(s.charAt(i))) {
                    seen.put(s.charAt(i), i);
                }
            }
        }

        if (!seen.isEmpty()) {
            int lowIndex = Collections.min(seen.values());
            int highIndex = Collections.max(seen.values());
            result = s.substring(lowIndex, highIndex + 1);
        }

        return result;
    }
}

class GFG {
    // Function to find the smallest window in string S that
    // contains all characters of string P
    public static String smallestWindow(String s, String p)
    {
        int len1 = s.length();
        int len2 = p.length();

        // Check if string's length is less than P's length
        if (len1 < len2) {
            return "-1";
        }

        // Initialize hash maps for P and string S
        HashMap<Character, Integer> hashP = new HashMap<>();
        HashMap<Character, Integer> hashS = new HashMap<>();

        // Store occurrence of characters of P
        for (int i = 0; i < len2; i++) {
            hashP.put(p.charAt(i),
                    hashP.getOrDefault(p.charAt(i), 0)
                            + 1);
        }

        int start = 0, start_idx = -1,
                min_len = Integer.MAX_VALUE;

        // Count of matched characters
        int count = 0;

        // Start traversing the string S
        for (int j = 0; j < len1; j++) {
            // Count occurrence of characters of string S
            char currentChar = s.charAt(j);
            hashS.put(currentChar,
                    hashS.getOrDefault(currentChar, 0)
                            + 1);

            // If S's char matches with P's char, increment
            // count
            if (hashP.containsKey(currentChar)
                    && hashS.get(currentChar)
                    <= hashP.get(currentChar)) {
                count++;
            }

            // If all characters are matched
            if (count == len2) {
                // Try to minimize the window
                while (
                        hashS.getOrDefault(s.charAt(start), 0)
                                > hashP.getOrDefault(
                                s.charAt(start), 0)
                                || !hashP.containsKey(
                                s.charAt(start))) {
                    if (hashS.get(s.charAt(start))
                            > hashP.getOrDefault(
                            s.charAt(start), 0)) {
                        hashS.put(s.charAt(start),
                                hashS.get(s.charAt(start))
                                        - 1);
                    }
                    start++;
                }

                // Update window size
                int len = j - start + 1;
                if (min_len > len) {
                    min_len = len;
                    // Update starting index
                    start_idx = start;
                }
            }
        }

        // If no window found
        if (start_idx == -1)
            return "-1";

        // Return the substring starting from start_idx and
        // length min_len
        return s.substring(start_idx, start_idx + min_len);
    }

    public static void main(String[] args)
    {
        String s = "timetopractice";
        String p = "toc";

        String result = smallestWindow(s, p);
        System.out.println(result);
    }
}
