// Phase 11 — the TWO shapes of an OpMode, side by side, simulated in plain Java.
//
//   LinearOpMode   = a SCRIPT you write top-to-bottom (you run your own loop).
//   iterative OpMode = a set of CALLBACKS the system calls FOR you.
//
// Same robot behavior, two mental models. Run:   java TwoOpModeStyles.java

public class TwoOpModeStyles {

    static final int FAKE_LOOPS = 5;   // pretend the match runs this many loops

    public static void main(String[] args) {
        System.out.println("===== LinearOpMode style (a script) =====");
        new MyLinearOp().runOpMode();          // YOU call it; it runs start to finish

        System.out.println("\n===== Iterative OpMode style (callbacks) =====");
        runIterative(new MyIterativeOp());     // the "app" calls the callbacks for you
    }

    // ============ LinearOpMode-style ============
    // You override ONE method (runOpMode) and write the whole match as a script:
    // set up, wait for start, then loop yourself.
    static class MyLinearOp {
        int counter = 0;

        void runOpMode() {
            System.out.println("[init]  motors ready");            // setup runs first
            System.out.println("[start] waitForStart() returned"); // driver pressed START
            for (int i = 0; i < FAKE_LOOPS; i++) {                 // YOUR loop = while(opModeIsActive())
                counter++;
                System.out.println("[loop]  counter = " + counter);
            }
            System.out.println("[stop]  script reached the end");
        }
    }

    // ============ Iterative OpMode-style ============
    // You DON'T write a loop. You fill in callbacks; the system decides when to call each.
    interface OpMode {
        void init();   // called once, when the OpMode is selected
        void loop();   // called over and over, after START
        void stop();   // called once, at the end
    }

    static class MyIterativeOp implements OpMode {
        int counter = 0;
        @Override public void init() { System.out.println("[init]  motors ready"); }
        @Override public void loop() { counter++; System.out.println("[loop]  counter = " + counter); }
        @Override public void stop() { System.out.println("[stop]  cleaned up"); }
    }

    // This method plays the role of the FTC app: it calls your callbacks in order.
    static void runIterative(OpMode op) {
        op.init();                                 // once
        System.out.println("[start] driver pressed START");
        for (int i = 0; i < FAKE_LOOPS; i++) {
            op.loop();                             // again and again
        }
        op.stop();                                 // once
    }
}
