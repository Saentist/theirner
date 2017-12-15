package trn.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class BlocUniquer {

    public static void main(String[] args) throws IOException {

        Set<String> pritedAddresses = new HashSet<>();

        PrintWriter writer = new PrintWriter(new FileWriter(BlockParser.unspent_uq, false));

        BlockParser.readFileLines(BlockParser.unspent_outs,

                new BlockParser.IFileLineReader() {

                    long start = System.currentTimeMillis();
                    int i = 0;

                    @Override
                    public void onLineRead(String line) {
                        i++;
                        if (i % 1_000_000 == 0) {
                            long totalSpentMs = System.currentTimeMillis() - start;
                            float speedPerSec = i / totalSpentMs * 1000;

                            System.out.println("unspentOuts: " + (i / 1_000_000) + " m lines. Speed: " + speedPerSec / 1_000_000 + " m lines/sec ");
                        }
                    }
                },
                new BlockParser.IFileLineReader() {

                    @Override
                    public void onLineRead(String line) {
                        String[] lineSplit = line.split(BlockParser.CVS_SPLIT_BY, -1);

                        String address = lineSplit[0];

                        if (!pritedAddresses.contains(address)) {
                            String addrrCut = address.length() > 27 ? address.substring(0, 27) : address;
                            writer.append(addrrCut + "\r\n");
                            pritedAddresses.add(address);
                        }
                    }
                }
        );

        writer.close();
    }
}
