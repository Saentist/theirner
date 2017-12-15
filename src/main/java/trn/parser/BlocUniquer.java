package trn.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BlocUniquer {

    public static void main(String[] args) throws IOException {


        PrintWriter writer_unspent_uq = new PrintWriter(new FileWriter(BlockParser.unspent_uq, false));
        PrintWriter writer_unspent_total = new PrintWriter(new FileWriter(BlockParser.unspent_total, false));

        Map<String, Long> unspent = new HashMap<>();

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

                    Set<String> printedAddresses = new HashSet<>();

                    @Override
                    public void onLineRead(String line) {
                        String[] lineSplit = line.split(BlockParser.CVS_SPLIT_BY, -1);

                        String address = lineSplit[0];

                        if (!printedAddresses.contains(address)) {
                            String addrrCut = address.length() > 27 ? address.substring(0, 27) : address;
                            writer_unspent_uq.append(addrrCut + "\r\n");
                            printedAddresses.add(address);
                        }
                    }
                },
                new BlockParser.IFileLineReader() {

                    @Override
                    public void onLineRead(String line) {
                        String[] lineSplit = line.split(BlockParser.CVS_SPLIT_BY, -1);

                        String address = lineSplit[0];
                        String amountStr = lineSplit[1];
                        long amount = Long.parseLong(amountStr);

                        if (unspent.containsKey(address)) {
                            unspent.put(address, unspent.get(address) + amount);
                        } else {
                            unspent.put(address, amount);
                        }
                    }
                }
        );

        writer_unspent_uq.close();

        for (String address : unspent.keySet()) {
            long amount = unspent.get(address);

            if (amount >= 1_000_000) { // 0.01 BTC
                writer_unspent_total.append(address + ": " + (amount / 100_000_000F) + "\r\n");
            }
        }

        writer_unspent_total.close();

        System.out.println("BlocUniquer: done.");
    }
}
