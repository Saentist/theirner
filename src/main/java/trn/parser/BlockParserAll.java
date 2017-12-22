package trn.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BlockParserAll {

    public static void main(String[] args) throws IOException {

        PrintWriter writer_addr_uq = new PrintWriter(new FileWriter(BlockParser.addr_all, false));

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

                            writer_addr_uq.flush();
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

                        if (BlockParser.BLOOM_FILTER.mightContain(address)) {
                            return;
                        }

                        BlockParser.BLOOM_FILTER.put(address);

                        writer_addr_uq.append(address + "\r\n");
                    }
                }
        );

        writer_addr_uq.close();

        System.out.println("BlockParserAll: done: " + BlockParser.BLOOM_FILTER.approximateElementCount() + " addresses.");
    }
}
