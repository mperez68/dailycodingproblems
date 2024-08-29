package CelebrityProblem;

import main.AbstractProblem;

import java.util.List;
import java.util.stream.IntStream;

/**
 * <p>At a party, there is a single person who everyone knows, but who does not know anyone in return (the "celebrity"). To help figure out who this is, you have access to an O(1) method called knows(a, b), which returns True if person a knows person b, else False.</p>
 *
 * <p>Given a list of N people and the above operation, find a way to identify the celebrity in O(N) time.</p>
 */

public class CelebrityProblem extends AbstractProblem {
    public static void main(String[] args) {
        CelebrityProblem p = new CelebrityProblem();
        System.out.println(p.solve());
    }

    int[][] n = {{0,0,1,0}, {1,0,1,0}, {0,0,0,0}, {1,0,1,0}};

    @Override
    protected String naiveSolution() {
        return String.valueOf(findCeleb(n));
    }

    @Override
    protected String GivenSolution() {
        return String.valueOf(GFG.celebrity(n, n.length));
    }

    // Function to check if person 'a' knows person 'b'
    static boolean knows(int a, int b, int[][] matrix)
    {
        return matrix[a][b] == 1;
    }

    private int findCeleb(int[][] n) {
        List<Integer> celebs = new java.util.LinkedList<>(IntStream.rangeClosed(0, n.length - 1)
                .boxed().toList());

        while (celebs.size() > 1) {
            if (knows(celebs.get(0), celebs.get(1), n)) {
                celebs.removeFirst();
            } else {
                celebs.remove(1);
            }
        }

        return celebs.get(0);
    }
}

// java program to find celebrity
// in the given Matrix using two-pointer approach

class GFG {

    public static int celebrity(int[][] matrix, int n) {
        // This function returns the celebrity
        // index 0-based (if any)

        int i = 0, j = n - 1;
        while (i < j) {
            if (matrix[j][i] == 1) // j knows i
                j--;
            else // j doesn't know i so i can't be celebrity
                i++;
        }
        // i points to our celebrity candidate
        int candidate = i;

        // Now, all that is left is to check that whether
        // the candidate is actually a celebrity i.e: he is
        // known by everyone but he knows no one
        for (i = 0; i < n; i++) {
            if (i != candidate) {
                if (matrix[i][candidate] == 0
                        || matrix[candidate][i] == 1)
                    return -1;
            }
        }
        // if we reach here this means that the candidate
        // is really a celebrity
        return candidate;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] matrix = {
                { 0, 0, 1, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 1, 0 }
        };

        int celebIdx = celebrity(matrix, n);

        if (celebIdx == -1)
            System.out.println("No Celebrity");
        else {
            System.out.println("Celebrity ID " + celebIdx);
        }
    }
}