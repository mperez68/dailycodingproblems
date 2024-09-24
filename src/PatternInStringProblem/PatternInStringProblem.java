package PatternInStringProblem;

import main.AbstractProblem;

import java.util.LinkedList;

/**<p>Given a string and a pattern, find the starting indices of all occurrences of the pattern in the string. For example, given the string "abracadabra" and the pattern "abr", you should return [0, 7].</p>
 *
 */

public class PatternInStringProblem extends AbstractProblem {
    public static void main(String[] args) {
        PatternInStringProblem p = new PatternInStringProblem();
        printSol = true;
        System.out.println(p.solve());
    }

    String sentence = "abracadabra";
    String pattern = "abr";

    @Override
    protected String naiveSolution() {
        return patternIndexes(sentence, pattern).toString();
    }

    @Override
    protected String GivenSolution() {
        return "";
    }

    LinkedList<Integer> patternIndexes(String sent, String pat) {
        LinkedList<Integer> result = new LinkedList<>();

        int i = 0;
        while (sent.substring(i).contains(pat)) {
            String sentTrim = sent.substring(i, i + pat.length());
            if (sentTrim.equals(pat)) {
                result.add(i);
            }

            i++;
        }

        return result;
    }
}
