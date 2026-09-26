# Phase 10 Checkpoint ✅

Do these without looking back at the README. Answers/criteria at the bottom.

1. **Write a contract.** In a new file `Contracts.java`, define an interface `Sensor` with one method `double read();`. Make a class `DistanceSensor implements Sensor` whose `read()` returns `42.0`. In `main`, store it in a `Sensor` variable and print `read()`.

2. **Name the states.** Define `enum ClawState { OPEN, CLOSED }`. Write a method `ClawState toggle(ClawState c)` that flips OPEN↔CLOSED using a `switch`. Print `toggle(OPEN)` and `toggle(CLOSED)`.

3. **Break and read.** Add `implements Sensor` to a class but *don't* write `read()`. Run it. Copy the error. What is Java demanding?

4. **Read a lambda.** In plain English, what does this line do, and when does the printing happen?
   ```java
   Runnable r = () -> System.out.println("go");
   ```

5. **FTC connection.** Name the annotation that puts an OpMode on the driver's *TeleOp* menu, and the one for *Autonomous*. What is `@Override` for?

## Self-grading criteria
1. ✅ if `Sensor s = new DistanceSensor();` compiles and `s.read()` prints `42.0`. (Storing the object in the *interface* type is the point — that's programming to the contract.)
2. ✅ `toggle(OPEN)` prints `CLOSED` and `toggle(CLOSED)` prints `OPEN`. Inside the switch you used `case OPEN:` / `case CLOSED:` (unqualified names).
3. ✅ if you got `error: <Class> is not abstract and does not override abstract method read() in Sensor`. Java is demanding you keep the contract — provide `read()`.
4. ✅ `r` holds a little function (a `Runnable`). The `println` does **not** run when `r` is created; it runs only when someone calls `r.run()`.
5. ✅ `@TeleOp` → TeleOp menu; `@Autonomous` → Autonomous menu. `@Override` tells the compiler to verify you're really replacing a parent/interface method (catches typos).

**Passed all 5?** Update [`docs/PROGRESS.md`](../docs/PROGRESS.md) and go to Phase 11 — the final capstone. 🎉
