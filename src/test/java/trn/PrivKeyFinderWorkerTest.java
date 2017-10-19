package trn;

import junit.framework.TestCase;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author aillusions
 */
public class PrivKeyFinderWorkerTest extends TestCase {

    private static final long ONE_BILLION = 1_000_000_000L;

    private static volatile long counterVol = 0;
    private static final AtomicLong counterAtom = new AtomicLong();

    public void testLoopPerf() {
        long start = System.currentTimeMillis();

        long val = 0;
        for (long i = 0; i <= ONE_BILLION; i++) {
            val++;
        }

        System.out.println("testLoopPerf: " + (System.currentTimeMillis() - start) + " ms. (" + val + ")");
    }

    public void testVolatile() {
        long start = System.currentTimeMillis();

        long val = 0;
        for (long i = 0; i <= ONE_BILLION; i++) {
            val++;
            counterVol++;
        }

        System.out.println("testVolatile: " + (System.currentTimeMillis() - start) + " ms. (" + val + ")");
    }

    public void testIncrementAndGet() {
        long start = System.currentTimeMillis();

        long val = 0;
        for (long i = 0; i <= ONE_BILLION; i++) {
            val++;
            counterAtom.incrementAndGet();
        }

        System.out.println("testIncrementAndGet: " + (System.currentTimeMillis() - start) + " ms. (" + val + ")");
    }
}
