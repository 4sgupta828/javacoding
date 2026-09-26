# Phase 8 Checkpoint ✅

Do these without looking back at the README. Answers/criteria at the bottom.

1. **Build a class from scratch.** In a new file `Battery.java`, write a `Battery` class with a private `int` field `percent`, a constructor that starts it at 100, a method `drain(int amount)` that lowers it, and `report()` that prints `Battery: NN%`. In `main`, build one, drain it twice, and report. Run with `java Battery.java`.

2. **Two objects, two states.** Build **two** `Battery` objects. Drain only the first. Show that the second is still at 100. Why are their states independent?

3. **Break it on purpose.** Declare `Battery b;` (no `new`) and call `b.report()`. Run it, copy the error, and say in one sentence what went wrong.

4. **static vs instance.** Add a `static int batteriesMade` that increases by 1 in the constructor. Print it via `Battery.batteriesMade` after building two batteries. Why does it read `2` and not `1`?

5. **FTC connection.** In `hardwareMap.get(DcMotor.class, "left_drive")`: (a) what does it hand back, and (b) name two methods you'd then call on that thing.

## Self-grading criteria
1. ✅ `percent` is `private`; constructor sets it to 100; `drain` subtracts; `report()` prints the value. Runs and drops below 100.
2. ✅ The second still prints 100. Independent because each `new Battery()` is a separate object with its **own** copy of the `percent` field.
3. ✅ You got a `NullPointerException` ("... because \"b\" is null"). Cause: you declared the variable but never built an object with `new`, so it pointed at nothing.
4. ✅ Prints `2`. `batteriesMade` is `static` — one counter shared by the whole class, bumped once per constructor call, so two `new`s make it 2. (Instance fields would each be separate; this is deliberately shared.)
5. ✅ (a) It returns a `DcMotor` **object** you control. (b) e.g. `setPower(0.5)` and `setDirection(...)` (also acceptable: `getCurrentPosition()`).

**Passed all 5?** Update [`docs/PROGRESS.md`](../docs/PROGRESS.md) and go to Phase 9. 🎉
