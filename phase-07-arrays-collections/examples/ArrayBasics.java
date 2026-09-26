// Phase 7 — arrays: a fixed-size row of boxes, all the same type.
// Also shows the enhanced for-loop and the ALIASING trap.
// Run:   java ArrayBasics.java

public class ArrayBasics {
    public static void main(String[] args) {

        // An array of 3 ints. Positions (indexes) are 0, 1, 2 — always start at 0.
        int[] scores = {10, 20, 30};

        // Read by index:
        System.out.println("First score:  " + scores[0]);   // 10
        System.out.println("Last score:   " + scores[2]);   // 30
        System.out.println("How many:     " + scores.length); // 3 (a field, not a method!)

        // Classic index loop: note  i < length  (NOT <=) to avoid going off the end.
        System.out.println("All scores (index loop):");
        for (int i = 0; i < scores.length; i++) {
            System.out.println("  scores[" + i + "] = " + scores[i]);
        }

        // Enhanced for-loop ("for each"): when you don't need the index, this is cleaner.
        // Read it as: "for each score in scores".
        System.out.println("All scores (for-each):");
        for (int score : scores) {
            System.out.println("  " + score);
        }

        // int total using the values:
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        System.out.println("Total: " + total);   // 60

        // ---- THE ALIASING TRAP: arrays are REFERENCES, not copies ----
        int[] a = {1, 2, 3};
        int[] b = a;            // b does NOT copy a — b points at the SAME array
        b[0] = 99;              // change through b...
        System.out.println("a[0] is now " + a[0]);   // ...and a sees it too! prints 99

        // ---- OFF THE END (don't uncomment unless you want the crash) ----
        // System.out.println(scores[3]);
        // Would throw:
        //   Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException:
        //   Index 3 out of bounds for length 3
    }
}
