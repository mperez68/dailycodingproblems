package LongestUniqueSubArrayProblem;

import main.AbstractProblem;

import java.util.LinkedList;
import java.util.List;
import java.util.*;

/**
 * <p>This problem was asked by Google.</p>
 *
 * <p>Given an array of elements, return the length of the longest subarray where all its elements are distinct.</p>
 *
 * <p>For example, given the array [5, 1, 3, 5, 2, 3, 4, 1], return 5 as the longest subarray of distinct elements is [5, 2, 3, 4, 1].</p>
 */

public class LongestUniqueSubArrayProblem extends AbstractProblem {
    public static void main(String[] args) {
        LongestUniqueSubArrayProblem p = new LongestUniqueSubArrayProblem();
        initRand(p);
        System.out.println(p.solve());
    }

    private static void initRand(LongestUniqueSubArrayProblem p) {
        int size = 10000;
        p.n = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            p.n[i] = rand.nextInt(100);
        }
    }

    @Override
    protected String naiveSolution() {
        return String.valueOf(getLongestUniqueSubArray());
    }

    @Override
    protected String GivenSolution() {
        return String.valueOf(GFG.largest_subarray(n, n.length));
    }

    int[] n = {5, 1, 3, 5, 2, 3, 4, 1};

    /**
     * O(n^2), feels like it could be faster.
     * @return ongest subarray where all its elements are distinct.
     */
    public int getLongestUniqueSubArray() {
        int longest = 0;
        List<Integer> sub = new LinkedList<>();

        for (int i = 0; i < n.length; i++) {
            int temp = 0;
            for (int j = i; j < n.length; j++) {
                if (sub.contains(n[j])) {
                    break;
                }
                sub.add(n[i + temp]);
                temp++;
            }
            longest = Math.max(temp, longest);
            sub.clear();
        }

        return longest;
    }
}
class GFG{

    // Function to find largest
// subarray with no duplicates
    static int largest_subarray(int a[], int n)
    {
        // Stores index of array elements
        HashMap<Integer,
                Integer> index = new HashMap<Integer,
                Integer>();
        int ans = 0;
        for(int i = 0, j = 0; i < n; i++)
        {

            // Update j based on previous
            // occurrence of a[i]
            j = Math.max(index.containsKey(a[i]) ?
                    index.get(a[i]) : 0, j);

            // Update ans to store maximum
            // length of subarray
            ans = Math.max(ans, i - j + 1);

            // Store the index of current
            // occurrence of a[i]
            index.put(a[i], i + 1);
        }

        // Return final ans
        return ans;
    }

    // Driver Code
    public static void main(String[] args)
    {
        int arr[] = { 1, 2, 3, 4, 5, 1, 2, 3 };
        int n = arr.length;
        System.out.print(largest_subarray(arr, n));
    }
}

// This code is contributed by Rajput-Ji
