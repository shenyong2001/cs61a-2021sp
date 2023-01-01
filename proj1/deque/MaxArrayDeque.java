package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }

        Iterator<T> itr = iterator();
        T maxItem = itr.next();
        while (itr.hasNext()) {
            T cur = itr.next();
            if (c.compare(cur, maxItem) > 0) {
                maxItem = cur;
            }
        }
        return maxItem;
    }

}
