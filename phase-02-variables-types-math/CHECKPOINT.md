# Phase 2 Checkpoint ✅

Do these without looking back at the README. Answers/criteria at the bottom.

1. **Declare the four.** In a new file `Vars.java`, declare one `int`, one `double`, one `boolean`, and one `String`, give each a value, and print all four with labels.

2. **Predict, then run.** Before running, write down what each line prints:
   ```java
   System.out.println(9 / 4);
   System.out.println(9 / 4.0);
   System.out.println(9 % 4);
   System.out.println((int) 4.99);
   ```

3. **Fix the bug.** This is supposed to print `0.5` but prints `0`. Fix it:
   ```java
   double result = 1 / 2;
   System.out.println(result);
   ```

4. **Ticks to revolutions.** A motor has `1120` ticks per revolution and has counted `2800` ticks. Print how many revolutions that is *as a decimal* (you should get `2.5`).

5. **FTC connection.** What Java type is a motor's power? What type is an encoder tick count? Give the range of legal motor-power values.

## Self-grading criteria
1. ✅ if it compiles and prints four labeled values, with correct types (`String` in `"..."`, `double` has a decimal, `boolean` is `true`/`false`).
2. ✅ `2`, `2.25`, `1`, `4`. (First is integer division; last is truncation, not rounding.)
3. ✅ The right side `1 / 2` is integer division → `0`. Fix: `double result = 1 / 2.0;` (or `1.0 / 2`). Now it prints `0.5`.
4. ✅ `(double) 2800 / 1120` → `2.5`. Using `2800 / 1120` (both `int`) would wrongly give `2`.
5. ✅ Power is a `double` in the range **−1.0 to 1.0** (`0.0` is stop); an encoder tick count is an `int`.

**Passed all 5?** Update [`docs/PROGRESS.md`](../docs/PROGRESS.md) and go to Phase 3. 🎉
