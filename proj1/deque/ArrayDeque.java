package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(T item) {
        if (items[nextFirst] != null) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = indexBefore(nextFirst);
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (items[nextLast] != null) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = indexAfter(nextLast);
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Iterator<T> itr = iterator();
        String toPrint = "" + itr.next();
        while (itr.hasNext()) {
            toPrint = toPrint + " " + itr.next();
        }
        System.out.println(toPrint);
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        if ((size < items.length / 4) && (size > 4)) {
            resize(items.length / 4);
        }

        int index = indexAfter(nextFirst);
        T first = items[index];
        items[index] = null;
        size -= 1;
        nextFirst = indexAfter(nextFirst);
        return first;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        if ((size < items.length / 4) && (size > 4)) {
            resize(items.length / 4);
        }

        int index = indexBefore(nextLast);
        T last = items[index];
        items[index] = null;
        size -= 1;
        nextLast = indexBefore(nextLast);
        return last;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= items.length) {
            return null;
        }
        index = (nextFirst + 1 + index) % items.length;
        return items[index];
    }

    private int indexBefore(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private int indexAfter(int index) {
        return (index + 1 + items.length) % items.length;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int i = 0;
        int index = indexAfter(nextFirst);
        while (index != indexBefore(nextLast)) {
            a[i] = items[index];
            index = indexAfter(index);
            i++;
        }
        a[i] = items[index];
        nextFirst = capacity - 1;
        nextLast = size;
        items = a;
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        ArrayDequeIterator() {
            wizPos = indexAfter(nextFirst);
        }

        @Override
        public boolean hasNext() {
            return wizPos <= indexBefore(nextLast);
        }

        @Override
        public T next() {
            T returnItem = items[wizPos];
            wizPos = indexAfter(wizPos);
            return returnItem;
        }
    }

    @Override
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
            if (deque.get(i).equals(get(i))) {
                return false;
            }
        }
        return true;
    }
}
