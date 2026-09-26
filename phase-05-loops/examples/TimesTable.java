// Phase 5 — nested loops: a loop inside a loop.
// This prints a multiplication table. The OUTER loop picks the row,
// the INNER loop walks across the columns of that row.
// Run:   java TimesTable.java

public class TimesTable {
    public static void main(String[] args) {

        int size = 5;   // make a 5 x 5 table (rows 1..5, columns 1..5)

        // OUTER loop: one pass per row.
        for (int row = 1; row <= size; row++) {

            // INNER loop: runs completely, start to finish, for EACH row.
            for (int col = 1; col <= size; col++) {
                int product = row * col;

                // print with a tab so the columns line up; no newline yet.
                System.out.print(product + "\t");
            }

            // After the inner loop finishes one full row, end the line.
            System.out.println();
        }

        // Key idea: for a 5x5 table the inner body runs 5 * 5 = 25 times total.
        // Nested loops multiply: careful, they get expensive fast.
    }
}
