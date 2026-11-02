import java.util.concurrent.atomic.AtomicInteger;

public class Withdrawals {
    public static void main(String[] args) throws InterruptedException {
        Account[] accounts = new Account[100_000];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account("a" + i, 100);
        }
        AtomicInteger withdrawals = new AtomicInteger();
        Runnable work = () -> {
            for (Account account : accounts) {
                synchronized (account) {
                    if (account.takeIfEnough(80)) {
                        account.add(-80);
                        withdrawals.getAndIncrement();
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
        System.out.println(below + " accounts below zero");
        System.out.println(withdrawals);
    }
}
