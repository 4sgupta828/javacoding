// Phase 5 — the three loop shapes, plus break and continue.
// Run:   java Loops.java

public class Loops {
    public static void main(String[] args) {

        // ---- for loop: use it when you KNOW how many times ----
        // start at i = 0, keep going while i < 5, add 1 each time.
        // i takes the values 0, 1, 2, 3, 4  (five numbers, NOT up to 5).
        System.out.println("for loop:");
        for (int i = 0; i < 5; i++) {
            System.out.println("  i = " + i);
        }

        // ---- while loop: use it when you loop UNTIL something changes ----
        // Here we count down. The condition is checked BEFORE each pass.
        System.out.println("while loop:");
        int countdown = 3;
        while (countdown > 0) {
            System.out.println("  " + countdown);
            countdown--;          // <-- if you forget this, the loop NEVER ends
        }
        System.out.println("  liftoff!");

        // ---- do-while loop: runs the body AT LEAST ONCE, then checks ----
        // The condition is checked AFTER the first pass, so the body always runs once.
        System.out.println("do-while loop:");
        int n = 10;
        do {
            System.out.println("  ran once, n = " + n);
        } while (n < 5);          // 10 < 5 is false, so it stops after one pass

        // ---- break: leave the loop early ----
        System.out.println("break at 3:");
        for (int i = 0; i < 100; i++) {
            if (i == 3) {
                break;            // jump out of the loop completely
            }
            System.out.println("  " + i);
        }

        // ---- continue: skip the rest of THIS pass, go to the next ----
        System.out.println("continue (odd numbers only):");
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {     // if i is even...
                continue;         // ...skip the println and go to the next i
            }
            System.out.println("  " + i);
        }
    }
}
