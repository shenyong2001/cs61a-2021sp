package deque;

public interface Deque<T> {
    void addFirst(T item);

    void addLast(T item);

    /** return whether Deque is empty. */
    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);

    boolean equals(Object obj);

}
