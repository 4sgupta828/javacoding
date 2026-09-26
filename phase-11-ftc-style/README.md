# Phase 11 — Putting It Together (FTC-Style)

> **Prereqs:** Phases 1–10 done. This is the final phase — it *integrates* everything and ends in the capstone.

## The Big Idea: real OpModes are just the pieces you already know, stacked

You've spent ten phases collecting parts: output, variables, the loop model, decisions, loops, methods, exceptions, collections, classes, inheritance, interfaces, enums, annotations. A real FTC OpMode is **all of those at once** — which is exactly why it looks scary. It isn't new material; it's *assembly*.

This phase does three things:
1. **Reads a real OpMode line by line** and labels which phase taught each piece.
2. Teaches the two **shapes** an OpMode comes in (`LinearOpMode` vs iterative) and the **state machine** pattern that runs most autonomous routines.
3. Ends in a **capstone**: you build a working, *simulated* robot in plain Java, then go read a real OpMode from GitHub and explain every line.

## Mental model: an OpMode is "set up → wait → loop: read, decide, act"

```
   ┌─────────────┐   ┌────────────┐   ┌──────────────────────────────┐
   │  set up     │──►│ wait for   │──►│  loop, every ~20 ms:         │
   │  hardware   │   │  START     │   │   read inputs → decide → act │◄─┐
   └─────────────┘   └────────────┘   └───────────────┬──────────────┘  │
     Phase 8            Phase 5            Phase 3 ────┘   still active? ─┘
```

Every OpMode is that shape. Find the shape first; the details fill in after.

## ANATOMY OF A REAL OPMODE (line-by-line)

Here's the skeleton from [`docs/reading-real-code.md`](../docs/reading-real-code.md). By now you can name every piece:

```java
@TeleOp                                    // Phase 10 — ANNOTATION: "app, list me as driver-controlled"
public class MyOp extends LinearOpMode {   // Phase 9 — INHERITANCE: MyOp IS-A LinearOpMode
    private DcMotor leftDrive;             // Phase 8 — a FIELD holding an object reference;
                                           //           Phase 8 — `private` is an ACCESS MODIFIER

    @Override                              // Phases 9–10 — ANNOTATION: "I'm replacing the parent's method"
    public void runOpMode() {              // Phase 6 — a METHOD; the one you must write for a LinearOpMode
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
                                           // Phase 8 — look hardware up BY NAME; returns an OBJECT
        waitForStart();                    // Phase 5/6 — BLOCKS here until the driver presses START
        while (opModeIsActive()) {         // Phase 5 — the CONTROL LOOP
            leftDrive.setPower(-gamepad1.left_stick_y);
                                           // Phase 3 — POLL the gamepad, set the motor (Y is inverted!)
            telemetry.addData("pos", leftDrive.getCurrentPosition());
                                           // Phase 1/8 — QUEUE a label + value on an object
            telemetry.update();            // Phase 1 — actually SEND it to the Driver Station
        }
    }
}
```

Nothing above is new. That's the whole point: you already learned all of it.

## LinearOpMode vs iterative OpMode (two mental models, side by side)

FTC gives you two shapes. They do the same job; they just hand you the loop differently.

| | **`LinearOpMode`** (a script) | **iterative `OpMode`** (callbacks) |
|---|---|---|
| You write | one method: `runOpMode()` | several methods: `init()`, `init_loop()`, `start()`, `loop()`, `stop()` |
| The loop | **you** write it: `while (opModeIsActive()) { ... }` | the **system** calls your `loop()` over and over |
| Waiting for START | you call `waitForStart();` | the system waits, then calls `start()` |
| Reads like | a recipe, top to bottom | filling in blanks someone else calls |
| Best when | you think "do this, then this, then loop" | you think "here's what to do each tick" |

```
LinearOpMode (you drive the loop)          Iterative OpMode (the app drives it)
────────────────────────────────          ───────────────────────────────────
runOpMode() {                              init()        ← app calls once
    setup...                               init_loop()   ← app calls while waiting
    waitForStart();                        start()       ← app calls when START pressed
    while (opModeIsActive()) {             loop()        ← app calls again and again
        read; decide; act;                 stop()        ← app calls at the end
    }
}
```

Run both mental models as plain Java: [`examples/TwoOpModeStyles.java`](examples/TwoOpModeStyles.java).

```bash
java TwoOpModeStyles.java
```

## State machines for autonomous (enum + switch + timer)

Autonomous can't just be a straight script when each step takes *time* ("drive for 2 seconds, then turn"). The clean pattern: an **enum** of steps, a **switch** that acts on the current step, and a **timer** that decides when to move on.

[`examples/AutoStateMachine.java`](examples/AutoStateMachine.java) is a runnable robot that drives forward 2s, turns 1s, then stops — printing telemetry each loop:

```bash
java AutoStateMachine.java
```

```java
enum AutoState { DRIVE_FORWARD, TURN, STOP, DONE }
// each loop:  1) switch on state -> set motor powers
//             2) print telemetry
//             3) if this state's time is up -> move to the next state
```

On a real robot the fake timer becomes FTC's `ElapsedTime` (Phase 8) and the `println` becomes `telemetry`. The *logic* is identical.

## Telemetry vs logging (a reused helper + an important distinction)

