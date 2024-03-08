package deque;

import java.util.Iterator;

public interface Deque<T> {
    public Iterator<T> iterator();

    public boolean equals(Object o);

    public void addFirst(T item);

    public void addLast(T item);

    public int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    public void printDeque();

    public T removeFirst();

    public T removeLast();

    public T get(int index);
}