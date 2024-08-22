package MaxHeapPermutationsProblem;

public class MaxHeapPermutationsProblem extends AbstractProblem {

    @Override
    public void naiveSolution() {
        HeapCalculator h = new HeapCalculator();

        int[] n = {3, 4, 7, 10, 25, 99};

        for (int size : n) {
            int[] list = new int[size];
            for (int i = 0; i < size; i++) {
                list[i] = i + 1;
            }
            System.out.println("given " + size + ", => " + h.solution(list));
        }
    }

    @Override
    public void GivenSolution() {
    }
}
