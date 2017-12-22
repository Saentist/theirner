package trn.parser;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import junit.framework.TestCase;

import java.io.*;
import java.nio.charset.Charset;

public class BlockParserTest extends TestCase {

    BloomFilter BLOOM_FILTER = BloomFilter.create(
            Funnels.stringFunnel(Charset.forName("UTF-8")),
            800,
            0.000_001);

    public void testBloom() {
        BLOOM_FILTER.put("50748b7a193a0b23f1e9494b51131d2f954cc6cf4792bacc69d207d16002080d");
        BLOOM_FILTER.put("04391286b3aefbb5df4cdb515ac7fce7942525fa602e1d7757e90a4fd41a1e20");
        BLOOM_FILTER.put("701ce76c033e0b03fa79503770a5874840373e30cd9c1eca472ec66617f3a3ee");

        assertTrue(BLOOM_FILTER.mightContain("701ce76c033e0b03fa79503770a5874840373e30cd9c1eca472ec66617f3a3ee"));

        assertFalse(BLOOM_FILTER.mightContain("_701ce76c033e0b03fa79503770a5874840373e30cd9c1eca472ec66617f3a3ee"));
    }

    public void testReaFile() throws IOException {

        long start = System.currentTimeMillis();

        int i = 0;
        BufferedReader br = new BufferedReader(new FileReader(BlockParser.tx_in_csv));
        String line;
        while ((line = br.readLine()) != null) {
            i++;

            //String[] lineSplit = line.split(BlockParser.CVS_SPLIT_BY, -1);
            //String uqTr = BlockParser.uniqueTransKey(TxIn.hashPrevOut(lineSplit), TxIn.indexPrevOut(lineSplit));
            //BlockParser.BLOOM_FILTER.put(uqTr);

            if (i % 1_000_000 == 0) {
                long totalSpentMs = System.currentTimeMillis() - start;
                float speedPerSec = i / totalSpentMs * 1000;
                System.out.println("Read lines: " + (i / 1_000_000) + " m lines. Speed: " + speedPerSec / 1_000_000 + " m lines/sec");
            }
        }
    }
}
