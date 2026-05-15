package util;

import java.util.concurrent.ThreadLocalRandom;

public class WaitUtil {

    private WaitUtil() {
        // Utility class
    }

    public static void waitNoLongerThan(double seconds) {

        long randomDelay = ThreadLocalRandom.current().nextLong(100, Math.round( 1000 * seconds));
        try {
            Thread.sleep(randomDelay);
        } catch (InterruptedException e) {
           LogUtil.warning("Thread interrompida durante espera: %s", e.getMessage());
        }


    }

    public static void waitFor(double seconds) {
        long delay = Math.round(1000 * seconds);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            LogUtil.warning("Thread interrompida durante espera: %s", e.getMessage());
        }
    }
}
