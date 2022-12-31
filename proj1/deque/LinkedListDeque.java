package deque;

import java.util.Iterator;

public class LinkedListDeque<T> {

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
    private Node sentinel;

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        this.size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        if (isEmpty()) {
            sentinel.prev = sentinel.next;
        }
        size = size + 1;
    }

    public void addLast(T item) {
        Node dummy = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = dummy;
        sentinel.prev = dummy;
        size = size + 1;
    }

    public boolean isEmpty() {
        return size == 0;
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

    public T removeLast() {
        if (!isEmpty()) {
            T ret = sentinel.prev.item;
            Node last = sentinel.prev;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size = size - 1;
            return ret;
        }
        return null;
    }

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

    public T getRecursive(int index) {
        Node cur = sentinel.next;
        return getRecursiveHelper(index, cur);
    }

    private T getRecursiveHelper(int index, Node sentinel) {
        if (index >= size || index < 0) {
            return null;
        }
        if (index == 0) {
            return sentinel.item;
        }
        return getRecursiveHelper(index - 1, sentinel.next);
    }

    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        if (((LinkedListDeque<?>) o).size() != size) {
            return false;
        }

        LinkedListDeque<?>.Node curObj = ((LinkedListDeque<?>) o).sentinel;
        Node curThis = sentinel;
        do {
            curObj = curObj.next;
            curThis = curThis.next;
            if (curObj.item != curThis.item)
                return false;
        } while (curThis.next != sentinel);

        return true;
    }

    public Iterator<T> iterator() {
        return new LLDequeIterator();
    }

    private class LLDequeIterator implements Iterator<T> {
        private int wizPos;

        public LLDequeIterator() {
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

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>();
//        LinkedListDeque<Integer> linkedListDeque2 = new LinkedListDeque<>();
//        linkedListDeque.addFirst(1);
//        linkedListDeque.addFirst(2);
//        linkedListDeque.addLast(3);
//        linkedListDeque.addLast(4);
//        linkedListDeque2.addFirst(1);
//        linkedListDeque2.addFirst(2);
//        linkedListDeque2.addLast(3);
//        linkedListDeque2.addLast(4);
//        System.out.println(linkedListDeque.equals(linkedListDeque2));
//        System.out.println(linkedListDeque.getRecursive(0));
//        System.out.println(linkedListDeque.getRecursive(1));
//        System.out.println(linkedListDeque.getRecursive(2));
//        System.out.println(linkedListDeque.getRecursive(3));
//        System.out.println(linkedListDeque.getRecursive(4));
//        System.out.println(linkedListDeque.getRecursive(-1));
//        linkedListDeque.removeFirst();
//        linkedListDeque.printDeque();
//        linkedListDeque.removeLast();
//        linkedListDeque.printDeque();
//        linkedListDeque.removeFirst();
//        linkedListDeque.printDeque();
//        linkedListDeque.removeLast();
//        linkedListDeque.printDeque();
//    }
}
