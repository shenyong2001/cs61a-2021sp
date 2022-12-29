package List;

public class SLList {

    public static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    /** The first item (if it exists) is at sentinel.next. */
    private IntNode sentinel;
    private int size;

    /** Creates an empty list. */
    public SLList() {
        sentinel = new IntNode(0, null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(0, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    /** Add x to the front of the list. */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size++;
    }

    /** Get the first item of th list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Add x to the last of the list. */
    public void addLast(int x) {
        IntNode current = sentinel;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new IntNode(x, null);
        size++;
    }

    public int size() {
        return size;
    }

    public String toString() {
        IntNode current = sentinel.next;
        String result = "";

        while (current.next != null) {
            result += (current.item + "-");
            current = current.next;
        }
        result += current.item;
        return result;
    }


    public static void main(String[] args) {
        SLList test = new SLList(1);
        test.addFirst(10);
        test.addFirst(15);
        test.addLast(5);
        test.addLast(20);
        System.out.println(test.size());
        System.out.println(test.toString());

        SLList empty = new SLList();
        empty.addLast(10);
        empty.addFirst(20);
        System.out.println(empty);
        System.out.println(empty.size());
    }

}
