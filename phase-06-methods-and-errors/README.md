# Phase 6 — Methods & Errors

> **Prereqs:** Phase 5 (Loops) done. You can write `for`/`while` loops and you remember `.equals()` vs `==` for Strings from Phase 3.

## The Big Idea: name a chunk of work once, then reuse it

A **method** is a named block of code you can run whenever you want, as many times as you want. You give it inputs (**parameters**), it does its job, and it can hand back a result (a **return value**). Methods let you break a big scary program into small pieces you can name, test, and trust.

The second half of this phase is the flip side of writing code: what happens when it **goes wrong**. An **exception** is Java's way of saying "I hit a problem I can't handle here." Learning to *read* an exception — the message and the **stack trace** — is the single most useful debugging skill you'll build. It's the difference between "it crashed, I give up" and "line 19, the motor is null, I forgot to initialize it."

## Mental model: a method is a kitchen appliance

A method is like a blender. You put ingredients **in** (the parameters), it does one job, and something comes **out** (the return value):

```
   add(2, 3)                          returns 5
   ─────────►  ┌───────────────────┐  ─────────►
   parameters  │  int add(a, b) {  │  return value
   (the inputs)│    return a + b;  │
               └───────────────────┘
```

You don't need to know *how* the blender works to use it — you just need to know what goes in and what comes out. That's the whole point: once `add` works, you stop thinking about it and just call it.

An **exception** is what happens when you put a fork in the blender. It stops, and it hands you a report (the stack trace) saying exactly where and why it jammed.

## Worked examples

### Methods: parameters, return, scope, overloading — [`examples/Methods.java`](examples/Methods.java)

```java
static int add(int a, int b) {        // two parameters, returns an int
    return a + b;
}

static double add(double a, double b) {   // OVERLOADING: same name, different types
    return a + b;
}

static void greet(String name) {      // "void" = returns nothing, just acts
    System.out.println("Hi, " + name + "!");
}

static int square(int x) {
    int result = x * x;               // 'result' is LOCAL — it only exists in here
    return result;
}
```

- **Parameters** are the inputs listed in the `(...)`. **Arguments** are the actual values you pass when you call it.
- **`return`** hands a value back and ends the method immediately.
- **Scope**: a variable declared inside a method (like `result`) exists *only* inside that method. Ask for it outside and you get `cannot find symbol`.
- **Overloading**: two methods can share a name if their parameter lists differ. Java picks based on what you pass — `add(2, 3)` uses the `int` one, `add(2.5, 0.5)` uses the `double` one.

### Our shared Log class — [`examples/Log.java`](examples/Log.java)

Throughout the rest of this course we'll use a tiny logging helper. It's about 15 lines and it's just a friendly wrapper over `System.out`:

```java
public class Log {
    public static void log(String level, String message) {
        System.out.println("[" + level + "] " + message);
    }
    public static void info(String message)  { log("INFO",  message); }
    public static void warn(String message)  { log("WARN",  message); }
    public static void error(String message) { log("ERROR", message); }
}
```

Call it like `Log.info("Robot initialized");` and it prints `[INFO] Robot initialized`. Notice the shape: a **label/level** plus a **message** — that's exactly the shape of FTC's `telemetry.addData("label", value)`. Same idea, and you built it yourself.

### try/catch: handling an exception instead of crashing

```java
String text = "banana";
try {
    int number = Integer.parseInt(text);   // throws NumberFormatException
    System.out.println(number);            // skipped when it throws
} catch (NumberFormatException e) {
    System.out.println("That wasn't a number.");   // we land here instead
}
```

`try` runs risky code; if it throws, control jumps to the matching `catch`, and the program **keeps running** instead of dying. `e` is the exception object — you can print it or ask it questions.

## ⚠️ Common Traps (with the real errors)

**1. `NullPointerException` — the #1 FTC crash.** `null` means "this variable points at nothing." Calling a method on nothing crashes. This happens constantly on a robot when you *declare* a motor but forget to *get* it from the hardware map (or the name in code doesn't match the robot's configuration):

```java
DcMotor leftDrive;              // declared, but never assigned -> it's null
leftDrive.setPower(0.5);        // BOOM: calling a method on null
```
```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "com.qualcomm.robotcore.hardware.DcMotor.setPower(double)" because "this.leftDrive" is null
	at MyOpMode.runOpMode(MyOpMode.java:19)
```
Java 21's message is unusually kind here: it literally tells you the variable — `"this.leftDrive" is null` — and the method you tried to call. The fix on a robot: `leftDrive = hardwareMap.get(DcMotor.class, "left_drive");` **before** you use it, and make sure `"left_drive"` matches the robot config exactly.

**2. Method must return the type it promised.** If a method says `int` but a path through it returns nothing:

```java
static int biggest(int a, int b) {
    if (a > b) return a;
    // forgot the else / the final return
}
```
```
error: missing return statement
```

**3. Wrong argument type.** Passing a `String` where an `int` is expected:

```java
add("2", 3);
```
```
error: incompatible types: String cannot be converted to int
```

