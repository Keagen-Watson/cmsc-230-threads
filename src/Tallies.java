// two threads, each with a Tally of its own, adding to the one total
public class Tallies {
    public static void main(String[] args) throws InterruptedException {
        Tally mine = new Tally();
        // Takes mine's lock
        Thread first = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                mine.add(mine);
            }
        });
        // Takes yours's lock
        Thread second = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                Tally.reset();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(Tally.total);
    }
}
