package trn;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import junit.framework.TestCase;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.TreeSet;

/**
 * @author aillusions
 */
public class MainTest extends TestCase {

    public void testDormantBloomFilter() throws IOException {
        DormantAddressProvider prov = new DormantAddressProvider();

        DormantBloomFilter bloomFilter = new DormantBloomFilter(prov.getDormantAddresses());

        assertTrue(bloomFilter.has("1P1iThxBH542Gmk1kZNXyji4E4iwpvSbrt"));
        assertFalse(bloomFilter.has("12dUggmXPYsPVHaHr1DoW5J6bb6gvh4yZq123)"));
    }

    public void testDormantSetFilter() throws IOException {
        DormantAddressProvider prov = new DormantAddressProvider();

        DormantSetFilter setFilter = new DormantSetFilter(prov.getDormantAddresses());

        assertTrue(setFilter.has("1P1iThxBH542Gmk1kZNXyji4E4iwpvSbrt"));
        assertFalse(setFilter.has("12dUggmXPYsPVHaHr1DoW5J6bb6gvh4yZq123)"));
    }

    public void testBloomFilterBase58Check() throws UnsupportedEncodingException {

        BloomFilter filter = BloomFilter.create(
                Funnels.byteArrayFunnel(),
                50_000L,
                0.01);

        filter.put("1Du2jAQsBQnkkVZkN4oqC46tS78k7WMkVq".getBytes("UTF-8"));

        assertTrue(filter.mightContain("1Du2jAQsBQnkkVZkN4oqC46tS78k7WMkVq".getBytes("UTF-8")));
        assertFalse(filter.mightContain("1Du2jAQsBQnkkVZkN4oqC46tS78k7WMkVq_123".getBytes("UTF-8")));
        assertFalse(filter.mightContain("_123".getBytes("UTF-8")));
    }

    public void testBloomFilter() {

        BloomFilter filter = BloomFilter.create(
                Funnels.byteArrayFunnel(),
                500,
                0.01);

        filter.put(BigInteger.valueOf(1).toByteArray());
        filter.put(BigInteger.valueOf(2).toByteArray());
        filter.put(BigInteger.valueOf(3).toByteArray());


        assertTrue(filter.mightContain(BigInteger.valueOf(1).toByteArray()));
        assertTrue(filter.mightContain(BigInteger.valueOf(2).toByteArray()));
        assertTrue(filter.mightContain(BigInteger.valueOf(3).toByteArray()));

        assertFalse(filter.mightContain(BigInteger.valueOf(100).toByteArray()));
    }

    public void testRadix() {
        System.out.println(Integer.toString(1000, 36));
    }

    public void testFromPrivateKey() {
        TreeSet<BigInteger> allInSet = new TreeSet<>();

        BigInteger mimKeyVal = new BigInteger("1", 10);
        BigInteger maxKeyVal = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364140", 16);

        BigInteger min = new BigInteger("75092275779465463846566764852568185336449448855279946465983440130440106909602");
        BigInteger max = new BigInteger("75092275779465463846566764852568185336449448855279946465983440130440106909602");

        for (int i = 2; i < 1000; i++) {
            ECKey key = new ECKey();
            //ECKey key = ECKey.fromPrivate(BigInteger.valueOf(i));
            // System.out.println(PrivKeyFinderWorker.getBtcAddress(key));

            BigInteger thisVal = key.getPrivKey();
            if (thisVal.compareTo(min) <= -1) {
                min = thisVal;
            } else if (thisVal.compareTo(max) >= 1) {
                max = thisVal;
            }

            allInSet.add(thisVal);
            //System.out.println(key.getPrivKey().toString());
        }

        BigInteger minDiff = new BigInteger("75092275779465463846566764852568185336449448855279946465983440130440106909602");
        BigInteger maxDiff = new BigInteger("75092275779465463846566764852568185336449448855279946465983440130440106909602");
        BigInteger prev = null;
        for (BigInteger thisVal : allInSet) {

            System.out.println("thisVal: " + thisVal.toString());

            if (prev == null) {
                prev = thisVal;
            } else {

                BigInteger diff = thisVal.subtract(prev);

                if (diff.compareTo(minDiff) <= -1) {
                    minDiff = diff;
                } else if (diff.compareTo(minDiff) >= 1) {
                    maxDiff = diff;
                }

                prev = thisVal;
            }
        }

        System.out.println("---------");
        System.out.println("mimKeyVal " + mimKeyVal.toString());
        System.out.println("maxKeyVal " + maxKeyVal.toString());

        System.out.println("min:      " + min.toString());
        System.out.println("max:      " + max.toString());

        System.out.println("minDiff:  " + minDiff.toString());
        System.out.println("maxDiff:  " + maxDiff.toString());


    }

    public void testDiff() {
        BigInteger min = new BigInteger("2509888038738773955893120560916054384187690728830615648690769961108614");
        BigInteger max = new BigInteger("115792087050815268180028792699858054683182472815411589831177736896551597915877");
        BigInteger delta = max.subtract(min);

        System.out.println("delta: " + delta.toString());

        BigInteger snippet = delta.divide(BigInteger.valueOf(100_000_000));
        System.out.println("snippet: " + snippet.toString());
    }

    public void testWip() {
        NetworkParameters params = new MainNetParams();

        DumpedPrivateKey key = DumpedPrivateKey.fromBase58(params, "KzzuoFPzrPhD55icpAi7idW7z7tH8xSYo3xqTcZm3fHk3AzVxpoP");
        String publicAddress = key.getKey().toAddress(params).toString();

        System.out.println("publicAddress: " + publicAddress.toString());
    }
}
