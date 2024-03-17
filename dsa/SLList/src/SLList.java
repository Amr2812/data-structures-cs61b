public class SLList<T> {
    // The first item (if exists) is at sentinel.next
    private Node sentinel;
    private int size;

    // static nested class if it doesn't use parent class => minor savings of memory
    private class Node {
        public T item;
        public Node next;

        public Node(T i, Node n) {
            item = i;
            next = n;
        }
    }

    public SLList() {
        sentinel = new Node(null, null);
        size = 0;
    }

    public SLList(T x) {
        sentinel = new Node(null, null);
        sentinel.next = new Node(x, null);
        size = 1;
    }

    public static void main(String[] args) {
        SLList<Integer> L = new SLList<>(10);
//        L.addLast(11);
//        L.addLast(12);
        System.out.println(L.getFirst());
    }

    // Adds x to the front of te list
    public void addFirst(T x) {
        sentinel.next = new Node(x, sentinel.next);
        size++;
    }

    public T getFirst() {
        return sentinel.next.item;
    }

    public void addLast(T x) {
        size++;

        Node p = sentinel;

        // Advance p to the end of the list
        while (p.next != null) {
            p = p.next;
        }

        p.next = new Node(x, null);
    }

    public int size() {
        return size;
    }
    /*
    private static int size(Node p) {
        if (p.next == null) {
            return 0;
        }

        return 1 + size(p.next);
    }

    public int size() {
        return size(first);
    }
     */
}
