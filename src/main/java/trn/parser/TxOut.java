package trn.parser;

import java.util.List;

//  @txid, indexOut, value, @scriptPubKey, address
public class TxOut {

    public static String txid(String[] lineSplit) {
        return lineSplit[0];
    }

    public static String indexOut(String[] lineSplit) {
        return lineSplit[1];
    }

    public static String value(String[] lineSplit) {
        return lineSplit[2];
    }

    public static String scriptPubKey(String[] lineSplit) {
        return lineSplit[3];
    }

    public static String address(String[] lineSplit) {
        return lineSplit[4];
    }

    public static boolean unspent(String[] lineSplit) {
        return lineSplit[5].equals("1");
    }

    public static void setUnspent(String[] lineSplit, boolean unspent) {
        lineSplit[5] = unspent ? "1" : "0";
    }

}
