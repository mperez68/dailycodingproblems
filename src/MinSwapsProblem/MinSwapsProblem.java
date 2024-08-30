package MinSwapsProblem;

import main.AbstractProblem;

import java.util.HashMap;

/**<p>There are N couples sitting in a row of length 2 * N. They are currently ordered randomly, but would like to rearrange themselves so that each couple's partners can sit side by side.</p>
 *
 * <p>What is the minimum number of swaps necessary for this to happen?</p>
 */

public class MinSwapsProblem extends AbstractProblem {
    public static void main(String[] args) {

        MinSwapsProblem p = new MinSwapsProblem();
        System.out.println(p.solve());
    }

    int n = 4;
    int[] bench = {3, 0, 1, 0, 2, 1, 2, 3};
    int[] givenN = {0, 3, 0, 1, 0, 2, 1, 2, 3};
    int[] givenN2 = {0, 3, 0, 1, 0, 2, 1, 2, 3};

    @Override
    protected String naiveSolution() {
        return String.valueOf(sortPairs());
    }

    @Override
    protected String GivenSolution() {
        return String.valueOf(GFG.minSwaps(n, givenN, givenN2));
    }

    private int sortPairs() {
        int result = 0;

        HashMap<Integer, Integer> unpaired = new HashMap<>();   // value, index
        for (int i = 0; i < n; i++) {
            int ptr = 2 * i;
            if (bench[ptr] != bench[ptr + 1]) {                             // if not already paired, seek swaps
                if (unpaired.containsKey(bench[ptr])) {                     // if 0 is in past unpaired elements, swap 1
                    // swap ptr+1 to pair
                    swap(ptr + 1, unpaired.get(bench[ptr]));
                    // did the swap fix a second pair?
                    checkPassed(unpaired, unpaired.get(bench[ptr + 1]));
                    // Remove swapped element
                    unpaired.remove(bench[ptr]);
                    result++;
                } else {
                    if (unpaired.containsKey(bench[ptr + 1])) {             // if 1 is in past unpaired elements, swap 0
                        // swap ptr to pair
                        swap(ptr, unpaired.get(bench[ptr + 1]));
                        // did the swap fix a second pair?
                        checkPassed(unpaired, unpaired.get(bench[ptr]));
                        // remove swapped element
                        unpaired.remove(bench[ptr + 1]);
                        result++;
                    } else {                                                // if neither can be paired, add both to unpaired
                        unpaired.put(bench[ptr], ptr);
                        unpaired.put(bench[ptr + 1], ptr + 1);
                    }
                }
            }
        }
        return result;
    }

    private void checkPassed(HashMap<Integer, Integer> unpaired, Integer ptr1) {
        int ptr2;
        if (ptr1 % 2 == 0) { // even
            ptr2 = ptr1 + 1;
        } else {            // odd
            ptr2 = ptr1 - 1;
        }

        if (bench[ptr1] == bench[ptr2]) {
            unpaired.remove(bench[ptr2]);
        } else {
            unpaired.put(bench[ptr1], ptr1);
            unpaired.put(bench[ptr2], ptr2);
        }
    }

    private void swap(int i, int j) {
        int temp = bench[i];
        bench[i] = bench[j];
        bench[j] = temp;
    }
}

// Java program to find minimum number
// of swaps required so that
// all pairs become adjacent.

class GFG {

    // This function updates indexes
// of elements 'a' and 'b'
    static void updateindex(int index[], int a,
                            int ai, int b, int bi)
    {
        index[a] = ai;
        index[b] = bi;
    }

    // This function returns minimum number
// of swaps required to arrange
// all elements of arr[i..n] become arranged
    static int minSwapsUtil(int arr[], int pairs[],
                            int index[], int i, int n)
    {
        // If all pairs processed so
        // no swapping needed return 0
        if (i > n)
            return 0;

        // If current pair is valid so
        // DO NOT DISTURB this pair
        // and move ahead.
        if (pairs[arr[i]] == arr[i + 1])
            return minSwapsUtil(arr, pairs, index, i + 2, n);

        // If we reach here, then arr[i] and
        // arr[i+1] don't form a pair

        // Swap pair of arr[i] with arr[i+1]
        // and recursively compute minimum swap
        // required if this move is made.
        int one = arr[i + 1];
        int indextwo = i + 1;
        int indexone = index[pairs[arr[i]]];
        int two = arr[index[pairs[arr[i]]]];
        arr[i + 1] = arr[i + 1] ^ arr[indexone] ^
                (arr[indexone] = arr[i + 1]);
        updateindex(index, one, indexone, two, indextwo);
        int a = minSwapsUtil(arr, pairs, index, i + 2, n);

        // Backtrack to previous configuration.
        // Also restore the previous
        // indices, of one and two
        arr[i + 1] = arr[i + 1] ^ arr[indexone] ^
                (arr[indexone] = arr[i + 1]);
        updateindex(index, one, indextwo, two, indexone);
        one = arr[i];
        indexone = index[pairs[arr[i + 1]]];

        // Now swap arr[i] with pair of arr[i+1]
        // and recursively compute minimum swaps
        // required for the subproblem
        // after this move
        two = arr[index[pairs[arr[i + 1]]]];
        indextwo = i;
        arr[i] = arr[i] ^ arr[indexone] ^ (arr[indexone] = arr[i]);
        updateindex(index, one, indexone, two, indextwo);
        int b = minSwapsUtil(arr, pairs, index, i + 2, n);

        // Backtrack to previous configuration. Also restore
        // the previous indices, of one and two
        arr[i] = arr[i] ^ arr[indexone] ^ (arr[indexone] = arr[i]);
        updateindex(index, one, indextwo, two, indexone);

        // Return minimum of two cases
        return 1 + Math.min(a, b);
    }

    // Returns minimum swaps required
    static int minSwaps(int n, int pairs[], int arr[])
    {
        // To store indices of array elements
        int index[] = new int[2 * n + 1];

        // Store index of each element in array index
        for (int i = 1; i <= 2 * n; i++)
            index[arr[i]] = i;

        // Call the recursive function
        return minSwapsUtil(arr, pairs, index, 1, 2 * n);
    }

    // Driver code
    public static void main(String[] args) {

        // For simplicity, it is assumed that arr[0] is
        // not used. The elements from index 1 to n are
        // only valid elements
        int arr[] = {0, 3, 5, 6, 4, 1, 2};

        // if (a, b) is pair than we have assigned elements
        // in array such that pairs[a] = b and pairs[b] = a
        int pairs[] = {0, 3, 6, 1, 5, 4, 2};
        int m = pairs.length;

        // Number of pairs n is half of total elements
        int n = m / 2;

        // If there are n elements in array, then
        // there are n pairs
        System.out.print("Min swaps required is " +
                minSwaps(n, pairs, arr));
    }
}

// This code is contributed by Anant Agarwal.
