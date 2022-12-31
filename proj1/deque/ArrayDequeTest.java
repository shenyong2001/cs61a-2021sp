package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {

    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        java.util.ArrayDeque<Integer> A = new java.util.ArrayDeque<>();

        int N = 500000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 5);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                arrayDeque.addLast(randVal);
                A.addLast(randVal);
            } else if (operationNumber == 1) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                arrayDeque.addFirst(randVal);
                A.addFirst(randVal);
            } else if (operationNumber == 2) {
                // size
                int sizeL = arrayDeque.size();
                int sizeB = A.size();
                assertEquals(sizeL, sizeB);
            } else if (operationNumber == 3 && arrayDeque.size() > 0) {
                // removeFirst
                int firstL = arrayDeque.removeFirst();
                int firstB = A.removeFirst();
                assertEquals(firstL, firstB);
            } else if (operationNumber == 4 && arrayDeque.size() > 0) {
                // removeLast
                int lastL = arrayDeque.removeLast();
                int lastB = A.removeLast();
                assertEquals(lastL, lastB);
            }
        }
    }
}
