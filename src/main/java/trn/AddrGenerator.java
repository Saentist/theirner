package trn;

import org.bitcoinj.core.ECKey;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static trn.PrivKeyFinderWorker.getBtcAddress;
import static trn.PrivKeyFinderWorker.getNewECKey;

public class AddrGenerator {

    public static void main(String[] args) throws IOException {

        PrintWriter writer = new PrintWriter(new FileWriter("1m_addresses.txt", true));

        for (int i = 0; i < 1_000_000; i++) {
            ECKey key = getNewECKey();
            String testBtcAddr = getBtcAddress(key);
            //writer.append(testBtcAddr + "\n");
            writer.append(testBtcAddr.substring(0, 27) + "\r\n");
        }

        writer.flush();
    }
}
