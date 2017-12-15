package trn.parser;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.io.*;
import java.nio.charset.Charset;

public class BlockParser {

    public static final String CVS_SPLIT_BY = ";";
    public static final String tx_in_csv = "g:\\csv_dump\\tx_in.csv.tmp";
    public static final String tx_out_csv = "g:\\csv_dump\\tx_out.csv.tmp";
    public static final String unspent_outs = "unspent_outs.txt";

    public static BloomFilter BLOOM_FILTER = BloomFilter.create(
            Funnels.stringFunnel(Charset.forName("UTF-8")),
            800_000_000,
            0.000_000_000_001);


    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        // @txid, @hashPrevOut, indexPrevOut, scriptSig, sequence
        readBloomFilter(tx_in_csv);

        // @txid, indexOut, value, @scriptPubKey, address
        unspentOuts(tx_out_csv);

        System.out.println(" Done in " + (System.currentTimeMillis() - start) + " ms. ");
    }

    public static void unspentOuts(String csvFile) throws IOException {

        PrintWriter writer = new PrintWriter(new FileWriter(unspent_outs, false));

        BufferedReader br;
        String line;

        br = new BufferedReader(new FileReader(csvFile));

        int maxLines = 770_294_424;
        int i = 0;

        while ((line = br.readLine()) != null) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] lineSplit = line.split(CVS_SPLIT_BY, -1);

            try {
                if (!TxOut.address(lineSplit).startsWith("1")) {
                    continue;
                }

                if (BLOOM_FILTER.mightContain(uniqueTransKey(TxOut.txid(lineSplit), TxOut.indexOut(lineSplit)))) {
                    continue;
                }

                writer.append(TxOut.address(lineSplit) + ";" + TxOut.value(lineSplit) + "\r\n");

                i++;
                if (i % 10_000_000 == 0) {
                    System.out.println("unspentOuts: " + i + " [" + (((double) i / maxLines) * 100) + "%]");
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println(line);
                e.printStackTrace();
            }
        }

        br.close();
        writer.close();
    }

    public static void readBloomFilter(String csvFile) throws IOException {

        BufferedReader br;
        String line;

        br = new BufferedReader(new FileReader(csvFile));

        int i = 0;

        int maxLines = 700_294_424;

        while ((line = br.readLine()) != null) {

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] lineSplit = line.split(CVS_SPLIT_BY);
            BLOOM_FILTER.put(uniqueTransKey(TxIn.hashPrevOut(lineSplit), TxIn.indexPrevOut(lineSplit)));

            i++;
            if (i % 10_000_000 == 0) {
                System.out.println("readBloomFilter: " + i + " [" + (((double) i / maxLines) * 100) + "%]");
            }
        }

        br.close();
    }

    private static String uniqueTransKey(String hashPrevOut, String indexPrevOut) {
        return hashPrevOut/*.substring(0, 30) */ + "" + indexPrevOut;
    }
}
