# Phase 5 — Loops

> **Prereqs:** Phase 4 (Decisions) done — you're comfortable with `if/else` and booleans (`&&`, `||`, `!`).

## The Big Idea: do the same thing many times, without repeating yourself

A **loop** runs a block of code over and over. Instead of copy-pasting `System.out.println` a hundred times, you write it once and tell Java "keep doing this." A loop needs three things:

1. a **start** (where you begin),
2. a **condition** (keep going while this is true),
3. a **change** (something that moves you toward stopping).

Why it matters for a robot: a robot program is *one giant loop*. It reads the gamepad, decides what to do, sets motor power, and does it all again — dozens of times a second, for the whole match. If you understand loops, you understand the shape of every OpMode you'll ever write.

## Mental model: a loop is a checkpoint you keep returning to

Picture a running track with a sign at the start line:

```
        ┌─────────────────────────────┐
        ▼                             │
   [ check condition ] ── false ──► exit the loop
        │ true                       │
        ▼                            │
   [ run the loop body ]             │
        │                            │
   [ do the change (i++) ] ──────────┘   (go back and check again)
```

Every time around, Java checks the condition **first**. If it's true, it runs the body once, then loops back to the check. If it's false, it skips out. If the change never makes the condition false, the loop runs forever — an **infinite loop**.

## Worked examples

### The three loop shapes — [`examples/Loops.java`](examples/Loops.java)

```java
// for: use it when you KNOW how many times.
for (int i = 0; i < 5; i++) {     // i = 0,1,2,3,4  (five passes)
    System.out.println("i = " + i);
}

// while: use it when you loop UNTIL something changes.
int countdown = 3;
while (countdown > 0) {
    System.out.println(countdown);
    countdown--;                  // the change! forget it and it loops forever
}

// do-while: runs the body AT LEAST ONCE, THEN checks the condition.
int n = 10;
do {
    System.out.println("ran once");
} while (n < 5);                  // 10 < 5 is false, so it stops after one pass
```

Read the three parts of the `for` header out loud: **"start at i = 0; keep going while i < 5; add 1 to i each time."** That semicolon-separated header is the whole loop in one line.

### `break` and `continue`

```java
for (int i = 0; i < 100; i++) {
    if (i == 3) break;            // leave the loop completely
    System.out.println(i);        // prints 0, 1, 2
}

for (int i = 0; i < 6; i++) {
    if (i % 2 == 0) continue;     // skip the rest of THIS pass, go to next i
    System.out.println(i);        // prints 1, 3, 5 (odds only)
}
```

- **`break`** = "I'm done, get me out of this loop now."
- **`continue`** = "skip the rest of this one pass, jump to the next."

### Nested loops — [`examples/TimesTable.java`](examples/TimesTable.java)

A loop can live inside another loop. The **inner** loop runs completely for *each* pass of the **outer** loop:

```java
for (int row = 1; row <= 3; row++) {        // outer: 3 rows
    for (int col = 1; col <= 3; col++) {    // inner: runs fully for each row
        System.out.print(row * col + "\t");
    }
    System.out.println();                    // end the row
}
```

The inner body runs `3 × 3 = 9` times. Nested loops **multiply** — a 1000×1000 nested loop is a million passes. Handy, but they get expensive fast.

## ⚠️ Common Traps (with the real errors)

**1. The off-by-one / fencepost error (`<` vs `<=`).** This is *the* classic loop bug. If you want 5 passes for `i` starting at 0, you need `i < 5`, not `i <= 5`:

```java
for (int i = 0; i <= 5; i++) {   // BUG: this runs 6 times (0,1,2,3,4,5)
    System.out.println(i);
}
```
It's called a "fencepost" error: to build a fence with 5 sections you need 6 posts. Counting sections vs posts is where people slip. **Rule of thumb:** start at `0` and use `<`, OR start at `1` and use `<=`. Pick one and be consistent.

**2. Off-by-one that crashes: `ArrayIndexOutOfBoundsException` (preview).** You'll meet arrays in Phase 7, but here's why `<=` bites hard. An array of 3 items has valid positions `0, 1, 2` — the last valid position is `length - 1`, not `length`:

```java
int[] scores = {10, 20, 30};       // positions 0, 1, 2
for (int i = 0; i <= scores.length; i++) {   // BUG: i reaches 3
    System.out.println(scores[i]);
}
```
```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
	at ArrayDemo.main(ArrayDemo.java:5)
```
Reading it back to front: length is `3`, you asked for index `3`, but `3` is one past the end. Use `i < scores.length`.

**3. The infinite loop (forgot the change).** If nothing inside the loop moves the condition toward false, it never stops:

