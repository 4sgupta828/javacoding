# Phase 5 Checkpoint ✅

Do these without looking back at the README. Answers/criteria at the bottom.

1. **Count with a `for`.** In a new file `Countdown.java`, use a `for` loop to print the numbers `10` down to `1`, one per line, then print `Liftoff!`. Run it with `java Countdown.java`.

2. **Fix the off-by-one.** This loop is supposed to print `1 2 3 4 5` but it prints too much. What's wrong, and what's the one-character fix?
   ```java
   for (int i = 1; i <= 6; i++) {
       System.out.println(i);
   }
   ```

3. **Skip and stop.** Using one `for` loop over `0..9`, print only the numbers that are **not** multiples of 3, and stop the loop entirely once you reach `8`. (Use `continue` and `break`.)

4. **Nested loop.** Print a 3-row, 4-column grid of `#` characters (a rectangle of hashes), using a loop inside a loop.

5. **The robot loop.** In plain English, what three steps happen on every pass of `while (opModeIsActive())`, and what makes that loop finally stop?

## Self-grading criteria
1. ✅ if it prints 10,9,...,1 then `Liftoff!`. A correct header: `for (int i = 10; i >= 1; i--)`.
2. ✅ The condition `i <= 6` runs one time too many (it prints `6`). Fix: change `<=` to `<` (i.e. `i < 6`), or change `6` to `5`. This is a fencepost / off-by-one error.
3. ✅ Prints `1 2 4 5 7` (skips 0,3,6 as multiples of 3; stops before 8 and 9). Example:
   ```java
   for (int i = 0; i < 10; i++) {
       if (i == 8) break;
       if (i % 3 == 0) continue;
       System.out.println(i);
   }
   ```
4. ✅ if you used an outer loop for the 3 rows and an inner loop for the 4 columns, e.g.:
   ```java
   for (int row = 0; row < 3; row++) {
       for (int col = 0; col < 4; col++) System.out.print("#");
       System.out.println();
   }
   ```
5. ✅ **Read inputs → decide → set outputs**, repeated ~50 times a second. It stops when `opModeIsActive()` becomes false (the match ends or the driver presses STOP).

**Passed all 5?** Update [`docs/PROGRESS.md`](../docs/PROGRESS.md) and go to Phase 6. 🎉
