import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 *
 * @author Keagen Watson
 */
public class WithdrawlsTest {
    @Test
    void noOverdraft() throws InterruptedException {
        Account[] accounts = new Account[100_000];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account("a" + i, 100);
        }
        Runnable work = () -> {
            for (Account account : accounts) {
                synchronized (account) {
                    if (account.takeIfEnough(80)) {
                        account.add(-80);
                    }
                }
            }
        };
        Thread first = new Thread(work);
        Thread second = new Thread(work);
        first.start();
        second.start();
        first.join();
        second.join();
        int below = 0;
        for (Account account : accounts) {
            if (account.getBalance() < 0) {
                below++;
            }
        }
        assertEquals(0, below);
    }
}
