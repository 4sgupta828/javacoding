# Phase 8 — Classes & Objects

> **Prereqs:** Phases 1–7 done. You can write methods, use arrays/collections, and read a stack trace. This is the **biggest conceptual leap** in the course — take it slow. Nothing new is *hard*; there's just a new way of *thinking*.

## The Big Idea: model the world as objects, each with its own state

Up to now your programs were a pile of variables and methods. That works for small things. But a robot is **big**: it has a drivetrain, an arm, a claw, a couple of sensors, a timer — and each of those has its own **state** (the claw is open or closed; the arm is at 45°) and its own **behaviors** (open, close, raise).

Cramming all of that into loose variables (`clawOpen`, `armAngle`, `leftPower`, `rightPower`, ...) gets unmanageable fast. **Classes** let you bundle related state and behavior into one named thing:

> "A robot **has** subsystems. Each subsystem **has** its own state and **knows** how to do its own jobs."

That sentence is object-oriented programming. A `Claw` object *owns* whether it's open and *knows how* to open/close itself. You stop tracking loose variables and start giving orders to objects: `claw.open()`. This is exactly how every real FTC robot program is organized — so this phase is the bridge to reading real robot code.

## Mental model: a class is a blueprint; an object is a house built from it

A **class** is a blueprint. It is *not* a thing you can live in — it's the drawing that says "a house has a door and 3 windows, and here's how the door opens." You can build **many** houses from one blueprint, and each house has its **own** door that opens independently.

```
   class Claw   ...............  the BLUEPRINT (written once)
   ┌─────────────────────────┐
   │ state:  open (yes/no)    │
   │ can:    open(), close()  │
   └─────────────────────────┘
            │  new Claw()      │  new Claw()
            ▼                  ▼
   ┌───────────────┐   ┌───────────────┐
   │ Claw OBJECT #1│   │ Claw OBJECT #2│   ← two real THINGS, each with its OWN state
   │ open = true   │   │ open = false  │
   └───────────────┘   └───────────────┘
```

