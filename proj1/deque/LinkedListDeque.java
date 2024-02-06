package deque;

public class LinkedListDeque<T> implements Deque<T> {
    // The first item (if exists) is at sentinel.next
    private Node sentinel;
    private int size;

    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node p = sentinel;

        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }

        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;

        Node first = sentinel.next;
        first.next.prev = sentinel;
        sentinel.next = first.next;
        size--;
        return first.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;

        Node last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        size--;
        return last.item;
    }

    @Override
    public T get(int index) {
        if (isEmpty() || index < 0) return null;

        Node p = sentinel.next;

        int i = 0;
        while (i != index) {
            p = p.next;
            i++;
        }

        return p.item;
    }

    public T getRecursive(int index) {
        if (isEmpty() || index < 0) return null;

        Node p = sentinel.next;
        int i = 0;

        return getRecursive(index, i, p);
    }

    private T getRecursive(int index, int i, Node p) {
        if (index == i) return p.item;

        return getRecursive(index, i, p.next);
    }
}