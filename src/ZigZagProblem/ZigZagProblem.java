package ZigZagProblem;

import main.AbstractProblem;

import java.util.LinkedList;

/**<p>Given a string and a number of lines k, print the string in zigzag form. In zigzag, characters are printed out diagonally from top left to bottom right until reaching the kth line, then back up to top right, and so on.</p>

 <p>For example, given the sentence "thisisazigzag" and k = 4, you should print:</p>

 <p>t     a     g</p>
 <p> h   s z   a</p>
 <p>  i i   i z</p>
 <p>   s     g</p>
 *
 */

public class ZigZagProblem extends AbstractProblem {
    public static void main(String[] args) {
        ZigZagProblem p = new ZigZagProblem();
        System.out.println(p.solve());
    }

    String string = "thisisazigzagthisisazigzagthisisazigzagthisisazigzag";
    int k = 5;

    @Override
    protected String naiveSolution() {
        ZigZag(string, k);
        return "";
    }

    @Override
    protected String GivenSolution() {
        return "";
    }

    void ZigZag(String in, int K) {
        LinkedList<String> lines = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            lines.push("");
        }

        int ptr = 0, dir = 1;
        for (char c : in.toCharArray()) {
            for (int i = 0; i < K; i++) {
                if (i == ptr) {
                    lines.set(i, lines.get(i).concat(String.valueOf(c)));
                } else {
                    lines.set(i, lines.get(i).concat(" "));
                }
            }
            if ( (ptr + dir < 0) || (ptr + dir >= K) ) {
                dir = -dir;
            }
            ptr += dir;
        }

        for (String line : lines) {
            System.out.println(line);
        }
    }
}