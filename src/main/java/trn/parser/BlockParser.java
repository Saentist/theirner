package trn.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BlockParser {

    public static final String CVS_SPLIT_BY = ";";
    public static final String tx_in_csv = "g:\\csv_dump\\tx_in.csv.tmp";
    public static final String tx_out_csv = "g:\\csv_dump\\tx_out.csv.tmp";

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        // @txid, @hashPrevOut, indexPrevOut, scriptSig, sequence
        List<String[]> txIns = readFile(tx_in_csv, null);

        // @txid, indexOut, value, @scriptPubKey, address
        List<String[]> txOuts = readFile(tx_out_csv, ";1");

        System.out.println(txIns.size() + " vs " + txOuts.size() + " in " + (System.currentTimeMillis() - start) + " ms. ");

        for (String[] txOut : txOuts) {
            System.out.println(TxOut.address(txOut) + " " + TxOut.unspent(txOut));
        }
    }

    public static List<String[]> readFile(String csvFile, String postfix) throws IOException {

        List<String[]> rv = new LinkedList<>();

        BufferedReader br;
        String line;

        br = new BufferedReader(new FileReader(csvFile));

        while ((line = br.readLine()) != null) {
            String[] lineSplit = (postfix == null ? line : line + postfix).split(CVS_SPLIT_BY);
            rv.add(lineSplit);
        }

        br.close();

        return rv;
    }
}
