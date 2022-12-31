package deque;

public class ArrayDeque<T> implements Deque<T> {

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
        if (get(nextFirst) != null) {
            // TODO: resize
        }
        items[nextFirst] = item;
        nextFirst = indexBefore(nextFirst);
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (get(nextLast) != null) {
            // TODO: resize
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
        int index = indexAfter(nextFirst);
        T cur = get(index);
        String toPrint = "" + cur;
        index = indexAfter(index);
        cur = get(index);
        while (index != indexAfter(nextFirst)) {
            toPrint = toPrint + " " + cur;
            index = indexAfter(index);
            cur = get(index);
        }
        System.out.println(toPrint);
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
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
        return items[index];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

    private int indexBefore(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private int indexAfter(int index) {
        return (index + 1 + items.length) % items.length;
    }

    public static void main(String[] args) {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast("a");
        arrayDeque.addLast("b");
        arrayDeque.addFirst("c");
        arrayDeque.addLast("d");
        arrayDeque.addLast("e");
        arrayDeque.addFirst("f");
        arrayDeque.addLast("g");
        arrayDeque.addLast("h");
        arrayDeque.printDeque();
    }
}
