# Phase 10 — Interfaces, Enums & Annotations

> **Prereqs:** Phase 9 (Inheritance & Polymorphism) done — you can read `extends`, `super`, and `@Override`.

## The Big Idea: contracts, named states, and metadata

Three small tools that make big, real Java (like FTC code) readable:

- **Interface = a contract.** It's a list of methods a class *promises* to provide. "Anything that's a `Subsystem` promises an `update()` method." The interface doesn't say *how* — just *what must exist*.
- **Enum = a fixed set of named states.** When a value can only be one of a few things — an arm is `DOWN`, `MID`, or `UP` — an enum makes those the *only* legal choices. No typos, no "banana."
- **Annotation = metadata (a note stuck on your code).** `@Override` (you've seen it) tells the compiler "I'm replacing a parent method." `@TeleOp` / `@Autonomous` tell the FTC app "put this OpMode on the menu." Annotations don't *do* anything by themselves — something else reads them.

Why it matters: real OpModes are *covered* in these. `@TeleOp` at the top, an interface your robot classes implement, an enum for the match state. Learn to read them and a scary file turns friendly.

## Mental model

```
INTERFACE = a promise / job description        ENUM = a short list of allowed values
┌─────────────────────────────┐               ArmState:  DOWN   MID   UP
│ Subsystem (contract)         │                          └──── pick exactly one ────┘
│   • must have update()       │
└─────────────────────────────┘               ANNOTATION = a sticky note on your code
        ▲            ▲                          @Override   → "this replaces a parent method"
   implements   implements                      @TeleOp     → "app: list me under TeleOp"
    ┌────┐        ┌────┐                         (the note is read by someone else, later)
    │Arm │        │Claw│   ← each keeps the promise its OWN way
    └────┘        └────┘
```

- **Inheritance (Phase 9) shares code; an interface shares only a *promise*.** `extends` gives you a parent's actual methods. `implements` gives you a to-do list you must fill in yourself. A class can implement *many* interfaces but `extends` only one class.

## Worked examples

### 1. Interfaces as contracts → [`examples/Subsystems.java`](examples/Subsystems.java)
`Subsystem` promises `name()` and `update()`. `Arm` and `Claw` each `implements Subsystem` and fulfill the promise their own way. The payoff: we drop both into one `Subsystem[]` array and call `update()` on each without caring which is which — the contract guarantees the method exists.

```bash
java Subsystems.java
```

### 2. Enums as named states → [`examples/RobotStates.java`](examples/RobotStates.java)
`enum ArmState { DOWN, MID, UP }` plus a `switch` that maps each state to a height. Also shows an enum that carries data (`RunMode.TELEOP` "knows" it's driver-controlled) and `.values()` to loop over every state.

> **Enum + `switch` gotcha:** inside `switch (state)`, write `case DOWN:` — **not** `case ArmState.DOWN:`. The bare name is required.

### 3. A command menu (the tangible win) → [`examples/CommandMenu.java`](examples/CommandMenu.java)
An interface (`Command`) + an enum (`MenuItem`) drive a menu. This one also teaches **reading** lambdas:

```java
Command openClaw = () -> System.out.println("Claw OPEN");
```
Read `() -> { ... }` as *"a Command whose `run()` does `{ ... }`."* That arrow shape is everywhere in modern FTC libraries:
```java
button.whenPressed(() -> claw.open());   // FTCLib: "when pressed, run this little function"
```
`Runnable` is Java's built-in name for a `() -> void` function. You don't have to *write* lambdas yet — just recognize them.

## ⚠️ Common Traps (with the real errors)

**1. Forgetting to implement a promised method.** If `Arm implements Subsystem` but you never write `update()`:
```
error: Arm is not abstract and does not override abstract method update() in Subsystem
```
The contract isn't optional. Provide *every* method the interface lists.

**2. `implements` vs `extends` mix-up.** You `extends` a class but `implements` an interface:
```java
class Arm extends Subsystem { }     // ← wrong, Subsystem is an interface
```
```
error: interface expected here
```
Fix: `class Arm implements Subsystem`.

**3. Qualifying the case label in an enum switch.**
```java
switch (state) {
    case ArmState.DOWN: ...   // ← wrong
}
```
```
error: an enum switch case label must be the unqualified name of an enumeration constant
```
Fix: `case DOWN:`.

**4. Comparing an enum to a String.** Enums are their own type, not text:
```java
if (state == "DOWN") { }        // ← wrong
```
```
error: incomparable types: ArmState and String
```
Fix: `if (state == ArmState.DOWN)`. (And yes — for enums, `==` is correct and safe.)

## 🔍 Decode this error
```
error: MyClaw is not abstract and does not override abstract method update() in Subsystem
  class MyClaw implements Subsystem {
  ^
```
- `implements Subsystem` → you signed the contract.
- `does not override abstract method update()` → but you never wrote `update()`.
- `is not abstract` → and you didn't mark the class `abstract` to opt out.
- **Fix:** add the `public void update() { ... }` method the interface requires. *An interface is a promise; the compiler makes you keep it.*

## 🤖 On the Robot (FTC)

All three tools show up immediately in real OpMode code:

```java
@TeleOp(name = "Drive")                       // ANNOTATION: puts this on the app's TeleOp menu
public class Drive extends LinearOpMode { ... }

@Autonomous                                   // ANNOTATION: lists it under Autonomous instead
public class Auto extends LinearOpMode { ... }
```

```java
public interface Subsystem {                  // CONTRACT every subsystem keeps
    void update();                            // "promise you can do one step of work"
}
public class Arm implements Subsystem {
    @Override public void update() { /* move the arm one step */ }
}
```

```java
enum LiftState { DOWN, MID, HIGH }            // named states for a mechanism
```

The big libraries lean on the lambda shape you just learned to read:
```java
gamepad.getGamepadButton(A).whenPressed(() -> claw.open());   // FTCLib command style
```
*You'll wire an interface, an enum, and a state machine into a full simulated OpMode in Phase 11.*

## 📖 Read a real OpMode

Open the SDK sample `BasicOpMode_Linear.java` (search GitHub — see [`docs/reading-real-code.md`](../docs/reading-real-code.md)) and hunt for the three tools from this phase:

1. The line **just above** `public class ...`. What annotation is it — `@TeleOp` or `@Autonomous`? What does it tell the app?
2. Find `@Override`. Which method is it stuck on, and whose method is being replaced (hint: Phase 9)?
3. The sample uses a plain `if`/stick math, not an enum — but *where could* an `enum` for "which alliance" or "which state" fit? (You're now reading like a programmer: spotting where a tool *would* help.)

<details><summary>What to expect</summary>

1. `@TeleOp` (a driver-controlled sample). It tells the Driver Station app to list this OpMode on the TeleOp menu so a human can pick it.
2. `@Override` sits on `runOpMode()`. It replaces the empty `runOpMode()` declared by the parent `LinearOpMode` (inheritance, Phase 9).
3. Anywhere there's a "mode" or "step": e.g. `enum Alliance { RED, BLUE }`, or the autonomous steps `enum Step { DRIVE, TURN, STOP }` you'll build in Phase 11.
</details>

## Self-check quiz
1. In one sentence: what does an interface give you that plain inheritance doesn't?
2. Why is `enum ArmState { DOWN, MID, UP }` safer than using the ints `0`, `1`, `2` for the arm's position?
3. What does the `@Override` annotation actually check for you?
4. Read this: `button.whenPressed(() -> claw.open());` — what is `() -> claw.open()` and when does it run?
5. Inside `switch (armState)`, should you write `case UP:` or `case ArmState.UP:`?

<details><summary>Answers</summary>

1. An interface lets *unrelated* classes promise the same methods (a shared contract) without sharing any code — and a class can implement many interfaces but extend only one class.
2. The enum makes the arm's position one of exactly three named values; you can't accidentally set it to `7`, and the names document themselves. Ints allow illegal values and mean nothing on sight.
3. It checks that a method with that exact name/signature really exists in a parent class or interface — if you typo it, you get a compile error instead of a silent, never-called method.
4. It's a lambda — a tiny function of type `Runnable`/`Command`. It does **not** run now; it's handed to `whenPressed`, which runs it later, when the button is pressed.
5. `case UP:` — the unqualified name. `case ArmState.UP:` is a compile error.
</details>

## Now do it
1. Run all three examples. In `RobotStates.java`, add a `MID`→`MID` tap-down path (a `previous()` method) and test it.
2. Open [`exercises/LiftControl.java`](exercises/LiftControl.java) and follow the TODOs. Check against [`exercises/solutions/LiftControl.java`](exercises/solutions/LiftControl.java).
3. Do [`CHECKPOINT.md`](CHECKPOINT.md) before moving to Phase 11 — the final capstone.
