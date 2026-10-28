import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

// section 4.2: your test goes here
class TransfersTest extends Transfers {

    @Test
    void transfersDoesNotResultInADeadlock() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            // make, start and join the two threads here
            Account alice = new Account("alice", 1000);
            Account bob = new Account("bob", 1000);

            Thread one = new Thread(() -> {
                for (int i = 0; i < 100_000; i++) {
                    transfer(alice, bob, 1);
                }
            });
            Thread two = new Thread(() -> {
                for (int i = 0; i < 100_000; i++) {
                    transfer(bob, alice, 1);
                }
            });

            one.start();
            two.start();
            one.join();
            two.join();
            assertEquals(2000, alice.getBalance() + bob.getBalance());
        });
    }

    @Test
    void threeWayTransfer() {

    }
}
