// two accounts, two threads, a dollar at a time in opposite directions
public class Transfers {
    // moves amount from one account to the other, holding both locks
    static void transfer(Account from, Account to, int amount) {
        Account[] accounts = firstAccount(from, to);
        synchronized (accounts[0]) {
            synchronized (accounts[1]) {
                from.add(-amount);
                to.add(amount);
            }
        }
    }

    static void transfer(Account a1, Account a2, Account a3, int amount) {

    }

    private static Account[] firstAccount(Account a1, Account a2) {
        if (a1.getName().compareTo(a2.getName()) > 0) {
            return new Account[]{a2, a1};
        }
        return new Account[]{a1, a2};
    }

    public static void main(String[] args) throws InterruptedException {
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
        System.out.println(alice.getBalance() + " + " + bob.getBalance() + " = "
                + (alice.getBalance() + bob.getBalance()));
    }
}
