package trn.parser;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

public class BlockParser {

    public static final String CVS_SPLIT_BY = ";";
    public static final String tx_in_csv = "h:\\tx_in.csv.tmp";
    public static final String tx_out_csv = "g:\\csv_dump\\tx_out.csv.tmp";

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        // @txid, @hashPrevOut, indexPrevOut, scriptSig, sequence
        //List<String[]> txIns = readFile(tx_in_csv, null);
        BloomFilter filter = readBloomFilter(tx_in_csv);

        // @txid, indexOut, value, @scriptPubKey, address
/*        List<String[]> txOuts = readFile(tx_out_csv, ";1");

        System.out.println(txIns.size() + " vs " + txOuts.size() + " in " + (System.currentTimeMillis() - start) + " ms. ");


        Set<String> prevOuts = new HashSet<>();

        for (String[] lineSplit : txIns) {
            prevOuts.add(TxIn.hashPrevOut(lineSplit) + "" + TxIn.indexPrevOut(lineSplit));
        }

        int i = 0;
        for (String[] txOut : txOuts) {
            if (TxIn.isExists(prevOuts, TxOut.txid(txOut), TxOut.indexOut(txOut))) {
                TxOut.setUnspent(txOut, false);
            }

            i++;
            if (i % 1000 == 0) {
                System.out.println(((i / txOuts.size()) * 100) + " % done");
            }
        }


        PrintWriter writer = new PrintWriter(new FileWriter("unspent_addresses.txt", true));

        for (String[] txOut : txOuts) {
            if (TxOut.unspent(txOut)) {
                writer.append(TxOut.address(txOut) + "\r\n");
            }
        }*/

        System.out.println(" Done in " + (System.currentTimeMillis() - start) + " ms. ");
    }

    public static List<String[]> readFile(String csvFile, String postfix) throws IOException {

        List<String[]> rv = new LinkedList<>();

        BufferedReader br;
        String line;

        br = new BufferedReader(new FileReader(csvFile));


        while ((line = br.readLine()) != null) {
            String[] lineSplit = (postfix == null ? line : line + postfix).split(CVS_SPLIT_BY);
            if (postfix != null && !TxOut.address(lineSplit).startsWith("1")) {
                continue;
            }
            rv.add(lineSplit);
        }

        br.close();

        return rv;
    }

    public static BloomFilter readBloomFilter(String csvFile) throws IOException {

        BloomFilter rv = BloomFilter.create(
                Funnels.stringFunnel(Charset.forName("UTF-8")),
                900_000_000,
                0.000_000_000_001);


        BufferedReader br;
        String line;

        br = new BufferedReader(new FileReader(csvFile));

        int i = 0;

        int maxLines = 700_294_424;

        while ((line = br.readLine()) != null) {
            String[] lineSplit = line.split(CVS_SPLIT_BY);
            rv.put(uniqueTransKey(TxIn.hashPrevOut(lineSplit), TxIn.indexPrevOut(lineSplit)));

            i++;
            if (i % 1_000_000 == 0) {
                System.out.println("readBloomFilter: " + i + " [" + (((double) i / maxLines) * 100) + "%]");
            }
        }

        br.close();

        return rv;
    }

    private static String uniqueTransKey(String hashPrevOut, String indexPrevOut) {
        return hashPrevOut/*.substring(0, 30) */ + "" + indexPrevOut;
    }
}
