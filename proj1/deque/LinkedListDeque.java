package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private final Node sentinel;

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        this.size = 0;
    }

    /** Adds item to the front of the list. */
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        if (isEmpty()) {
            sentinel.prev = sentinel.next;
        }
        size = size + 1;
    }

    /** Adds item to the end of the list. */
    public void addLast(T item) {
        Node dummy = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = dummy;
        sentinel.prev = dummy;
        size = size + 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node cur = sentinel.next;
        String toPrint = "";

        while (cur.next != sentinel) {
            toPrint += (cur.item + " ");
            cur = cur.next;
        }

        toPrint += cur.item;
        System.out.println(toPrint);
    }

    /** Remove item at the front of the list. */
    public T removeFirst() {
        if (!isEmpty()) {
            T ret = sentinel.next.item;
            Node first = sentinel.next;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            first = null;
            size = size - 1;
            return ret;
        }
        return null;
    }

    /** Remove item at the end of the list. */
    public T removeLast() {
        if (!isEmpty()) {
            T ret = sentinel.prev.item;
            Node last = sentinel.prev;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            last = null;
            size = size - 1;
            return ret;
        }
        return null;
    }

    /** Get the ith item of the list. */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node cur = sentinel.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.item;
    }

    /** Get the ith item of the list recursively. */
    public T getRecursive(int index) {
        Node cur = sentinel.next;
        return getRecursiveHelper(index, cur);
    }

    private T getRecursiveHelper(int index, Node cur) {
        if (index >= size || index < 0) {
            return null;
        }
        if (index == 0) {
            return cur.item;
        }
        return getRecursiveHelper(index - 1, cur.next);
    }

    /** Returns whether the parameter o is equal to the Deque. */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Deque)) {
            return false;
        }

        Deque<?> deque = (Deque<?>) obj;
        if (deque.size() != size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (deque.get(i) != get(i)) {
                return false;
            }
        }

        return true;
    }

    public Iterator<T> iterator() {
        return new LLDequeIterator();
    }

    private class LLDequeIterator implements Iterator<T> {
        private int wizPos;

        LLDequeIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }
}
