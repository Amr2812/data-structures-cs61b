package deque;

import afu.org.checkerframework.checker.oigj.qual.I;
import org.checkerframework.checker.units.qual.A;
import ucb.checkstyle.Main;

public class ArrayDeque<T> implements Deque<T> {
    private static final int MIN_SIZE = 8;
    private final int RESIZE_FACTOR = 2;
    private int front, back;
    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[MIN_SIZE];
        front = back = -1;
        size = 0;
    }

    private boolean isFull() {
        return size == items.length;
    }

    private boolean needsDownsizing() {
        return items.length >= 16 && (((float) size / items.length) < .25);
    }

    @Override
    public void addFirst(T item) {
        if (isFull()) {
            resize(size * RESIZE_FACTOR);
        }

        if (isEmpty()) {
            front = back = 0;
        } else if (front == 0) {
            front = items.length - 1;
        } else if (front > back) {
            front--;
        } else {
            front = (front - 1) % items.length;
        }

        items[front] = item;
        size++;
    }

    // Add to the back of the deque
    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(size * RESIZE_FACTOR);
        } else if (isEmpty()) {
            front++;
        }

        back = (back + 1) % items.length;
        items[back] = item;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int tempFront = front;
        while (tempFront != back) {
            System.out.print(items[tempFront] + " ");
            tempFront = (tempFront + 1) % items.length;
        }
        if (!isEmpty()) System.out.print(items[tempFront]);
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;

        T item = items[front];
        items[front] = null;

        if (front == back) { /* contains 1 element */
            front = back = -1;
        } else {
            front = (front + 1) % items.length;
        }

        size--;
        if (needsDownsizing()) resize(items.length / RESIZE_FACTOR);
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;

        T item = items[back];
        items[back] = null;

        if (front == back) { /* contains 1 element */
            front = back = -1;
        } else if (back == 0) {
            back = items.length - 1;
        } else {
            back = (back - 1) % items.length;
        }

        size--;
        if (needsDownsizing()) resize(items.length / RESIZE_FACTOR);
        return item;
    }

    @Override
    public T get(int index) {
        if (index > items.length) return null;
        return items[index];
    }

    private void resize(int newSize) {
        T[] newList = (T[]) new Object[newSize];

        if (back > front) {
            System.arraycopy(items, front, newList, 0, size);
        } else {
            System.arraycopy(items, 0, newList, 0, size);
        }

        items = newList;
        front = 0;
        back = size - 1;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast(0);
        arrayDeque.addLast(1);
        arrayDeque.addLast(2);
        arrayDeque.addFirst(-1);
        arrayDeque.addFirst(-1);
        arrayDeque.addFirst(-1);
        arrayDeque.addFirst(-1);
        arrayDeque.addFirst(-1);
        arrayDeque.addFirst(-1);
        arrayDeque.addFirst(-1);
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
//        arrayDeque.removeLast();
        arrayDeque.printDeque();
    }
}
