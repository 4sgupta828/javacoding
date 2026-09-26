# Phase 7 Checkpoint ✅

Do these without looking back at the README. Answers/criteria at the bottom.

1. **Sum an array.** In a new file `Sum.java`, make an `int[]` of five numbers and use a loop to print their total. Run it with `java Sum.java`.

2. **Predict the aliasing output.** Without running it, what does this print, and why?
   ```java
   int[] a = {5, 6, 7};
   int[] b = a;
   b[1] = 100;
   System.out.println(a[1]);
   ```

3. **Grow a list.** Make an `ArrayList<String>` of three robot subsystems (e.g. `"drive"`, `"arm"`, `"claw"`), print the size, then add a fourth and print the size again.

4. **Look it up.** Make a `HashMap<String, Integer>` of two motors to their target positions. Print the target for one motor. Then print `map.get("nonexistent")` — what do you get?

5. **Spot the crash.** This loop is meant to print all elements but throws an exception. Which exception, and what's the fix?
   ```java
   int[] nums = {1, 2, 3, 4};
   for (int i = 0; i <= nums.length; i++) {
       System.out.println(nums[i]);
   }
   ```

## Self-grading criteria
1. ✅ if it prints the correct total using a loop (index loop or for-each), e.g. `int total = 0; for (int n : nums) total += n;`.
2. ✅ It prints **100**. `b = a` copies the reference, so `a` and `b` are the same array; changing `b[1]` changes `a[1]`. (This is aliasing.)
3. ✅ Prints `3` then `4`. The `ArrayList` grew when you called `add` a fourth time — no manual resizing needed.
4. ✅ Prints the correct target for the known motor, and prints `null` for `"nonexistent"` (a missing key returns `null`).
5. ✅ It throws `ArrayIndexOutOfBoundsException: Index 4 out of bounds for length 4`. The bug is `i <= nums.length`, which lets `i` reach `4` (past the last index `3`). Fix: change `<=` to `<` (`i < nums.length`).

**Passed all 5?** Update [`docs/PROGRESS.md`](../docs/PROGRESS.md) and go to Phase 8. 🎉
