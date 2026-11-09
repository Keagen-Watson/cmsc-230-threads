import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 *
 * @author Keagen Watson
 */
public class CountServerTest {
    @Test
    void CountServerNoLongerOvercounts() throws InterruptedException {
        CountServer.count = 0;
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100_000; j++) {
                    CountServer.add();
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        assertEquals(400_000, CountServer.count);
    }
}
