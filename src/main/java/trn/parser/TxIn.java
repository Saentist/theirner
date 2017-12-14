package trn.parser;

import java.util.List;
import java.util.Set;

// @txid, @hashPrevOut, indexPrevOut, scriptSig, sequence
public class TxIn {

    public static String txid(String[] lineSplit) {
        return lineSplit[0];
    }

    public static String hashPrevOut(String[] lineSplit) {
        return lineSplit[1];
    }

    public static String indexPrevOut(String[] lineSplit) {
        return lineSplit[2];
    }

    public static String scriptSig(String[] lineSplit) {
        return lineSplit[3];
    }

    public static String sequence(String[] lineSplit) {
        return lineSplit[4];
    }

    public static boolean isExists(List<String[]> txIns, String hashPrevOut, String indexPrevOut) {
        return txIns.parallelStream().anyMatch(lineSplit -> hashPrevOut(lineSplit).equals(hashPrevOut) && indexPrevOut(lineSplit).equals(indexPrevOut));
    }

    public static boolean isExists(Set<String> prevOuts, String hashPrevOut, String indexPrevOut) {
        return prevOuts.contains(hashPrevOut + "" + indexPrevOut);
    }
}
