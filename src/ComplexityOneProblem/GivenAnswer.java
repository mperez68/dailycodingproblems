package ComplexityOneProblem;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class AllOne {
    private final Node root = new Node(); // Sentinel node to mark beginning and end of doubly linked list
    private final Map<String, Node> nodes = new HashMap<>(); // Mapping from key to its corresponding Node

    public AllOne() {
        // Initialize the doubly linked list with root node pointing to itself
        root.next = root;
        root.prev = root;
    }

    public void inc(String key) {
        // Increase the count for the key
        if (!nodes.containsKey(key)) {
            // If key is new, insert it appropriately
            if (root.next == root || root.next.cnt > 1) {
                nodes.put(key, root.insert(new Node(key, 1)));
            } else {
                root.next.keys.add(key);
                nodes.put(key, root.next);
            }
        } else {
            // If key already exists, move it to the next Node or create a new Node
            Node current = nodes.get(key);
            Node next = current.next;
            if (next == root || next.cnt > current.cnt + 1) {
                nodes.put(key, current.insert(new Node(key, current.cnt + 1)));
            } else {
                next.keys.add(key);
                nodes.put(key, next);
            }
            // Remove key from current Node and check if it should be deleted
            current.keys.remove(key);
            if (current.keys.isEmpty()) {
                current.remove();
            }
        }
    }

    public void dec(String key) {
        // Decrease the count for the key
        Node current = nodes.get(key);
        if (current.cnt == 1) {
            // If count goes to 0, remove key from system
            nodes.remove(key);
        } else {
            // Move key to the previous Node or create a new Node
            Node prev = current.prev;
            if (prev == root || prev.cnt < current.cnt - 1) {
                nodes.put(key, prev.insert(new Node(key, current.cnt - 1)));
            } else {
                prev.keys.add(key);
                nodes.put(key, prev);
            }
        }

        // Remove key from current Node and remove Node if empty
        current.keys.remove(key);
        if (current.keys.isEmpty()) {
            current.remove();
        }
    }

    public String getMaxKey() {
        // Get and return a key with the maximum count
        if (root.prev == root) return ""; // Handle case with no keys
        return root.prev.keys.iterator().next();
    }

    public String getMinKey() {
        // Get and return a key with the minimum count
        if (root.next == root) return ""; // Handle case with no keys
        return root.next.keys.iterator().next();
    }
}

class Node {
    Node prev; // Previous node in doubly linked list
    Node next; // Next node in doubly linked list
    int cnt; // Count associated with node
    Set<String> keys = new HashSet<>(); // Set of keys with this count

    // Constructor for sentinel node
    public Node() {
        this("", 0);
    }

    // Constructor for actual nodes
    public Node(String key, int cnt) {
        this.cnt = cnt;
        keys.add(key);
    }

    // Method to insert this node before the provided node
    public Node insert(Node node) {
        node.prev = this;
        node.next = this.next;
        node.prev.next = node;
        node.next.prev = node;
        return node;
    }

    // Method to remove this node from the linked list
    public void remove() {
        this.prev.next = this.next;
        this.next.prev = this.prev;
    }
}

