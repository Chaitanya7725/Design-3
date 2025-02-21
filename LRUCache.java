import java.util.HashMap;
import java.util.Map;

// TC: O(1): The get and put methods are working on constant time.
// SC: O(n): where n is the capacity mentioned for the problem.
class Node {
    int key, val;
    Node prev, next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

public class LRUCache {
    int capacity;
    static Node head, tail;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(4);
        print(head);
        lruCache.put(1, 1);
        print(head);
        lruCache.put(2, 2);
        print(head);
        System.out.println(lruCache.get(1));
        print(head);
        lruCache.put(3, 3);
        print(head);
        System.out.println(lruCache.get(2));
        print(head);
        lruCache.put(4, 4);
        print(head);
        System.out.println(lruCache.get(1));
        print(head);
        System.out.println(lruCache.get(3));
        print(head);
        System.out.println(lruCache.get(4));
        print(head);
    }

    // utility method for printing node keys and values
    private static void print(Node head) {
        while (head != null) {
            System.out.print("[" + head.key + "," + head.val + "]⇋");
            head = head.next;
        }
        System.out.println();
    }

    // Method for retrieving value based on key
    public int get(int key) {
        if (map.containsKey(key)) {
            Node current = map.get(key);
            remove(current);
            add(current);
            return current.val;
        }
        return -1;
    }

    // Method for updating, adding the key,value at the head.
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node current = map.get(key);
            remove(current);
            add(current);
            current.val = value;
            return;
        }
        if (map.size() == capacity) {
            Node current = tail.prev;
            remove(current);
            map.remove(current.key);
        }
        Node current = new Node(key, value);
        add(current);
        map.put(key, current);
        return;
    }

    // Utility method for retrieving the value of key in the front of queue.
    private void add(Node node) {
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    // Utility method for removing any particular node.
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

}