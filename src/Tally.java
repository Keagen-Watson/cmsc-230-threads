// one total for the whole program, added to through any Tally
class Tally {
    static int total;

    // The lock of the object calling add
    static synchronized void add(Tally tally) {
        total++;
    }

    static synchronized void reset() {
        total = 0;
    }
}
