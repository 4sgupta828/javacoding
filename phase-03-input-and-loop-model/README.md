# Phase 3 — Data In & Out: the Robot Loop Model

> **Prereqs:** Phase 2 done — you're comfortable with variables, types, and the integer-division trap.

## The Big Idea: how a program gets data *in* — and the two ways to do it

So far your programs only spoke; now they'll **listen**. There are two fundamentally different ways a program can get input, and the difference is the single most important idea in this phase:

- **Blocking (ask-and-wait):** the program *stops* and does nothing until input arrives. This is how a console `Scanner` works — and how a robot **never** works.
- **Polling (read-every-tick):** the program loops fast, and each time around it just *reads the current value* of its inputs and moves on. This is how a robot reads its gamepad.

We'll learn console input first because it's the easy on-ramp — but we'll flag loudly that it's **scaffolding that doesn't exist on a robot**, and then build the mental model you'll actually use.

## Mental model: the phone call vs. the dashboard

- **Blocking = a phone call.** You dial, then you *wait*, holding the line, unable to do anything else until the other person answers. `in.nextLine()` is exactly this: your program freezes at that line.
- **Polling = glancing at a car dashboard.** You don't "wait for the speedometer." You *glance* at it whenever you go around your loop; it always shows its current value. Reading `gamepad1.left_stick_y` is a glance — instant, never waiting.

```
 BLOCKING (Scanner):                POLLING (robot loop):
   print prompt                       loop, ~50x/second:
   WAIT.......... (frozen)              read left_stick_y   (a glance)
   got input!                           read button a       (a glance)
   continue                             react, then loop again
```

## Worked examples

- [`examples/Greeter.java`](examples/Greeter.java) — `Scanner` input, `String` methods (`.toUpperCase()`, `.length()`), and `printf` formatting. Note the comment: **Scanner is console-only.**
- [`examples/PollingVsBlocking.java`](examples/PollingVsBlocking.java) — a plain-Java simulation of the robot loop: a `for`-loop that *reads* pretend stick/button values each tick, never waiting. This is the shape of a real OpMode.

### `printf` cheat-sheet (the fill-in-the-blanks printer)
```java
System.out.printf("Team %d ran %.2f power on %s.%n", 4828, 0.75, "left");
// %d = int    %.2f = double, 2 decimals    %s = String    %n = newline
```

## ⚠️ Common Traps (with the real errors)

**1. `==` vs `.equals()` for Strings — the classic.** For `String`s, `==` asks "are these the *exact same object in memory*?", which is almost never what you mean. To compare the *text*, use `.equals()`.
```java
Scanner in = new Scanner(System.in);
String answer = in.nextLine();     // you type: yes
if (answer == "yes") {             // ← WRONG: compares object identity, not text
    System.out.println("match!");
}                                   // prints NOTHING — the block is skipped
```
The compiler gives no error; it just silently does the wrong thing. Correct:
```java
if (answer.equals("yes")) {        // compares the actual characters
    System.out.println("match!");
}
```
(Tip: `"yes".equals(answer)` is even safer — it can't crash if `answer` is somehow empty. And `.equalsIgnoreCase("yes")` matches `YES`, `Yes`, etc.)

**2. `nextInt()` then `nextLine()` leftover-newline surprise.** After `in.nextInt()`, the Enter key is still waiting in the buffer, so the *next* `in.nextLine()` reads an empty line:
```java
int team = in.nextInt();
String name = in.nextLine();   // ← comes back EMPTY, doesn't wait
```
Fix: add an extra `in.nextLine();` after `nextInt()` to swallow the leftover newline, or read everything with `nextLine()` and convert.

**3. Type mismatch on input.** If you type `hello` when the code calls `in.nextInt()`:
```
Exception in thread "main" java.util.InputMismatchException
	at java.base/java.util.Scanner.throwFor(Scanner.java:...)
```
`nextInt()` demands digits; letters make it throw.

**4. Forgetting the import.** Using `Scanner` without `import java.util.Scanner;` at the top:
```
error: cannot find symbol
  symbol:   class Scanner
```

## 🔍 Decode this error
```
Exception in thread "main" java.util.NoSuchElementException: No line found
	at java.base/java.util.Scanner.nextLine(Scanner.java:1651)
	at Greeter.main(Greeter.java:12)
```
- `Exception in thread "main"` → a *runtime* crash (it compiled fine, then failed while running).
- `NoSuchElementException: No line found` → `nextLine()` was called but there was no input left to read (e.g. you closed the input, or ran with no keyboard attached).
- The bottom line `Greeter.main(Greeter.java:12)` → **your** code, line 12 — always read the stack trace top-down for the *what*, and find the line in *your* file for the *where*.

## 🤖 On the Robot (FTC) — the most important correction in this course
On a robot, **there is no Scanner and nothing ever waits for input.** Instead, your code runs in a fast loop, and each pass you *poll* the gamepad by reading its fields:
```java
while (opModeIsActive()) {                 // the control loop, ~50x per second
    double drive = -gamepad1.left_stick_y; // a float, -1.0 .. 1.0, Y is INVERTED (up = negative)
    boolean grab = gamepad1.a;             // a button is a boolean: pressed = true

    leftMotor.setPower(drive);             // react to whatever we just read
    if (grab) { claw.setPosition(0.0); }

    telemetry.update();                    // then loop again immediately
}
```
Notice what's **not** there: no "ask the driver," no waiting. Three facts to burn in now:
1. **Gamepad input is non-blocking polling** — you read `gamepad1.left_stick_y`, `gamepad1.a`, etc. *every tick*.
2. **Sticks are `float` in −1.0…1.0, and the Y axis is inverted** (push up → negative number), which is why you'll usually write `-gamepad1.left_stick_y`.
3. **Buttons are `boolean`** — `true` while held. *(You'll write a real control loop in Phase 5 and read hardware for real in Phase 8.)*

`Scanner` was training wheels to practice input; the loop above is the bike.

## Self-check quiz
1. Why does `if (answer == "yes")` usually fail even when the user typed `yes`? What should you use?
2. What's the difference between *blocking* and *polling*? Which does a robot use?
3. On a gamepad, what type is `left_stick_y`, and why do teams usually negate it?
4. What type is `gamepad1.a`?
5. In `printf`, what do `%d`, `%.2f`, and `%n` mean?

<details><summary>Answers</summary>

1. `==` compares object identity, not the text; two different `String` objects with the same characters can be `==` false. Use `answer.equals("yes")` (or `"yes".equalsIgnoreCase(answer)`).
2. *Blocking* stops and waits for input before continuing; *polling* reads the current value each loop pass and never waits. A robot uses **polling**.
3. `left_stick_y` is a `float` in −1.0…1.0. Its Y axis is inverted (up is negative), so teams negate it so "up = forward = positive power."
4. A `boolean` (`true` while the button is held).
5. `%d` = an `int`, `%.2f` = a `double` with 2 decimal places, `%n` = a newline.
</details>

## Now do it
1. Run [`examples/Greeter.java`](examples/Greeter.java) and type answers. Then run [`examples/PollingVsBlocking.java`](examples/PollingVsBlocking.java) and trace how `drivePower` is just `-stickY` each tick.
2. Open [`exercises/MadLibs.java`](exercises/MadLibs.java) and follow the TODOs — your tangible win is a working Mad Libs game.
3. Do [`CHECKPOINT.md`](CHECKPOINT.md) before moving to Phase 4.
