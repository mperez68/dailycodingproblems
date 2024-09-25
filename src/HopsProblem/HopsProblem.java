package HopsProblem;

import main.AbstractProblem;

import java.util.Arrays;

/**<p>Given an integer list where each number represents the number of hops you can make, determine whether you can reach to the last index starting at index 0.</p>

 <p>For example, [2, 0, 1, 0] returns True while [1, 1, 0, 1] returns False.</p>
 *
 */

public class HopsProblem extends AbstractProblem {
    public static void main(String[] args) {
        HopsProblem p = new HopsProblem();
        printSol = true;
        System.out.println(p.solve());
    }

    int[][] lists = {
            { 2, 0, 1, 0 },
            { 1, 1, 0, 1 }
    };

    @Override
    protected String naiveSolution() {
        return Arrays.toString(calcHops(lists));
    }

    @Override
    protected String GivenSolution() {
        return "";
    }

    boolean[] calcHops(int[][] ls) {
        boolean[] result = new boolean[ls.length];

        for (int i = 0; i < ls.length; i++) {
            int[] l = ls[i];

            int ptr = 0;
            do {
                ptr += l[ptr];
            } while ( (ptr < l.length) && (l[ptr] != 0) );
            result[i] = ptr >= (l.length - 1);
        }

        return result;
    }
}
