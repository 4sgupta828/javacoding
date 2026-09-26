# Phase 1 — Getting Started & Output

> **Prereqs:** [`docs/getting-started.md`](../docs/getting-started.md) done — `java -version` prints Java 21.

## The Big Idea: a program is a list of instructions, run in order

A computer does exactly what you tell it, one step at a time, top to bottom. Programming is writing those steps precisely enough that a machine can follow them. Your first job is just to make the computer **say something**.

Why start with output? Because you can't debug what you can't see. On a robot, *seeing what the code thinks* (via telemetry) is how you figure out why it's misbehaving. Printing is your flashlight.

## Mental model: source → bytecode → JVM

You write **source code** (`Hello.java`, plain text). Java can't run that directly. It goes through two stages:

```
   Hello.java          Hello.class          running program
  (source, you   ──►   (bytecode, the  ──►  (the JVM executes
   write this)          compiler makes)      the bytecode)
       │                     │                    │
     javac  ───────────────►                      │
                        java  ─────────────────────►
```

- **`javac`** = the *compiler*. It translates your text into **bytecode**.
- **`java`** = the *runtime* (the JVM). It runs the bytecode.

Java 21 lets you skip the visible compile step with `java Hello.java` — but the compile still happens behind the scenes. You'll see both ways below so the idea is concrete.

## Your first program

Look at [`examples/Hello.java`](examples/Hello.java):

```java
public class Hello {                 // every Java file has a class; its name matches the file
    public static void main(String[] args) {   // main = where the program starts
        System.out.println("Hello, robot!");    // print one line
    }
}
```

Run it (from inside `phase-01-getting-started/examples/`):

```bash
java Hello.java
```

Output:
```
Hello, robot!
```

### Do the explicit compile once (so "compile" is real)
```bash
javac Hello.java     # look: this creates a new file, Hello.class
ls                    # you'll see Hello.class appear — that's the bytecode
java Hello            # run the class (note: no .java, no .class)
```
That `Hello.class` is the bytecode from the diagram. After this once, just use `java Hello.java`.

## `println` vs `print`
- `System.out.println("x")` — prints `x` **and** moves to a new line.
- `System.out.print("x")` — prints `x` with **no** new line.

See [`examples/Output.java`](examples/Output.java) for the difference and for `\n` (newline) and `\t` (tab).

## Comments (notes to humans, ignored by Java)
```java
// this is a single-line comment
/* this is a
   multi-line comment */
```
Comments explain *why*. Use them.

## What are those top lines in real code? (`package` / `import`)
Real files — including every FTC OpMode — start with lines like:
```java
package org.firstinspires.ftc.teamcode;      // which folder/namespace this class lives in
import com.qualcomm.robotcore.hardware.DcMotor;  // "let me use the DcMotor class from over there"
```
You don't need these for tiny single-file programs yet. Just know: **`package` = the class's address; `import` = borrowing a class from somewhere else.** You'll use them for real in Phase 8.

## ⚠️ Common Traps (with the real errors)

**1. File name must match the public class name.**
If your class is `public class Hello` the file must be `Hello.java`. Otherwise:
```
error: class Hello is public, should be declared in a file named Hello.java
```

**2. Missing semicolon.** Every statement ends in `;`.
```java
System.out.println("hi")   // ← forgot the ;
```
```
error: ';' expected
```

**3. Capitalization matters.** Java is case-sensitive. `system.out.println` (lowercase s) fails:
```
error: cannot find symbol
  symbol:   variable system
```

**4. Smart quotes.** If you copy from a doc, `"` might become `"` `"` (curly quotes). Java only accepts straight `"`. TextEdit loves to do this — use VS Code.

## 🔍 Decode this error
```
error: cannot find symbol
  symbol:   method printine(String)
  location: variable out of type PrintStream
```
- `cannot find symbol` → Java doesn't recognize a name you used.
- `method printine(String)` → the name it can't find is `printine` — a **typo** for `println`.
- Fix the spelling. *"cannot find symbol" almost always means a typo or a missing import.*

## 🤖 On the Robot (FTC)
On a robot you don't have a console, so `System.out.println` mostly goes to a hidden log ("logcat"), **not** the driver's screen. Instead FTC gives you a **telemetry object**:
```java
telemetry.addData("Status", "Running");   // queue a label + value
telemetry.update();                         // ← actually send it to the Driver Station
```
Two things to notice now (they'll matter later):
1. `telemetry` is an **object** you call methods on — not a plain `println`.
2. Nothing shows up until you call `telemetry.update()`. Forgetting `.update()` is a top-5 beginner bug. *(You'll write real telemetry in Phase 11.)*

## Self-check quiz
1. What's the difference between `javac` and `java`?
2. Why must a file with `public class Robot` be named `Robot.java`?
3. What does `System.out.print` do differently from `System.out.println`?
4. On a robot, why won't `System.out.println` show up for the driver?

<details><summary>Answers</summary>

1. `javac` compiles source → bytecode (`.class`); `java` runs the bytecode on the JVM.
2. Java requires a public class to live in a file matching its name; the compiler enforces it.
3. `print` stays on the same line; `println` adds a newline after.
4. It goes to the log (logcat), not the Driver Station; you use `telemetry.addData/update` for the driver.
</details>

## Now do it
1. Run both examples. Change the text. Break something on purpose (delete a `;`) and read the error.
2. Open [`exercises/Banner.java`](exercises/Banner.java) and follow the TODOs.
3. Do [`CHECKPOINT.md`](CHECKPOINT.md) before moving to Phase 2.
