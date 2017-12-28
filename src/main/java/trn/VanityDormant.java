package trn;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author aillusions
 */
public class VanityDormant {

    public static void main(String[] args) throws IOException {

        int maxLength = "1FeexV6bAHb8ybZjqQMjJrcCrHG".length();

        PrintWriter writer = new PrintWriter(new FileWriter("dormant_vanity.txt", false));

        DormantAddressProvider prov = new DormantAddressProvider();
        for (String s : prov.getDormantAddresses()) {
            writer.append(s.substring(0, maxLength) + "\n");
        }

        writer.close();
    }
}
