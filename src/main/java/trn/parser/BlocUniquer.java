package trn.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class BlocUniquer {

    public static int BTC_0_01 = 1_000_000;

    public static float min_amount = BTC_0_01 * 50; // 0.5 BTC
    public static long max_lines = 2_000_000;

    public static void main(String[] args) throws IOException {

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

                    @Override
                    public void onLineRead(String line) {
                        String[] lineSplit = line.split(BlockParser.CVS_SPLIT_BY, -1);

                        String address = lineSplit[0];

                        if (address.contains("111111")) {
                            return;
                        }

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

        PrintWriter writer_unspent_uq = new PrintWriter(new FileWriter(BlockParser.unspent_uq, false));
        PrintWriter writer_unspent_total = new PrintWriter(new FileWriter(BlockParser.unspent_total, false));

        List<Map.Entry<String, Long>> unspentList = new LinkedList(unspent.entrySet());
        unspentList.sort((Comparator) (o1, o2) -> -((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue()));

        long totalPrinted = 0;

        for (Map.Entry<String, Long> entry : unspentList) {

            String address = entry.getKey();

            long amount = unspent.get(address);

            if (amount < min_amount) { // 0.5 BTC
                break;
            }

            if (totalPrinted > max_lines) {
                break;
            }

            writer_unspent_total.append(address + ": " + (amount / 100_000_000F) + "\r\n");

            String addrrCut = address.length() > 27 ? address.substring(0, 27) : address;
            writer_unspent_uq.append(addrrCut + "\r\n");

            totalPrinted++;

        }

        writer_unspent_total.close();
        writer_unspent_uq.close();

        System.out.println("BlocUniquer: done: " + unspentList.size() + " addresses.");
    }
}
