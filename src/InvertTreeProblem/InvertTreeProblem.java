package InvertTreeProblem;

import main.AbstractProblem;

/**
 * <p>Invert a binary tree.</p>
 */

public class InvertTreeProblem extends AbstractProblem {
    public static void main(String[] args) {
        InvertTreeProblem p = new InvertTreeProblem();
        for (int i : p.nums){
            p.root.insert(i);
        }
        System.out.println(p.solve());
    }

    Node root = new Node(0);
    int[] nums = { 1, 2, 3, 4, 5 };

    @Override
    protected String naiveSolution() {
        root.print();
        root.invert();
        System.out.println();
        root.print();
        return "";
    }

    @Override
    protected String GivenSolution() {
        return "";
    }
}

class Node {
    int key, weight;
    Node left, right;
    public Node(int item) {
        key = item;
        left = right = null;
    }

    void print() {
        if (left != null) {
            left.print();
        }
        System.out.print(key + " ");
        if (right != null) {
            right.print();
        }
    }

    public void invert() {
        Node temp = left;
        left = right;
        right = temp;
        if (left != null) {
            left.invert();
        }
        if (right != null) {
            right.invert();
        }
    }

    public void insert(int item) {
        if (left == null) {
            left = new Node(item);
            weight++;
            return;
        } else if (right == null) {
            right = new Node(item);
            weight++;
            return;
        } else if (left.weight > right.weight) {
            right.insert(item);
        } else {
            left.insert(item);
        }
        weight = left.weight + right.weight;
    }
}