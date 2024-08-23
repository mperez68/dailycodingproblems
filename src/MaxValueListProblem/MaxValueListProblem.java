package MaxValueListProblem;

import main.AbstractProblem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class MaxValueListProblem extends AbstractProblem {
    public static void main(String[] args) {
        MaxValueListProblem p = new MaxValueListProblem();
        initRandomInput(p);
        System.out.println(p.Solve());
    }

    public static void initRandomInput(MaxValueListProblem p) {
        int size = 1000;
        p.n = new String[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            p.n[i] = String.valueOf(rand.nextInt(10000));
        }
    }

    String[] n = {"10", "7", "76", "415"};

    @Override
    protected String naiveSolution() {
        maxValueList l = new maxValueList(n);
        return l.toString();
    }

    @Override
    protected String GivenSolution() {
        return LargestNumber.largestNumber(n);
    }
}

class maxValueList {
    private final LinkedList<String> n = new LinkedList<>();        // list of values

    public maxValueList(String[] in) {
        insAll(in);
    }

    public void insAll(String[] in) {
        for (String i : in) {
            ins(i);
        }
    }

    public void ins(String in) {
        n.push(in);
    }

    private void sort() {
        n.sort(new ValueComparator().reversed());
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        sort();
        for (String i : n) {
            s.append(i);
        }

        return s.toString();
    }
}

class ValueComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int o1First = Integer.parseInt(o1 + o2);
        int o2First = Integer.parseInt(o2 + o1);

        return Integer.compare(o1First, o2First);
    }
}

class LargestNumber {
    public static String largestNumber(String[] arr)
    {
        // Custom comparator to compare concatenated strings
        Comparator<String> myCompare
                = (X, Y) -> (X + Y).compareTo(Y + X);

        // Sort the array using the custom comparator
        Arrays.sort(arr, myCompare.reversed());

        // Handle the case where all numbers are zero
        if (arr[0].equals("0")) {
            return "0";
        }

        // Concatenate the sorted array
        StringBuilder result = new StringBuilder();
        for (String num : arr) {
            result.append(num);
        }

        return result.toString();
    }

    public static void main(String[] args)
    {
        String[] arr1 = { "3", "30", "34", "5", "9" };
        System.out.println(
                largestNumber(arr1)); // Output: "9534330"

        String[] arr2 = { "54", "546", "548", "60" };
        System.out.println(
                largestNumber(arr2)); // Output: "6054854654"
    }
}