Key vocabulary (you'll use these constantly):

| Word | Means | In code |
|------|-------|---------|
| **class** | the blueprint | `class Claw { ... }` |
| **object** (a.k.a. *instance*) | a thing built from the blueprint | `new Claw()` |
| **field** | a variable that IS the object's state | `private boolean open;` |
| **constructor** | the setup code that runs when you build an object | `public Claw() { ... }` |
| **method** | a behavior the object can do | `void open() { ... }` |
| **`this`** | "the object I'm inside of right now" | `this.open = true;` |

## Worked example 1 — a robot made of subsystems

Open [`examples/RobotDemo.java`](examples/RobotDemo.java) and run it:

```bash
java RobotDemo.java
```

The heart of it — a `Claw` blueprint:

```java
class Claw {
    private boolean open;                 // FIELD: this object's state

    public Claw() {                       // CONSTRUCTOR: runs once, at `new Claw()`
        this.open = false;                //   start closed
    }

    public void open()  { this.open = true;  }   // METHODS change this object's state
    public void close() { this.open = false; }

    public String label() {               // a method that RETURNS a value
        return open ? "OPEN" : "CLOSED";
    }
}
```

Then in `main`:

```java
Robot bot    = new Robot("Optimus");    // build one object
Robot backup = new Robot("Bumblebee");  // build a SECOND, totally separate object

bot.openClaw();      // changes ONLY bot's claw
backup.status();     // Bumblebee's claw is still CLOSED — separate state!
```

Two objects, one blueprint, **independent state**. That's the whole idea.

### `this` — why it's there
Inside a method, `this` means "the object this method was called on." When a parameter and a field share a name, `this` disambiguates:

```java
public Robot(String name) {
    this.name = name;   // this.name = the FIELD;  name = the PARAMETER
}
```

Without `this.`, `name = name;` would just assign the parameter to itself and the field would stay empty — a classic silent bug.

## Worked example 2 — a clean little object: a timer

Open [`examples/TimerDemo.java`](examples/TimerDemo.java):

```bash
java TimerDemo.java
```

It's a stopwatch object with just two behaviors — `reset()` and `seconds()` — over one hidden field. This is a deliberate stand-in for FTC's **`ElapsedTime`** object, which has the *exact same shape*:

```java
StopClock timer = new StopClock();   // (FTC: ElapsedTime timer = new ElapsedTime();)
timer.reset();                        //  start counting from zero
double t = timer.seconds();           //  how long since reset?
```

Notice the field (`startNanos`) is `private`: the outside world never touches it directly, it just calls `seconds()`. That's **encapsulation** — hide the messy inside, expose clean buttons.

## Access modifiers: `public` vs `private`

- **`private`** — usable only *inside this class*. Use it for fields and helper methods. It protects an object's state so nobody can set it to something nonsensical.
- **`public`** — usable from *anywhere*. Use it for the methods you *want* others to call (`open()`, `status()`).

Rule of thumb for beginners: **fields `private`, the methods you mean to be used `public`.** If a `Claw`'s `open` field were public, any code anywhere could flip it — and you'd never be able to add a rule like "don't open past 90°."

## Static vs instance: belongs-to-the-class vs belongs-to-each-object

- **instance** members belong to each object. Each `Robot` has its **own** `name` and `claw`.
- **`static`** members belong to the **class itself** — there's exactly one, shared by all objects.

```java
class Robot {
    public static int robotsBuilt = 0;   // ONE counter, shared by every Robot
    private final String name;           // each Robot has its OWN name
    ...
}
```

You already know two static things:
- **`public static void main`** is static because it runs *before any object exists* — there's no object to attach it to yet.
- **Constants** are `static final`: one shared, unchangeable value, e.g. `static final double TICKS_PER_REV = 537.7;`. (`final` = "can't be reassigned.")

You call a static member on the **class name**: `Robot.robotsBuilt`. You call an instance member on an **object**: `bot.status()`.

## ⚠️ Common Traps (with the real errors)

**1. Calling an instance method as if it were static.** A top-5 beginner error.
```java
Claw.open();     // ← wrong: open() needs an actual claw object
```
```
error: non-static method open() cannot be referenced from a static context
```
Fix: build an object first — `Claw c = new Claw(); c.open();`

**2. Forgetting `new`.** Declaring a variable does **not** build an object.
```java
Robot bot;          // just a name that points at NOTHING (null) so far
bot.status();       // crash:
```
```
Exception in thread "main" java.lang.NullPointerException:
    Cannot invoke "Robot.status()" because "bot" is null
```
Fix: `Robot bot = new Robot("Optimus");`

**3. Shadowing a field by forgetting `this`.**
```java
public Robot(String name) {
    name = name;    // assigns the parameter to itself; the FIELD stays null
}
```
No error — it just silently does nothing useful. Later `status()` prints `null`. Use `this.name = name;`.

**4. Touching a `private` field from outside.**
```java
bot.name = "Zed";   // from main(), where name is private
```
```
error: name has private access in Robot
```
Fix: that's the point of `private`. Go through a public method instead.

## 🔍 Decode this error
```
Exception in thread "main" java.lang.NullPointerException:
    Cannot invoke "Arm.getAngle()" because "this.arm" is null
    at Robot.status(RobotDemo.java:88)
```
- `NullPointerException` → you used a reference that points to nothing (`null`).
- `because "this.arm" is null` → modern Java tells you *exactly which* reference: the `arm` field.
- `at Robot.status(RobotDemo.java:88)` → and *exactly where*: line 88, in `status()`.
- **Cause:** the constructor never did `this.arm = new Arm();`, so `arm` was never built. *A field you declare but never assign starts as `null`.*

## 🤖 On the Robot (FTC)

This phase is the real home for objects — an FTC program *is* a class:

```java
public class MyRobot {
    private DcMotor leftDrive;      // a FIELD holding a motor OBJECT
    private Servo   claw;           // a FIELD holding a servo OBJECT

    public void init(HardwareMap hardwareMap) {
        // hardwareMap.get(...) looks up a physical device by the name you set in the app
        // and hands you back an OBJECT to control it.
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        claw      = hardwareMap.get(Servo.class,   "claw");
    }
}
```

- **`DcMotor.class`** is a *class literal* — it's how you hand the class itself to a method, so `get` knows what kind of object to return. Read it as "give me the DcMotor named 'left_drive'." (One line: `Something.class` means "the class `Something`, as a value.")
- The strings (`"left_drive"`) are the config names you type into the Driver Station app.

Once you have the objects, you call **methods** on them — the DcMotor/Servo API you'll live in:

```java
leftDrive.setDirection(DcMotor.Direction.REVERSE);  // flip so "forward" is forward
leftDrive.setPower(0.75);        // motor power is a double, -1.0 .. +1.0

claw.setPosition(0.0);           // Servo position is a double, 0.0 .. 1.0 (open)
claw.setPosition(1.0);           // (closed) — the servo swings to that fraction of its range

ElapsedTime timer = new ElapsedTime();  // a real SDK object, just like our StopClock
timer.reset();
if (timer.seconds() > 3.0) { /* ... */ }
```

Everything you build in the exercise — a `Robot` that owns a `Claw` and an `Arm` and obeys commands — is a simplified version of a real FTC subsystem class. *You'll wire one to real hardware in Phase 11.*

## 📖 Read a real OpMode

Read this and name each piece — don't worry about running it:

```java
public class Lift {                                  // a subsystem class (a blueprint)
    private DcMotor motor;                            // FIELD: holds a motor object (private!)
    static final int TOP    = 1200;                   // static final = a shared CONSTANT (encoder ticks)
    static final int BOTTOM = 0;

    public Lift(HardwareMap hardwareMap) {            // CONSTRUCTOR takes the hardwareMap
        motor = hardwareMap.get(DcMotor.class, "lift"); // look up hardware -> get an OBJECT back
    }

    public void goToTop()    { motor.setPower(0.8);  } // instance METHODS: behaviors of THIS lift
    public void goToBottom() { motor.setPower(-0.8); }
    public int  height()     { return motor.getCurrentPosition(); }  // RETURNS the encoder reading
}
```

**Predict:** What does `new Lift(hardwareMap)` do? Why is `motor` `private`? What's the difference between `TOP` and `height()`?

<details><summary>Check yourself</summary>

- `new Lift(hardwareMap)` builds a Lift object and, in its constructor, looks up the "lift" motor and stores that object in the `motor` field.
- `motor` is `private` so only the `Lift` class controls the motor — the rest of the program uses `goToTop()` / `goToBottom()` and can't do something unsafe directly.
- `TOP` is a `static final` **constant** (a fixed number shared by the class); `height()` is an **instance method** that reads the *current* live position of *this* lift.
</details>

## Self-check quiz
1. In one sentence each: what's a **class** and what's an **object**?
2. What does a **constructor** do, and when does it run?
3. Why write `this.name = name;` instead of `name = name;`?
4. What's the difference between a `private` field and a `public` method — why make fields private?
5. Why is `main` marked `static`? Why is a per-robot `name` field *not* static?
6. In `hardwareMap.get(DcMotor.class, "left_drive")`, what does the method give you back, and what is `DcMotor.class`?

<details><summary>Answers</summary>

1. A **class** is a blueprint describing state + behavior; an **object** is one actual thing built from that blueprint (with `new`). Many objects can come from one class, each with its own state.
2. A constructor sets up a new object's starting state; it runs once, automatically, when you write `new ClassName(...)`.
3. `this.name` is the object's field; the bare `name` is the parameter. `name = name;` just assigns the parameter to itself and leaves the field `null`.
4. A `private` field can only be touched inside its own class; a `public` method can be called from anywhere. Fields are private so the object controls its own state and outside code can't set it to something invalid.
5. `main` is `static` because it runs before any object exists — there's nothing to attach it to. A `name` differs per robot, so it must be an instance field (one per object), not shared by the class.
6. It returns an **object** (a `DcMotor` you can call `setPower` on). `DcMotor.class` is a class literal — the class itself passed as a value, telling `get` what type to return.
</details>

## Now do it
1. Run both examples. In `RobotDemo.java`, build a third robot and give it a different arm angle — confirm the others don't change.
2. Do [`exercises/RobotCommands.java`](exercises/RobotCommands.java) — build a robot that obeys typed commands. Check against [`solutions/`](exercises/solutions/RobotCommands.java).
3. Pass [`CHECKPOINT.md`](CHECKPOINT.md) before moving to Phase 9.
