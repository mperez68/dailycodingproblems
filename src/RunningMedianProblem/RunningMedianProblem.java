package RunningMedianProblem;

import main.AbstractProblem;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**<p>Compute the running median of a sequence of numbers. That is, given a stream of numbers, print out the median of the list so far on each new element.</p>

 <p>Recall that the median of an even-numbered list is the average of the two middle numbers.</p>

 <p>For example, given the sequence [2, 1, 5, 7, 2, 0, 5], your algorithm should print out:</p>

 <l>
 <li>2</li>
 <li>1.5</li>
 <li>2</li>
 <li>3.5</li>
 <li>2</li>
 <li>2</li>
 <li>2</li></l>
 *
 */

public class RunningMedianProblem extends AbstractProblem {
    public static void main(String[] args) {
        RunningMedianProblem p = new RunningMedianProblem();
        System.out.println(p.solve());
    }

    int[] nums = {2, 1, 5, 7, 2, 0, 5};
    float[] nums2 = {2, 1, 5, 7, 2, 0, 5};

    @Override
    protected String naiveSolution() {
        return median(nums);
    }

    @Override
    protected String GivenSolution() {
        return GFG.printMedian(nums2, nums.length);
    }

    String median(int[] in) {
        StringBuilder result = new StringBuilder();


        if (in.length != 0) {
            LinkedList<Integer> tempIn = new LinkedList<>();
            for (int i: in) {
                tempIn.add(i);
            }
            double med;

            Collections.sort(tempIn);

            if (tempIn.size() % 2 == 0) {
                // even
                med = (double) (tempIn.get(tempIn.size() / 2) + tempIn.get((tempIn.size() / 2) - 1)) / 2;
            } else {
                // odd
                med = tempIn.get(tempIn.size() / 2);
            }

            result.append(median(Arrays.copyOfRange(in, 0, in.length - 1))).append(med).append(" ");
        }

        return result.toString();
    }
}

// Java code to implement the approach

class GFG {

    // Function to find position to insert current element
    // of stream using binary search
    static int binarySearch(float arr[], float item,
                            int low, int high)
    {
        if (low >= high) {
            return (item > arr[low]) ? (low + 1) : low;
        }

        int mid = (low + high) / 2;

        if (item == arr[mid])
            return mid + 1;

        if (item > arr[mid])
            return binarySearch(arr, item, mid + 1, high);

        return binarySearch(arr, item, low, mid - 1);
    }

    // Function to print median of stream of integers
    static String printMedian(float arr[], int n)
    {
        StringBuilder result = new StringBuilder();
        int i, j, pos;
        float num;
        int count = 1;

        result.append(arr[0]).append(" ");

        for (i = 1; i < n; i++) {
            float median;
            j = i - 1;
            num = arr[i];

            // find position to insert current element in
            // sorted part of array
            pos = binarySearch(arr, num, 0, j);

            // move elements to right to create space to
            // insert the current element
            while (j >= pos) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = num;

            // increment count of sorted elements in array
            count++;

            // if odd number of integers are read from
            // stream then middle element in sorted order is
            // median else average of middle elements is
            // median
            if (count % 2 != 0) {
                median = arr[count / 2];
            }
            else {
                median = (arr[(count / 2) - 1]
                        + arr[count / 2])
                        / 2;
            }

            result.append(median).append(" ");
        }
        return result.toString();
    }

    // Driver code
    public static void main(String[] args)
    {

        float arr[]
                = { 5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4 };
        int n = arr.length;

        System.out.println(printMedian(arr, n));
    }
}

// This code is contributed by sanjoy_62.

// This code is modified by Susobhan Akhuli
