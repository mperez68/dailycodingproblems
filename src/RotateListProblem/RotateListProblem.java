package RotateListProblem;

import main.AbstractProblem;

import java.util.Arrays;

/**
 * <p>Write a function that rotates a list by k elements. For example, [1, 2, 3, 4, 5, 6] rotated by two becomes [3, 4, 5, 6, 1, 2]. Try solving this without creating a copy of the list. How many swap or move operations do you need?</p>
 */

public class RotateListProblem extends AbstractProblem {
    public static void main(String[] args) {
        RotateListProblem p = new RotateListProblem();
        System.out.println(p.solve());
    }

    int[] list = {1, 2, 3, 4, 5, 6};
    @Override
    protected String naiveSolution() {
        return Arrays.toString(rotate(list, 2));
    }

    @Override
    protected String GivenSolution() {
        return "[3, 4, 5, 6, 1, 2]";
    }

    int[] rotate(int[] l, int rot) {
        int[] temp = Arrays.copyOf(l, rot);
        int ptr = 0;
        while (ptr < l.length - rot) {
            l[ptr] = l[ptr + rot];
            ptr++;
        }

        int i = 0;
        while (ptr < l.length) {
            l[ptr] = temp[i];
            ptr++;
            i++;
        }

        return l;
    }
}
