// Phase 8 — a tiny object with clean state: a stopwatch.
// This is a stand-in for FTC's `ElapsedTime` object. Same shape:
//    timer.reset();      // start counting from zero
//    timer.seconds();    // how long since the last reset
//
// We can't use the real ElapsedTime here (that needs the FTC SDK), so we build
// our own small version using the JVM clock. The IDEA is identical.
//
// Run:   java TimerDemo.java

public class TimerDemo {
    public static void main(String[] args) throws InterruptedException {

        // Build a stopwatch OBJECT. `new` runs the constructor, which starts the clock.
        StopClock timer = new StopClock();

        System.out.println("Working...");
        Thread.sleep(400);                       // pretend to do 0.4s of work (blocks this program)

        // Ask the object how much time has passed. It answers from ITS OWN stored start time.
        System.out.printf("Elapsed: %.2f seconds%n", timer.seconds());

        timer.reset();                           // start over from zero
        Thread.sleep(150);
        System.out.printf("After reset: %.2f seconds%n", timer.seconds());
    }
}

// A minimal timer object. State = the moment it was last started.
class StopClock {
    private long startNanos;                     // FIELD: when we last reset, in nanoseconds

    // Constructor: the clock starts running the instant you build it.
    public StopClock() {
        reset();                                 // reuse our own method — no need to repeat code
    }

    // Restart from zero.
    public void reset() {
        this.startNanos = System.nanoTime();     // remember "now" in this object
    }

    // Return seconds since the last reset (nanoseconds / 1 billion).
    public double seconds() {
        long elapsedNanos = System.nanoTime() - startNanos;
        return elapsedNanos / 1_000_000_000.0;   // divide by a DOUBLE so we keep the fraction
    }
}