[`examples/TelemetryDemo.java`](examples/TelemetryDemo.java) reuses the Phase-6 `Log` helper next to a telemetry-style `addData`/`update`, because **they are two different jobs**:

- **Telemetry** is for the **driver**, **right now**, on the Driver Station screen. It's transient — it clears every `update()`.
- **Logging** is for **you**, the programmer, as a **permanent record** to read *after* the match to debug.

In real FTC, telemetry is `telemetry.addData/update`; real logging has options like **`java.util.logging`**, **SLF4J**, or FTC's own **`RobotLog`**. Don't confuse them: `telemetry ≠ logging`.

```bash
java TelemetryDemo.java
```

## ⚠️ A Java-21-vs-FTC language caveat (read this before you copy code from the web)

You learned on **Java 21**. FTC's toolchain targets an **older** language level. The durable core you know — classes, methods, loops, `if`/`switch` *statements*, `var`, interfaces, enums, generics *use* — is exactly what FTC uses. But a few *modern* conveniences you may see in online examples **won't appear in — or may not even compile in —** FTC code:

| Modern feature (avoid in FTC) | Safe FTC alternative you already know |
|---|---|
| `record Point(int x, int y) {}` | a normal class with fields + a constructor (Phase 8) |
| text blocks `"""..."""` | ordinary strings with `\n` (Phase 1) |
| `sealed` classes / interfaces | a plain `interface` or `abstract class` (Phases 9–10) |
| `switch` **expression** `case X -> value;` | the classic `switch` **statement** with `case X:` + `break:` (Phase 4) |
| pattern matching in `switch` | an `if`/`instanceof` chain |

Rule of thumb: if it has `->` inside a `switch`, or the words `record`/`sealed`/`"""`, treat it as "nice, but maybe not for the robot." The examples in this phase deliberately use only the safe core.

## 🔍 Decode this error
```
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "DcMotor.setPower(double)" because "this.leftDrive" is null
	at MyOp.runOpMode(MyOp.java:12)
```
- `NullPointerException` (Phase 6) → you used an object that was never set up.
- `because "this.leftDrive" is null` → `leftDrive` is `null` — you never assigned it.
- The cause on a real robot: you skipped `leftDrive = hardwareMap.get(DcMotor.class, "left_drive");`, or the name `"left_drive"` doesn't match the robot config. **Fix:** set the field from `hardwareMap` before using it, and check the config name exactly. *This is the #1 FTC crash.*

## 🤖 On the Robot (FTC)
This whole phase *is* the robot. The capstone below is the closest you can get to a real OpMode without a robot: fake `Gamepad`, fake `DcMotor`, an `init`/`loop` shape, and a state-machine autonomous — all in runnable plain Java. When you later open Android Studio or OnBot Java, you'll swap the fakes for the real `hardwareMap` objects and the code shape won't change.

## 📖 Read a real OpMode (this is half the capstone)
Open **`BasicOpMode_Linear.java`** from the FtcRobotController samples on GitHub (see [`docs/reading-real-code.md`](../docs/reading-real-code.md)). Read it top to bottom and, for **each line**, name the phase that taught it — annotation (10), `extends` (9), fields/`hardwareMap` (8), the loop (5), gamepad polling (3), telemetry (1). Anything you can't name is a great question to write down.

## Self-check quiz
1. In a `LinearOpMode`, who writes the control loop — you, or the system? What about an iterative `OpMode`?
2. In the anatomy skeleton, which line *actually sends* data to the driver, and which just queues it?
3. Why is `while (opModeIsActive())` used instead of `while (true)`?
4. Name the three ingredients of the autonomous state-machine pattern.
5. You copy `switch (state) { case UP -> 300; }` from a blog and it won't compile in FTC. What modern feature is that, and what's the safe rewrite?
6. Telemetry vs logging: which one is for the driver during the match, and which is a record for you to debug later?

<details><summary>Answers</summary>

1. `LinearOpMode`: **you** write the loop (`while (opModeIsActive())`). Iterative `OpMode`: the **system** calls your `loop()` repeatedly — you don't write the loop.
2. `telemetry.update();` sends it; `telemetry.addData(...)` only queues it. Forgetting `.update()` means the driver sees nothing.
3. `opModeIsActive()` becomes false when the driver presses STOP (or the match ends), so the robot stops cleanly. `while (true)` would never let go.
4. An **enum** of states, a **switch** that acts on the current state, and a **timer** (`ElapsedTime`) that decides when to advance.
5. It's a `switch` **expression** (`case X -> value`), a modern feature that may not compile in FTC. Rewrite with the classic statement: `switch (state) { case UP: return 300; ... }`.
6. Telemetry is for the driver during the match (transient, on the Driver Station). Logging is a permanent record for you to read afterward (`java.util.logging`, SLF4J, `RobotLog`).
</details>

## Now do it — the capstone is [`CHECKPOINT.md`](CHECKPOINT.md)
1. Run all three examples and predict each one's output before you do.
2. Finish [`exercises/RobotSim.java`](exercises/RobotSim.java) (the simulated robot). Check against [`exercises/solutions/RobotSim.java`](exercises/solutions/RobotSim.java).
3. Do the capstone in [`CHECKPOINT.md`](CHECKPOINT.md): complete the simulation **and** read/explain a real OpMode. 🎉🎉
