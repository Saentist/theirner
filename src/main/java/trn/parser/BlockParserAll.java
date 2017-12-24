package trn.parser;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.Charset;

public class BlockParserAll {

    private static BloomFilter BLOOM_FILTER;

    public static void main(String[] args) throws IOException {

        BLOOM_FILTER = BloomFilter.create(
                Funnels.stringFunnel(Charset.forName("UTF-8")),
                310_000_000,
                0.000_000_000_1);

        PrintWriter writer_addr_uq = new PrintWriter(new FileWriter(BlockParser.addr_all, false));

        BlockParser.readFileLines(BlockParser.tx_out_csv,

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
                            System.out.println("all addresses: " + (i / 1_000_000) + " m lines. Speed: " + speedPerSec / 1_000_000 + " m lines/sec " + " added about: " + BLOOM_FILTER.approximateElementCount() + " addresses.");
                        }
                    }
                },
                new BlockParser.IFileLineReader() {

                    @Override
                    public void onLineRead(String line) {
                        String[] lineSplit = line.split(BlockParser.CVS_SPLIT_BY, -1);

                        String address = lineSplit[4];

                        if (!StringUtils.startsWith(address, "1")) {
                            return;
                        }

                        if (BLOOM_FILTER.mightContain(address)) {
                            return;
                        }

                        BLOOM_FILTER.put(address);

                        writer_addr_uq.append(address + "\r\n");
                    }
                }
        );

        writer_addr_uq.close();

        BLOOM_FILTER.writeTo(new FileOutputStream(BlockParser.addr_all_bin));

        System.out.println("BlockParserAll: done: " + BLOOM_FILTER.approximateElementCount() + " addresses.");
    }
}
