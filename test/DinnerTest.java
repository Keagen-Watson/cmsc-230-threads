import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

/**
 *
 *
 * @author Keagen Watson
 */
public class DinnerTest extends Dinner {
    @Test
    void eachPhilosopherAteCompletely() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Fork[] forks = new Fork[5];
            for (int i = 0; i < 5; i++) {
                forks[i] = new Fork(i);
            }
            Thread[] philosophers = new Thread[5];
            for (int i = 0; i < 5; i++) {
                int who = i;
                Fork left = forks[i];
                Fork right = forks[(i + 1) % 5];
                philosophers[i] = new Thread(() -> {
                    for (int meal = 0; meal < 100_000; meal++) {
                        eat(who, left, right);
                    }
                });
            }
            for (Thread p : philosophers) {
                p.start();
            }
            for (Thread p : philosophers) {
                p.join();
            }
        });
    }
}
