package MaxHeapPermutationsProblem;

import main.AbstractProblem;

import java.util.Random;

public class MaxHeapPermutationsProblem extends AbstractProblem {
    public static void main(String[] args) {
        MaxHeapPermutationsProblem p = new MaxHeapPermutationsProblem();
        initRandomInput(p);
        System.out.println(p.solve());
    }

    private static void initRandomInput(MaxHeapPermutationsProblem p) {
        int size = 1000;
        p.n = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            p.n[i] = rand.nextInt(100);
        }
    }

    public int[] n = {3, 4, 7, 25};

    public String naiveSolution() {
        HeapCalculator h = new HeapCalculator();
        StringBuilder solutions = new StringBuilder();

        for (int size : n) {
            int[] list = new int[size];
            for (int i = 0; i < size; i++) {
                list[i] = i + 1;
            }
            solutions.append(h.solution(list));
        }
        return solutions.toString();
    }

    public String GivenSolution() {
        StringBuilder solutions = new StringBuilder();
        for (int size : n) {
            solutions.append(GFG.solve(size));
        }
        return solutions.toString();
    }
}