**4. Using a local variable out of scope.** A variable born inside a method (or loop, or `if`) dies at the closing `}`:

```java
static int square(int x) { int result = x * x; return result; }
// ...somewhere in main:
System.out.println(result);     // result doesn't exist here
```
```
error: cannot find symbol
  symbol:   variable result
```

## 🔍 Decode this error — reading a stack trace

A **stack trace** is the trail of method calls that led to the crash. Read it like this: the **top line** says *what* went wrong; the indented `at` lines are *where* (newest call first); a `Caused by:` line, if present, is the **real root cause**.

```
Exception in thread "main" java.lang.RuntimeException: Failed to initialize drivetrain
	at Robot.init(Robot.java:22)
	at Robot.main(Robot.java:9)
Caused by: java.lang.NullPointerException: Cannot invoke "DcMotor.setPower(double)" because "this.leftDrive" is null
	at Robot.init(Robot.java:19)
	... 1 more
```

How to read it, in order:
1. **Top line:** a `RuntimeException` with the message "Failed to initialize drivetrain." That's the *symptom*.
2. **`at Robot.init(Robot.java:22)`** → it blew up inside `init()`, at **line 22** of `Robot.java`. The line below (`main`, line 9) is who called `init`.
3. **`Caused by:` is the gold.** The *actual* cause is a `NullPointerException` at **Robot.java:19** — `leftDrive` is null. Jump to line 19 first. The `Caused by` is almost always where the real bug lives.
4. **`... 1 more`** just means "the rest of the trace is the same as above; I trimmed it."

**The habit:** find the highest line that names *your* file and *your* line number, go there, and read the message. Ignore the deep library lines for now.

## 🤖 On the Robot (FTC)
Methods keep an OpMode readable. Instead of one giant blob, teams write small helpers:

```java
// Helper methods — named jobs you call from the loop.
private void drive(double power) {
    leftDrive.setPower(power);
    rightDrive.setPower(power);
}
private void turn(double power) {
    leftDrive.setPower(power);
    rightDrive.setPower(-power);   // opposite sides -> spin in place
}
```

And there's a required keyword you'll see on every `LinearOpMode`:

```java
@Override
public void runOpMode() throws InterruptedException {   // <- note the "throws"
    // ...
    waitForStart();
    while (opModeIsActive()) { drive(0.5); }
}
```

`throws InterruptedException` is a promise: "this method might be interrupted (e.g. the match is force-stopped), and I'm not handling that here — the FTC framework will." You don't catch it; you just declare it, because methods like `waitForStart()` and `sleep()` can throw it. *(You'll write real helper methods in Phase 8+.)*

## 📖 Read a real OpMode

Read this; don't run it. Notice the method call and the `throws`.

```java
@Override
public void runOpMode() throws InterruptedException {   // declares it may be interrupted
    leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");   // initialize! (or NPE later)
    rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

    telemetry.addData("Status", "Initialized");
    telemetry.update();

    waitForStart();                       // can throw InterruptedException
    while (opModeIsActive()) {
        double power = -gamepad1.left_stick_y;
        setDrivePower(power, power);      // a helper METHOD keeps the loop readable
        telemetry.update();
    }
}

private void setDrivePower(double left, double right) {   // params + void return
    leftDrive.setPower(left);
    rightDrive.setPower(right);
}
```

**Predict:** If someone deleted the line `leftDrive = hardwareMap.get(...)`, which line would crash, and with what exception? *(Answer: `setDrivePower` → `leftDrive.setPower(...)` → `NullPointerException`, because `leftDrive` was never initialized.)*

## Self-check quiz
1. What's the difference between a **parameter** and a **return value**?
2. What does a `void` method return?
3. Two methods are both named `add`. How can that be legal, and how does Java tell them apart?
4. What does `null` mean, and what exception do you get for calling a method on it?
5. In a stack trace, what does the `Caused by:` line tell you, and why is it the first thing to read?
6. What does `throws InterruptedException` on `runOpMode()` mean — are you handling the exception?

<details><summary>Answers</summary>

1. A **parameter** is an input the method receives; a **return value** is the result it hands back to the caller.
2. Nothing. `void` means the method does its work but returns no value.
3. It's **overloading** — legal because their parameter lists differ. Java picks the version whose parameters match the arguments you pass.
4. `null` means the variable points at no object. Calling a method on it throws `NullPointerException`.
5. `Caused by:` names the **root cause** exception and the line where it originated — that's usually the actual bug, so read it first (before the higher-level symptom message).
6. It declares that the method *may* throw `InterruptedException` and is **not** handling it here — it passes responsibility up to the FTC framework. You're not catching it; you're just declaring the possibility.
</details>

## Now do it
1. Run both examples. In `Methods.java`, uncomment the out-of-scope `result` line and read the `cannot find symbol` error.
2. Build the capstone: open [`exercises/MatchScorer.java`](exercises/MatchScorer.java) and follow the TODOs. This is your Phase 6 tangible win. 🏆
3. Do [`CHECKPOINT.md`](CHECKPOINT.md) before moving to Phase 7.
