# Phase 6 Checkpoint ✅

Do these without looking back at the README. Answers/criteria at the bottom.

1. **Write a method.** In a new file `MathHelpers.java`, write a method `int triple(int x)` that returns `x * 3`, and call it from `main` to print `triple(7)`.

2. **Overload it.** Add a second method, also named `triple`, that takes a `double` and returns a `double`. Call both from `main` and show they print different results (`21` vs, say, `7.5`).

3. **Read the stack trace.** Given this trace, which file and line should you look at *first*, and what's the underlying bug?
   ```
   Exception in thread "main" java.lang.RuntimeException: init failed
   	at Arm.setup(Arm.java:30)
   	at Arm.main(Arm.java:8)
   Caused by: java.lang.NullPointerException: Cannot invoke "Servo.setPosition(double)" because "this.claw" is null
   	at Arm.setup(Arm.java:27)
   	... 1 more
   ```

4. **Catch an exception.** Write code that tries `Integer.parseInt("12x")` inside a `try`, catches the `NumberFormatException`, and prints `Not a valid number` instead of crashing. Confirm the program prints a line *after* the try/catch.

5. **FTC connection.** Why does `public void runOpMode() throws InterruptedException` have `throws` on it, and does that mean you are catching the exception yourself?

## Self-grading criteria
1. ✅ `triple(7)` prints `21`. Method: `static int triple(int x) { return x * 3; }`.
2. ✅ You have two `triple` methods with different parameter types (`int` and `double`). `triple(2.5)` prints `7.5`. That's overloading; Java chooses by argument type.
3. ✅ Look at **Arm.java line 27 first** — the `Caused by:` NullPointerException is the root cause. The bug: `claw` (a `Servo`) was never initialized (it's `null`), so `claw.setPosition(...)` crashes. (Line 30 is just the symptom that wrapped it.)
4. ✅ Prints `Not a valid number`, then a following line still runs, e.g.:
   ```java
   try {
       int n = Integer.parseInt("12x");
       System.out.println(n);
   } catch (NumberFormatException e) {
       System.out.println("Not a valid number");
   }
   System.out.println("still running");
   ```
5. ✅ `throws` **declares** that the method might throw `InterruptedException` and is passing that responsibility up to the FTC framework — you are **not** catching it yourself. It's required because calls like `waitForStart()`/`sleep()` can throw it.

**Passed all 5?** Update [`docs/PROGRESS.md`](../docs/PROGRESS.md) and go to Phase 7. 🎉
