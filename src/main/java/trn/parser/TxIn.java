package trn.parser;

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
}
