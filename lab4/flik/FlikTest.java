package flik;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FlikTest {
    @Test
    public void RandomTest() {
        int N = 5000;
        Flik flik = new Flik();
        for (int i = 0; i < 5000; i++) {
            int randVal = StdRandom.uniform(0, N);
            int equal = randVal;
            assertTrue("randVal should be equal to equal", flik.isSameNumber(randVal, equal));
        }
    }
}
