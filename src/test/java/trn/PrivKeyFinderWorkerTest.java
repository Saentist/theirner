package trn;

import junit.framework.TestCase;

/**
 * @author aillusions
 */
public class PrivKeyFinderWorkerTest extends TestCase {

    private final static long ONE_BILLION = 1_000_000_000L;

    public void testLoopPerf() {
        long start = System.currentTimeMillis();

        long val = 0;
        for (long i = 0; i <= ONE_BILLION; i++) {
            val++;
        }

        System.out.println("testLoopPerf: " + (System.currentTimeMillis() - start) + " ms. (" + val + ")");
    }
}
