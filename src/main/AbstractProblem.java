package main;

import java.util.HashMap;

public abstract class AbstractProblem {

    protected static boolean printSol;

    public String solve() {
        long start = System.currentTimeMillis();
        String nSol = naiveSolution();
        long naive = System.currentTimeMillis();
        String gSol = GivenSolution();
        long given = System.currentTimeMillis();

        if (printSol) {
            System.out.println(nSol + " " + gSol);
        }

        HashMap<String, Long> output = new HashMap<>();
        long correct = 0;
        if (nSol.equals(gSol)) {
            correct = 1;
        }
        output.put("Correct?", correct);
        output.put("Given Time", given - naive);
        output.put("Naive Time", naive - start);
        return output.toString();
    }

    protected abstract String naiveSolution();

    protected abstract String GivenSolution();

}
