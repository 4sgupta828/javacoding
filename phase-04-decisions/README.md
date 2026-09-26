# Phase 4 — Decisions

> **Prereqs:** Phase 3 done — you understand input, `String.equals()`, and the polling loop model.

## The Big Idea: programs choose

Until now your code ran straight through, top to bottom. Real programs — and every robot — must **choose** what to do based on the situation: *if* the button is pressed, open the claw; *if* the stick is barely moved, ignore it; *if* the battery is low, slow down. That's branching, and it's built out of one idea you already met in Phase 2: the `boolean` (true/false). A **condition** is just a `boolean`, and an `if` runs its block only when that boolean is `true`.

## Mental model: a fork in the road

An `if` is a fork. The program walks up to it, checks a yes/no question (the condition), and takes exactly one path.

```
                 score >= 70 ?
                  /         \
               true        false
                /             \
        "you passed"      "try again"
```

`if / else if / else` is a *chain* of forks checked top to bottom: Java takes the **first** condition that's true and skips the rest. So order matters — put the most specific/highest conditions first.

## Worked examples

- [`examples/Decisions.java`](examples/Decisions.java) — `if / else if / else`, comparison operators (`== != < > <= >=`), the logic operators `&& || !`, and a light `switch`.
- [`examples/Deadzone.java`](examples/Deadzone.java) — an `if` that ignores tiny joystick values (`Math.abs(stick) < 0.05`) so the robot doesn't creep. This is a real FTC pattern.

### The operators you'll combine
| Comparison | Meaning | | Logic | Meaning |
|-----------|---------|---|-------|---------|
| `==` | equal to | | `&&` | AND (both true) |
| `!=` | not equal | | `\|\|` | OR (either true) |
| `<` `>` | less / greater | | `!` | NOT (flip it) |
| `<=` `>=` | less/greater or equal | | | |

## ⚠️ Common Traps (with the real errors)

**1. `=` vs `==` — assignment vs comparison.** `=` *puts a value in a box*; `==` *asks a question*. Mixing them up is a top beginner bug.
```java
int mode = 2;
if (mode = 3) {           // ← WRONG: this ASSIGNS 3, it doesn't compare
    System.out.println("mode three");
}
```
```
error: incompatible types: int cannot be converted to boolean
```
Java expects a `boolean` inside `if(...)`, but `mode = 3` produces an `int`. Fix: `if (mode == 3)`. *(With `boolean` variables this is nastier: `if (armUp = true)` compiles and silently forces `armUp` to `true` every time — so just write `if (armUp)`.)*

**2. Comparing Strings with `==` (again!).** Same trap as Phase 3, and it bites hardest inside `if`:
```java
if (cmd == "go") { ... }        // compares identity, not text -> usually false
if (cmd.equals("go")) { ... }   // correct
```

**3. A stray semicolon after `if`.** This one compiles and ruins your day:
```java
if (score >= 70);               // ← the ; ENDS the if with an empty body
    System.out.println("passed");   // this ALWAYS runs, regardless of score
```
No error — the print just always happens. Delete the semicolon.

**4. `switch` with no `break` falls through.** Without `break`, Java keeps running the *next* case too:
```java
switch (mode) {
    case 1: System.out.println("one");   // no break!
    case 2: System.out.println("two");   // if mode==1, BOTH print
}
```
Add `break;` after each case (or, later, you'll see the arrow form that doesn't fall through).

## 🔍 Decode this error
```
error: incompatible types: int cannot be converted to boolean
    if (mode = 3) {
            ^
```
- `incompatible types` → Java got the wrong kind of value somewhere.
- `int cannot be converted to boolean` → an `if` needs a `boolean` (a yes/no), but it received an `int`.
- The `^` points right at `mode = 3` — a single `=`, which *assigns* and yields an `int`.
- **Fix:** use `==` to compare: `if (mode == 3)`. Whenever you see "cannot be converted to boolean" inside an `if`, suspect a `=` that should be `==`.

## 🤖 On the Robot (FTC)
Decisions are how a robot reacts to its driver and sensors. The canonical one: *if a button is pressed, do a thing.*
```java
if (gamepad1.a) {              // A held? (a boolean)
    claw.setPosition(0.0);     // open the claw
} else {
    claw.setPosition(1.0);     // otherwise keep it closed
}
```
And the deadzone check from the example, in real form:
```java
double drive = -gamepad1.left_stick_y;   // remember: Y is inverted
if (Math.abs(drive) < 0.05) {
    drive = 0.0;                          // ignore tiny wiggle so we don't creep
}
leftMotor.setPower(drive);
```
These run *every loop tick* (Phase 3's polling model), so the robot re-decides ~50 times a second. *(You'll wire this into a real control loop in Phase 5 and to real hardware in Phase 8.)*

## 📖 Read a real OpMode
You don't need a robot to *read* code. Here's a snippet straight out of a typical TeleOp — predict what it does before reading the comments:
```java
// inside the control loop:
if (gamepad1.right_bumper) {
    intake.setPower(1.0);        // bumper held -> run intake in
} else if (gamepad1.left_bumper) {
    intake.setPower(-1.0);       // other bumper -> spit out
} else {
    intake.setPower(0.0);        // neither -> stop
}
```
**Read it like this:** the three branches are checked top to bottom, only one runs, and because it's polled every tick, *letting go* of both bumpers falls to the `else` and stops the intake. That "release = stop" behavior is entirely due to the `else`. This is the exact `if / else if / else` you practiced above — real OpModes are mostly ideas you already know, stacked up.

## Self-check quiz
1. What's the difference between `=` and `==`?
2. In `if / else if / else`, if two conditions are both true, which block runs?
3. Why does `if (cmd == "go")` usually fail, and what's the fix?
4. What does `Math.abs(-0.03) < 0.05` evaluate to, and why is that useful for a joystick?
5. What happens in a `switch` if you forget `break;`?

<details><summary>Answers</summary>

1. `=` assigns a value to a variable; `==` compares two values and produces a `boolean`. An `if` needs `==`.
2. The **first** true one (top to bottom); the rest are skipped.
3. `==` compares object identity, not text, so it's usually false. Use `cmd.equals("go")`.
4. `true` (0.03 < 0.05). It lets a deadzone check treat a stick that's barely off-center as zero, so the robot doesn't creep.
5. Execution "falls through" and runs the following case(s) too, until it hits a `break` or the end of the switch.
</details>

## Now do it
1. Run both examples. In `Decisions.java`, change `score` and `mode` and predict each result first.
2. Open [`exercises/RockPaperScissors.java`](exercises/RockPaperScissors.java) and follow the TODOs — your tangible win is a playable Rock-Paper-Scissors game.
3. Do [`CHECKPOINT.md`](CHECKPOINT.md) before moving to Phase 5.
