# Phase 4 Checkpoint ✅

Do these without looking back at the README. Answers/criteria at the bottom.

1. **Grade it.** In a new file `Grader.java`, set `int score = 83;` and print a letter grade using `if / else if / else`: A for ≥90, B for ≥80, C for ≥70, else "below C".

2. **Spot the bug.** What's wrong here, what does it actually do, and how do you fix it?
   ```java
   if (mode = 1) {
       System.out.println("driving");
   }
   ```

3. **Deadzone.** Write an `if` that sets `double power = 0.0;` when `Math.abs(power)` is less than `0.05`, and otherwise leaves it alone. Test it with `power = 0.03` and `power = 0.6`.

4. **Boolean logic.** Given `boolean batteryOk = true;` and `boolean armClear = false;`, write a single `if` that prints `"go"` only when the battery is OK *and* the arm is clear. Does it print for these values?

5. **FTC connection.** Write the `if / else` that opens the claw (`claw.setPosition(0.0)`) when `gamepad1.a` is pressed and closes it (`claw.setPosition(1.0)`) otherwise. What type is `gamepad1.a`?

## Self-grading criteria
1. ✅ if the chain is ordered high-to-low and `83` prints `B`. (Order matters: `>=70` must come after `>=80`.)
2. ✅ `=` assigns instead of compares; here it fails to compile with `incompatible types: int cannot be converted to boolean`. Fix: `if (mode == 1)`.
3. ✅ `if (Math.abs(power) < 0.05) { power = 0.0; }` → `0.03` becomes `0.0`; `0.6` stays `0.6`.
4. ✅ `if (batteryOk && armClear) { System.out.println("go"); }` → prints **nothing** here, because `armClear` is `false` (AND needs both true).
5. ✅ `if (gamepad1.a) { claw.setPosition(0.0); } else { claw.setPosition(1.0); }` — `gamepad1.a` is a `boolean`.

**Passed all 5?** Update [`docs/PROGRESS.md`](../docs/PROGRESS.md) and go to Phase 5. 🎉
