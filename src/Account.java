// an account: a name and a balance in whole dollars
public class Account {
    private final String name;
    private int balance;

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void add(int amount) {balance = balance + amount;}

    // Fixes what the 2 synchronized methods couldn't since this method is synchronized. Both
    // threads have to call the takeIfEnough() method separately so neither thread will ever
    // change an account that doesn't have enough money
    public synchronized boolean takeIfEnough(int amount) {
        return balance >= amount;
    }
}
