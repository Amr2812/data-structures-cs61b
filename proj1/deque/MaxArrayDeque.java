package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        if (isEmpty()) return null;

        T maxItem = get(0);
        for (int i = 0; i < this.size(); i++) {
            T current = get(i);
            if (comparator.compare(current, maxItem) > 0) {
                maxItem = current;
            }
        }

        return maxItem;
    }

    public T max(Comparator<T> comp) {
        if (isEmpty()) return null;

        T maxItem = get(0);
        for (int i = 0; i < this.size(); i++) {
            T current = get(i);
            if (comp.compare(current, maxItem) > 0) {
                maxItem = current;
            }
        }

        return maxItem;
    }
}
