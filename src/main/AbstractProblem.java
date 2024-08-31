package main;

import java.util.HashMap;
import java.util.Objects;

public abstract class AbstractProblem {

    protected static boolean printSol;

    public String solve() {
        long start = System.currentTimeMillis();
        String nSol = naiveSolution();
        long naive = System.currentTimeMillis();
        String gSol = GivenSolution();
        long given = System.currentTimeMillis();

        if (printSol) {
            String n = nSol;
            String g = gSol;
            if (n == null) n = "null";
            if (g == null) g = "null";
            System.out.println(n + " " + g);
        }

        HashMap<String, Long> output = new HashMap<>();
        long correct = 0;
        if (Objects.equals(nSol, gSol)) {
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
