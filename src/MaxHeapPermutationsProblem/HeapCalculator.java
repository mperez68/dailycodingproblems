package MaxHeapPermutationsProblem;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * 8/21/24
 * This problem was asked by Microsoft.
 * Write a program to determine how many distinct ways there are to create a max heap from a list of N given integers.
 * For example, if N = 3, and our integers are [1, 2, 3], there are two ways, shown below.
 *   3      3
 *  / \    / \
 * 1   2  2   1
 */

public class HeapCalculator {
    Dictionary<String, Integer> permutations = new Hashtable<>();

    public HeapCalculator(){  }

    public String solution(int[] n) {
        permutations = new Hashtable<>();
        if (n.length == 0) {
            return "0";
        }
        for (int start = 0; start < n.length; start++){
            Node root = new Node(n[(start) % n.length]);
            for (int i = 1; i < n.length; i++) {
                root.insert(n[(start + i) % (n.length)]);
            }
            addKey(root);
        }
        return String.valueOf(permutations.size());
    }

    private void addKey(Node root) {
        String key = root.toString();
        if (permutations.get(key) == null) {
            permutations.put(key, 1);
        } else {
            permutations.put(key, permutations.get(key) + 1);
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    /**
     * Base constructor
     * @param val nodes given value. Cannot be changed.
     */
    public Node(int val) { value = val; }

    /**
     * turns node and all children into a max heap.
     */
    public void heapify() {
        // if leaf node or only has one leaf node following, return 1; only one orientation exists.
        if (left == null) {
            return;
        }
        // if either child value is greater than self value, swap
        if (left.value > value) {
            swapLeft();
        }
        // break if no right comparison
        if (right == null) {
            return;
        }
        if (right.value > value) {
            swapRight();
        }
        // If child values don't outweigh self value, try swaps
        if (left.value + right.value <= value) {
            if (value + right.value > left.value) {
                swapLeft();
            } else if (left.value + value > right.value) {
                swapRight();
            }
        }
    left.heapify();
    right.heapify();
    }

    /**
     * insert value as child node or self if not possible while maintaining as a heap.
     * @param in number of distinct ways the heap tree can be made.
     */
    public void insert(int in) {
        // if either child is empty, insert in null node.
        if (left == null) {
            left = new Node(in);
        } else if (right == null) {
            right = new Node(in);
        } else if (right.insertDepth() < left.insertDepth()) {
            // else if right depth is lower, insert right.
            right.insert(in);
        } else {
            // else, insert left
            left.insert(in);
        }
        heapify();
    }

    private void swapLeft() {
        int temp = value;
        value = left.value;
        left.value = temp;
    }

    private void swapRight() {
        int temp = value;
        value = right.value;
        right.value = temp;
    }

    public int insertDepth() {
        if (right == null) {
            return 0;
        } else if (left != null) {
            return left.insertDepth() + 1;
        }
        return 0;
    }

    public int depth() {
        if (left != null) {
            return left.insertDepth() + 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (left != null) {
            s.append(left).append("\t");
        }
        s.append("\t".repeat(Math.max(0, depth())));
        s.append(value).append("\n");
        if (right != null) {
            s.append(right).append("\t");
        }

        return s.toString();
    }
}
