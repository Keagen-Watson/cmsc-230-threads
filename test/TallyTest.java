import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 *
 * @author Keagen Watson
 */
public class TallyTest extends Tally {
    @Test
    void totalIsNotShort() throws InterruptedException {
        Tally one = new Tally();
        Tally two = new Tally();
        Tally three = new Tally();
        Tally four = new Tally();
        // Takes mine's lock
        Thread first = new Thread(() -> {
            for (int i = 0; i < 25_000; i++) {
                Tally.add(one);
            }
        });
        // Takes yours's lock
        Thread second = new Thread(() -> {
            for (int i = 0; i < 25_000; i++) {
                Tally.add(two);
            }
        });
        Thread third = new Thread(() -> {
            for (int i = 0; i < 25_000; i++) {
                Tally.add(three);
            }
        });
        Thread fourth = new Thread(() -> {
            for (int i = 0; i < 25_000; i++) {
                Tally.add(four);
            }
        });
        first.start();
        second.start();
        third.start();
        fourth.start();
        first.join();
        second.join();
        third.join();
        fourth.join();
        assertEquals(100_000, Tally.total);
    }
}
