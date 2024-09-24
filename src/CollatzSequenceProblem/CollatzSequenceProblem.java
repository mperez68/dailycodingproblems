package CollatzSequenceProblem;

import main.AbstractProblem;

/**<p>A Collatz sequence in mathematics can be defined as follows. Starting with any positive integer:</p>

 <p>if n is even, the next number in the sequence is n / 2</p>
 <p>if n is odd, the next number in the sequence is 3n + 1</p>
 <p>It is conjectured that every such sequence eventually reaches the number 1. Test this conjecture.</p>

 <p>Bonus: What input n <= 1000000 gives the longest sequence?</p>
 *
 */

public class CollatzSequenceProblem extends AbstractProblem {
    public static void main(String[] args) {
        CollatzSequenceProblem p = new CollatzSequenceProblem();
        System.out.println(p.solve());
    }

    int n = 1000000;

    @Override
    protected String naiveSolution() {
        return collatz(n);
    }

    @Override
    protected String GivenSolution() {
        return GFG.printCollatz(n);
    }

    String collatz(int N) {
        StringBuilder result = new StringBuilder();

        if (N == 1) {
            return String.valueOf(N);
        }

        if (N % 2 == 0) {
            result.append(N).append(" ").append(collatz(N / 2));
        } else {
            result.append(N).append(" ").append(collatz((3 * N) + 1));
        }

        return result.toString();
    }
}

class GFG
{
    static String printCollatz(int n)
    {
        // modified to fit answer
        StringBuilder ans = new StringBuilder();
        // We simply follow steps
        // while we do not reach 1
        while (n != 1)
        {
            ans.append(n).append(" ");

            // If n is odd
            if ((n & 1) == 1)
                n = 3 * n + 1;

                // If even
            else
                n = n / 2;
        }

        // Print 1 at the end
        ans.append(n);
        return ans.toString();
    }

    // Driver code
    public static void main (String[] args)
    {
        System.out.println(printCollatz(6));
    }
}

// This code is contributed
// by akt_mit
