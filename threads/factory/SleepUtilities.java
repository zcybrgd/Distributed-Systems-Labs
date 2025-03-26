package threads.factory;

import java.util.Random;

public class SleepUtilities {
    private static final Random rand = new Random();

    public static void nap() {
        try {
            Thread.sleep(rand.nextInt(3000));  // Sleep for 0 to 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
