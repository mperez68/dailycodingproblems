package main;

import java.util.HashMap;
import java.util.Objects;

public abstract class AbstractProblem {
    public String Solve() {
        long start = System.currentTimeMillis();
        String nSol = naiveSolution();
        long naive = System.currentTimeMillis();
        String gSol = GivenSolution();
        long given = System.currentTimeMillis();

        HashMap<String, Long> output = new HashMap<>();
        long correct = 1;
        if (nSol.equals(gSol)) {
            correct = 0;
        }
        output.put("Correct?", correct);
        output.put("Given Time", given - naive);
        output.put("Naive Time", naive - start);
        return output.toString();
    }

    protected abstract String naiveSolution();

    protected abstract String GivenSolution();

}
