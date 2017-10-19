package trn.jocl.samples;

import com.aparapi.Kernel;
import com.aparapi.Range;

import java.util.stream.IntStream;

/**
 * @author aillusions
 */
public class Aparapi {


    public static void main(String[] args) {

        final int[] inA = IntStream.range(0, 100).toArray();

        final int[] inB = inA;

        final int[] result = new int[inA.length];

        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                int i = getGlobalId();
                for (long ax = 0; ax < 10000000000000000L; ax++) {
                    result[i] = inA[i] + inB[i];
                    for (int dx = 0; dx < 1000000000; dx++) {
                        result[i] = inA[i] + inB[i];
                    }
                }

                result[i] = inA[i] + inB[i];
            }
        };

        Range range = Range.create(result.length);
        kernel.execute(range);
    }
}
