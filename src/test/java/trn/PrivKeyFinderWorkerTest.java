package trn;

import junit.framework.TestCase;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author aillusions
 */
public class PrivKeyFinderWorkerTest extends TestCase {

    private static final long ONE_BILLION = 1_000_000_000L;
    private static final long ONE_MILLION = 1_000_000L;
    private static final long ONE_THOUSAND = 1_000L;

    private static volatile long counterVol = 0;
    private static final AtomicLong counterAtom = new AtomicLong();

    public void testLoopPerf() {
        long start = System.currentTimeMillis();

        long val = 0;
        for (long i = 0; i <= ONE_MILLION; i++) {
            val++;
        }

        System.out.println("testLoopPerf: " + (System.currentTimeMillis() - start) + " ms. (" + val + ")");
    }

    public void testVolatile() {
        long start = System.currentTimeMillis();

        long val = 0;
        for (long i = 0; i <= ONE_MILLION; i++) {
            val++;
            counterVol++;
        }

        System.out.println("testVolatile: " + (System.currentTimeMillis() - start) + " ms. (" + val + ")");
    }

    public void testIncrementAndGet() {
        long start = System.currentTimeMillis();

        long val = 0;
        for (long i = 0; i <= ONE_MILLION; i++) {
            val++;
            counterAtom.incrementAndGet();
        }

        System.out.println("testIncrementAndGet: " + (System.currentTimeMillis() - start) + " ms. (" + val + ")");
    }

    public void testContains() {

        DormantSetFilter bloomFilter = new DormantSetFilter(new DormantAddressProvider().getDormantAddresses());

        long start = System.currentTimeMillis();

        long val = 0;
        for (long i = 0; i <= ONE_MILLION; i++) {
            val++;
            bloomFilter.has("test");
        }

        System.out.println("testContains: " + (System.currentTimeMillis() - start) + " ms. (" + val + ")");
    }

    public void testContainsConcat() {

        DormantSetFilter bloomFilter = new DormantSetFilter(new DormantAddressProvider().getDormantAddresses());

        long start = System.currentTimeMillis();

        long val = 0;
        for (long i = 0; i <= ONE_MILLION; i++) {
            val++;
            bloomFilter.has("test" + i);
        }

        System.out.println("testContainsConcat: " + (System.currentTimeMillis() - start) + " ms. (" + val + ")");
    }

    public void testNewKey() {

        long start = System.currentTimeMillis();

        long val = 0;
        for (long i = 0; i <= ONE_THOUSAND; i++) {
            val++;
            PrivKeyFinderWorker.getNewECKey();
        }

        System.out.println("testNewKey: " + (System.currentTimeMillis() - start) + " ms. (" + val + ")");
    }
}
