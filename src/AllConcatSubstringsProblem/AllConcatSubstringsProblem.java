package AllConcatSubstringsProblem;

import main.AbstractProblem;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * <p>This problem was asked by Dropbox.</p>
 *
 * <p>Given a string s and a list of words words, where each word is the same length, find all starting indices of substrings in s that is a concatenation of every word in words exactly once.</p>
 *
 * <p>For example, given s = "dogcatcatcodecatdog" and words = ["cat", "dog"], return [0, 13], since "dogcat" starts at index 0 and "catdog" starts at index 13.</p>
 *
 * <p>Given s = "barfoobazbitbyte" and words = ["dog", "cat"], return [] since there are no substrings composed of "dog" and "cat" in s.</p>
 *
 * <p>The order of the indices does not matter.</p>
 */

public class AllConcatSubstringsProblem extends AbstractProblem {
    public static void main(String[] args) {
        printSol = true;
        AllConcatSubstringsProblem p = new AllConcatSubstringsProblem();
        System.out.println(p.solve());
    }

    String s = "dogcatcatcodecatdog";
    String[] words = {"cat", "dog"};

    @Override
    protected String naiveSolution() {
        return getIndexesOfAllPermutations().toString();
    }

    @Override
    protected String GivenSolution() {
        return GFG.findSubstring(s, Arrays.stream(words).toList()).toString();
    }

    private LinkedList<Integer> getIndexesOfAllPermutations() {
        LinkedList<Integer> result = new LinkedList<>();

        int index;
        LinkedList<String> subWords;
        for (String word : words) {
            if (s.contains(word)) {
                subWords = new LinkedList<>(List.of(words));
                subWords.remove(word);
                String subS = s;
                while (subS.contains(word)) {
                    index = _getIndexAnyPermutation(subS.indexOf(word), word, subWords);
                    subS = subS.substring(subS.indexOf(word) + word.length());
                    if (index >= 0) {
                        result.push(index);
                    }
                }
            }
        }

        return result;
    }

    private int _getIndexAnyPermutation(int startIndex, String currentConcat, LinkedList<String> subList) {


        // Success State
        if (subList.isEmpty()) {
            return startIndex;
        }

        // If more words in sublist to check, keep diving
        for (String word : subList) {
            String newConcat = currentConcat + word;
            if (newConcat.length() + startIndex > s.length()) {
                return -1;
            }
            if (s.substring(startIndex, newConcat.length() + 1).contains(newConcat)) {
                subList.remove(word);
                return _getIndexAnyPermutation(startIndex, newConcat, subList);
            }
        }

        return -1;
    }
}

// Java program to calculate the starting indices
// of substrings inside S which contains all the
// words present in List L.

class GFG
{
    public static ArrayList<Integer>
    findSubstring(String A, final List<String> B)
    {

        // Number of a characters of a word in list L.
        int size_word = B.get(0).length();

        // Number of words present inside list L.
        int word_count = B.size();

        // Total characters present in list L.
        int size_l = size_word * word_count;

        // Resultant vector which stores indices.
        ArrayList<Integer> res = new ArrayList<Integer>();
        int n = A.length();

        // If the total number of characters in list L
        // is more than length of string S itself.
        if (size_l > n)
        {
            return res;
        }

        // Map stores the words present in list L
        // against it's occurrences inside list L
        HashMap<String, Integer> hashMap =
                new HashMap<String, Integer>();

        for (String word : B)
        {
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }


        for (int i = 0; i <= n - size_l; i++)
        {
            HashMap<String, Integer> tempMap =
                    (HashMap<String, Integer>) hashMap.clone();
            int j = i, count = word_count;

            // Traverse the substring
            while (j < i + size_l)
            {
                // Extract the word
                String word = A.substring(j, j + size_word);

                // If word not found or if frequency
                // of current word is more than required simply break.
                if (!hashMap.containsKey(word) || tempMap.get(word) == 0)
                {
                    break;
                }

                // Else decrement the count of word from hash_map
                else
                {
                    tempMap.put(word, tempMap.get(word) - 1);
                    count--;
                }
                j += size_word;
            }

            // Store the starting index of that
            // substring when all the words in
            // the list are in substring
            if (count == 0)
            {
                res.add(i);
            }

        }
        return res;
    }

    // Driver code
    public static void main(String[] args)
    {
        String S = "barfoothefoobarman";
        ArrayList<String> L =
                new ArrayList<>(Arrays.asList("foo", "bar"));
        ArrayList<Integer> indices = findSubstring(S, L);
        for (Integer i : indices)
        {
            System.out.println(i);
        }
    }
}

// This code is contributed by Manish Sakariya
