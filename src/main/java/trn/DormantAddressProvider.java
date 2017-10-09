package trn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author aillusions
 */
public class DormantAddressProvider {

    private List<String> listOfLines;

    public DormantAddressProvider() {

        try {
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(DormantAddressProvider.class.getClassLoader().getResourceAsStream("dormant.txt")));
            listOfLines = new ArrayList<>();
            String line = null;

            line = bufReader.readLine();

            while (line != null) {
                listOfLines.add(line);
                line = bufReader.readLine();
            }
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getDormantAddresses() {
        return listOfLines;
    }
}
