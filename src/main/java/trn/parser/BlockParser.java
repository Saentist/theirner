package trn.parser;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.io.*;
import java.nio.charset.Charset;

public class BlockParser {

    public static final String CVS_SPLIT_BY = ";";
    public static final String tx_in_csv = "g:\\csv_dump\\tx_in.csv.tmp";
    public static final String tx_out_csv = "g:\\csv_dump\\tx_out.csv.tmp";
    public static final String unspent_outs = "g:\\csv_dump\\unspent_outs.txt";
    public static final String unspent_uq = "g:\\csv_dump\\unspent_uq.txt";
    public static final String unspent_total = "g:\\csv_dump\\unspent_total.txt";

    public static BloomFilter BLOOM_FILTER = BloomFilter.create(
            Funnels.stringFunnel(Charset.forName("UTF-8")),
            800_000_000,
            0.000_000_000_001);

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        // @txid, @hashPrevOut, indexPrevOut, scriptSig, sequence
        readBloomFilter();

        // @txid, indexOut, value, @scriptPubKey, address
        unspentOuts();

        System.out.println(" Done in " + (System.currentTimeMillis() - start) + " ms. ");
    }

    // First step
    public static void readBloomFilter() throws IOException {

        readFileLines(tx_in_csv,

                new IFileLineReader() {

                    long start = System.currentTimeMillis();
                    int maxLines = 700_294_424;
                    int i = 0;

                    @Override
                    public void onLineRead(String line) {
                        i++;
                        if (i % 1_000_000 == 0) {
                            long totalSpentMs = System.currentTimeMillis() - start;
                            float speedPerSec = i / totalSpentMs * 1000;

                            System.out.println("readBloomFilter: " + (i / 1_000_000) + " m lines. Speed: " + speedPerSec / 1_000_000 + " m lines/sec " + " [" + Math.floor(((double) i / maxLines) * 100) + "%]");
                        }
                    }
                },
                new IFileLineReader() {

                    @Override
                    public void onLineRead(String line) {
                        String[] lineSplit = line.split(CVS_SPLIT_BY, -1);
                        BLOOM_FILTER.put(uniqueTransKey(TxIn.hashPrevOut(lineSplit), TxIn.indexPrevOut(lineSplit)));
                    }
                }
        );
    }

    // Second step
    public static void unspentOuts() throws IOException {

        PrintWriter writer = new PrintWriter(new FileWriter(unspent_outs, false));

        readFileLines(tx_out_csv,
                new IFileLineReader() {

                    long start = System.currentTimeMillis();
                    int maxLines = 770_294_424;
                    int i = 0;

                    @Override
                    public void onLineRead(String line) {
                        i++;
                        if (i % 1_000_000 == 0) {
                            long totalSpentMs = System.currentTimeMillis() - start;
                            float speedPerSec = i / totalSpentMs * 1000;

                            System.out.println("unspentOuts: " + (i / 1_000_000) + " m lines. Speed: " + speedPerSec / 1_000_000 + " m lines/sec " + " [" + Math.floor(((double) i / maxLines) * 100) + "%]");
                        }
                    }
                },
                new IFileLineReader() {

                    @Override
                    public void onLineRead(String line) {
                        String[] lineSplit = line.split(CVS_SPLIT_BY, -1);

                        if (!TxOut.address(lineSplit).startsWith("1")) {
                            return;
                        }

                        if (BLOOM_FILTER.mightContain(uniqueTransKey(TxOut.txid(lineSplit), TxOut.indexOut(lineSplit)))) {
                            return;
                        }

                        writer.append(TxOut.address(lineSplit) + ";" + TxOut.value(lineSplit) + "\r\n");
                    }
                }
        );

        writer.close();
    }


    public static String uniqueTransKey(String hashPrevOut, String indexPrevOut) {
        return hashPrevOut/*.substring(0, 30) */ + "" + indexPrevOut;
    }

    public static void readFileLines(String filePath, IFileLineReader... lineReader) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }

            for (IFileLineReader iFileLineReader : lineReader) {

                try {
                    iFileLineReader.onLineRead(line);
                } catch (Exception e) {
                    System.out.println(line);
                    e.printStackTrace();
                }
            }
        }

        br.close();
    }

    public interface IFileLineReader {
        void onLineRead(String line);
    }
}
