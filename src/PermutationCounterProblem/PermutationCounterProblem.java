package PermutationCounterProblem;

import main.AbstractProblem;

/**
 * <p>Given integers M and N, write a program that counts how many positive integer pairs (a, b) satisfy the following conditions:</p>
 *
 * <p>a + b = M</p>
 * <p>a XOR b = N</p>
 */

public class PermutationCounterProblem extends AbstractProblem {
    public static void main(String[] args) {
        PermutationCounterProblem p = new PermutationCounterProblem();
        printSol = true;
        System.out.println(p.solve());
    }

    int m = 20, n = 5;

    @Override
    protected String naiveSolution() {
        return String.valueOf(countPermutations(m, n));
    }

    @Override
    protected String GivenSolution() {
        given G = new given();
        return String.valueOf(G.getCount(m, n));
    }

    int countPermutations(int M, int N) {
        if (M < 1) {
            return 0;
        }
        int result = 0;

        for (int i = 1; i < M; i++) {
            int b = M - i;
            if ( (i ^ b) == N ) {
                result++;
            }
        }

        return result;
    }
}

class given {
    public int getCount(int M, int N) {
        int count = 0;
        for (int i = 1; i < M; i++) {
            if ((i ^ M - i) == N) {
                count++;
            }
        }
        return count;
    }
}