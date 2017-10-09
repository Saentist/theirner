package trn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Base58Check - bitcoin address format
 *
 * @author aillusions
 */
public class Main {

    private static final AtomicLong counter = new AtomicLong();

    public static void main(String[] args) throws IOException {

        PrintWriter writer = new PrintWriter("theirner.log", "UTF-8");

        for (int i = 0; i < 8; i++) {
            Thread thread = new Thread(new PrivKeyFinderWorker(writer, counter));
            thread.start();
        }

        Timer timer = new Timer();
        TimerTask minutelyTask = new TimerTask() {
            @Override
            public void run() {
                writer.append("Processed: " + counter.get() + "\n");
                writer.flush();
            }
        };

        timer.schedule(minutelyTask, 0l, 1000 * 60);
    }
}