```java
int countdown = 3;
while (countdown > 0) {
    System.out.println(countdown);
    // forgot countdown--;  -> prints 3 forever
}
```
There's no error message — the program just hangs, printing forever. **Press `Ctrl+C` in the terminal to kill it.** If a loop "hangs," you forgot the change.

**4. Stray semicolon after the loop header.** A `;` right after `for(...)` or `while(...)` ends the loop with an *empty* body:

```java
for (int i = 0; i < 5; i++);     // <- that semicolon is the whole loop body!
    System.out.println(i);        // this runs ONCE, after the loop, and i is out of scope
```
```
error: cannot find symbol
  symbol:   variable i
```
The loop spins 5 times doing nothing, then the `println` fails because `i` only existed inside the loop.

## 🔍 Decode this error
```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
	at ArrayDemo.main(ArrayDemo.java:5)
```
- `Exception in thread "main"` → the program crashed while running (this is a *runtime* error, not a compile error).
- `ArrayIndexOutOfBoundsException` → you reached for a position that doesn't exist.
- `Index 3 out of bounds for length 3` → the collection has length 3 (positions 0–2); you asked for position 3.
- `at ArrayDemo.main(ArrayDemo.java:5)` → **exactly where**: line 5. Go there. It's almost always a loop using `<=` where it should use `<`.

## 🤖 On the Robot (FTC)
The heart of every driver-controlled OpMode is one `while` loop:

```java
waitForStart();                       // wait until the driver presses START
while (opModeIsActive()) {            // loop until the match ends or STOP is pressed
    // 1. READ inputs
    double power = -gamepad1.left_stick_y;
    // 2. DECIDE
    if (Math.abs(power) < 0.05) power = 0;   // ignore tiny stick drift (deadzone)
    // 3. SET outputs
    leftDrive.setPower(power);
    telemetry.addData("power", power);
    telemetry.update();
}
```

Notice the rhythm: **read inputs → decide → set outputs**, then loop back. This body runs *about every 20 milliseconds* — roughly 50 times a second — for the entire match. `opModeIsActive()` is the loop condition; it becomes false when the match ends, which is what stops the loop cleanly. *(You'll write real OpModes in Phase 11.)*

## 📖 Read a real OpMode

Don't run this — just read it and predict what it does. Cover the comments first and guess.

```java
waitForStart();
while (opModeIsActive()) {                      // the control loop (Phase 5!)
    double drive = -gamepad1.left_stick_y;      // read stick (Y is inverted, Phase 3)
    double turn  =  gamepad1.right_stick_x;
    leftDrive.setPower(drive - turn);           // decide + set outputs
    rightDrive.setPower(drive + turn);
    telemetry.addData("drive", drive);
    telemetry.update();                          // send to Driver Station
}
```

**Predict:** How many times does this loop run during a 2-minute match? (Roughly: ~50 times/second × 120 seconds ≈ 6,000 passes.) What single method call in the header is what eventually stops it? *(Answer: `opModeIsActive()` turning false.)*

## Self-check quiz
1. When would you use a `for` loop instead of a `while` loop?
2. What's the one thing a `do-while` loop guarantees that a `while` loop does not?
3. In `for (int i = 0; i < 5; i++)`, how many times does the body run, and what are the values of `i`?
4. What's the difference between `break` and `continue`?
5. You wrote a loop and the program "hangs" and never stops. What did you most likely forget?
6. Why is `i <= array.length` a bug when looping over an array?

<details><summary>Answers</summary>

1. Use `for` when you know the number of passes (counting); use `while` when you loop until some condition changes and you don't know how many passes that'll take.
2. `do-while` always runs its body **at least once**, because it checks the condition *after* the first pass. A `while` might run zero times.
3. It runs **5** times; `i` is `0, 1, 2, 3, 4`. (It stops when `i` becomes 5, because `5 < 5` is false.)
4. `break` exits the whole loop immediately; `continue` skips the rest of the current pass and jumps to the next one.
5. The **change** — something (like `i++` or `countdown--`) that moves the condition toward false. Without it you get an infinite loop. Kill it with `Ctrl+C`.
6. Valid positions are `0` to `length - 1`. `i <= length` lets `i` reach `length`, which is one past the end — that throws `ArrayIndexOutOfBoundsException`. Use `i < array.length`.
</details>

## Now do it
1. Run both examples. Change the counts and the `<` to `<=` on purpose and watch the off-by-one.
2. Build the number-guessing game: open [`exercises/GuessingGame.java`](exercises/GuessingGame.java) and follow the TODOs. This is your tangible win for this phase. 🎯
3. Do [`CHECKPOINT.md`](CHECKPOINT.md) before moving to Phase 6.
