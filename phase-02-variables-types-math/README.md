# Phase 2 — Variables, Types & Math

> **Prereqs:** Phase 1 done — you can write, run, and read errors from a tiny `.java` file.

## The Big Idea: programs remember values, and every value has a type

A program isn't much use if it can't *remember* things: a score, a motor's speed, a robot's name. A **variable** is a named place to store a value so you can use it later. And in Java every value has a **type** — a promise about *what kind* of thing it is (a whole number? a decimal? text? true/false?).

Why does type matter so much? Because Java uses the type to decide how math works. As you'll see, `1 / 2` gives `0` when both sides are whole numbers — a bug that has cost real FTC teams real matches. Understanding types is how you avoid it.

## Mental model: a variable = a labeled box

Picture a shelf of boxes. Each box has a **label** (the name), holds **one value**, and is a **fixed size and shape** (the type — an `int` box can't hold text).

```
   ┌──────────┐   ┌──────────┐   ┌──────────┐
   │  ticks   │   │  power   │   │  armUp   │
   │  1120    │   │  0.75    │   │  true    │
   └──────────┘   └──────────┘   └──────────┘
     int             double         boolean
```

When you write `int ticks = 1120;` you are (1) making a box, (2) labeling it `ticks`, (3) declaring it holds `int`s, and (4) putting `1120` in it. Later `ticks = 500;` swaps the value; `ticks = 3.5;` is an **error** — wrong shape for the box.

### The four types you'll use constantly (plus one to just recognize)
| Type | Holds | Example | Quotes? |
|------|-------|---------|---------|
| `int` | whole numbers | `1120`, `-4`, `0` | none |
| `double` | decimals | `0.75`, `-1.0`, `3.14` | none |
| `boolean` | true / false | `true`, `false` | none |
| `String` | text | `"Servo"`, `"go!"` | **double** `"..."` |
| `char` | one character | `'A'`, `'x'` | **single** `'...'` |

You'll rarely reach for `char` — just recognize it (single quotes, one character). The workhorses are the top four.

### Experiment instantly with `jshell`
You don't even need a file to try math and types. In your terminal, type `jshell` and press Enter, then type expressions:
```
jshell> 7 / 2
$1 ==> 3
jshell> 7 / 2.0
$2 ==> 3.5
jshell> int x = 5
x ==> 5
```
`jshell` prints the answer *and* the type of every line. Type `/exit` to leave. Use it all through Phases 2–5 to check "wait, what does this give?" in seconds.

## Worked examples

- [`examples/Types.java`](examples/Types.java) — the four types, operators (`+ - * / %`), casting, and the integer-division trap up close.
- [`examples/MotorMath.java`](examples/MotorMath.java) — the same ideas in robot terms: `double` motor power, `int` encoder ticks, and where the trap bites.

Run them, then change the numbers and predict the output before re-running.

## ⚠️ Common Traps (with the real errors)

**1. Integer division — the big one.** If BOTH sides of `/` are `int`, Java throws away the fraction:
```java
System.out.println(1 / 2);   // prints 0   (not 0.5)
System.out.println(7 / 2);   // prints 3   (not 3.5)
```
There's no error message — just a **wrong answer**, which is worse. Fix it by making one side a `double`: `1 / 2.0`, or cast: `(double) a / b`.

**2. Casting truncates; it does not round.**
```java
int n = (int) 3.9;   // n is 3, NOT 4  — the decimal is chopped off
```

**3. Putting the wrong type in a box.**
```java
int power = 0.5;
```
```
error: incompatible types: possible lossy conversion from double to int
```
Java is warning that `0.5` would lose data as an `int`. Use `double power = 0.5;`.

**4. Using a variable before you make it.**
```java
System.out.println(speed);   // no `speed` box exists yet
```
```
error: cannot find symbol
  symbol:   variable speed
```

**5. Naming a box twice.**
```java
int ticks = 10;
int ticks = 20;   // second `int` re-declares the same name
```
```
error: variable ticks is already defined in method main(String[])
```
To change the value, just write `ticks = 20;` (no `int`).

## 🔍 Decode this error
```
error: incompatible types: possible lossy conversion from double to int
    int half = 1.0 / 2;
                   ^
```
- `incompatible types` → you tried to put one type into a box made for another.
- `from double to int` → the value on the right is a `double` (`1.0 / 2` is `0.5`), but the box is `int`.
- `possible lossy conversion` → shrinking a `double` into an `int` would lose the `.5`, so Java refuses to do it silently.
- **Fix:** either make the box a `double` (`double half = 1.0 / 2;`) or, if you really want a whole number, cast on purpose: `int half = (int) (1.0 / 2);`.

## 🤖 On the Robot (FTC)
Types are not academic on a robot — the SDK forces them on you:
```java
double power = 0.75;          // motor power is ALWAYS a double: -1.0 .. 1.0
leftMotor.setPower(power);    // hand the motor a double

int position = leftMotor.getCurrentPosition();   // encoder ticks come back as an int
```
- **Motor power is a `double`** clamped to −1.0 (full reverse) … +1.0 (full forward). `0.0` is stop.
- **Encoder "ticks" are `int`s.** Converting ticks to distance or revolutions is exactly the division from `MotorMath.java` — and forgetting to cast gives you the integer-division bug on a real drivetrain. *(You'll call `setPower`/`getCurrentPosition` for real in Phase 8.)*

## Self-check quiz
1. What does `5 / 2` print? What about `5 / 2.0`?
2. You need a variable for a motor's power of `0.6`. What type should it be, and why not `int`?
3. What does `(int) 2.99` give — `2` or `3`?
4. Why is `int result = 3 / 4;` almost certainly a bug if you wanted `0.75`?
5. On a robot, what type is a motor's power? What type is an encoder count?

<details><summary>Answers</summary>

1. `5 / 2` → `2` (integer division drops the `.5`). `5 / 2.0` → `2.5` (one side is a `double`, so the fraction is kept).
2. `double`, because `0.6` has a decimal point; an `int` box can only hold whole numbers and would reject it (`incompatible types`).
3. `2` — casting **chops** the decimal, it does not round.
4. Both `3` and `4` are `int`s, so it's integer division: `result` becomes `0`, not `0.75`. Use `3 / 4.0` or make `result` a `double`.
5. Motor power is a `double` (−1.0…1.0); an encoder count is an `int` (ticks).
</details>

## Now do it
1. Run both examples. In `jshell`, try `1120 / 1680` then `1120 / 1680.0` and notice the difference.
2. Open [`exercises/GearCalculator.java`](exercises/GearCalculator.java) and follow the TODOs — this is your tangible win: a working gear-ratio calculator.
3. Do [`CHECKPOINT.md`](CHECKPOINT.md) before moving to Phase 3.
