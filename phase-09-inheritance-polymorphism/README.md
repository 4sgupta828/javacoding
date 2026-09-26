# Phase 9 — Inheritance & Polymorphism

> **Prereqs:** Phase 8 done. You can build a class with fields, a constructor, and methods, and you know class-vs-object. This phase stacks classes on top of each other. (We stay focused: **interfaces, enums, and annotations are Phase 10** — not here.)

## The Big Idea: build new classes on top of existing ones ("is-a")

You just learned to write a class. But real programs have *families* of classes that are almost the same: a `Drivetrain`, a `Claw`, and a `Lift` are all **subsystems** — they all get initialized, they all update every loop. Copy-pasting that shared code into each one is a maintenance nightmare.

**Inheritance** lets one class build on another: a child class gets everything the parent has, then adds or changes what's different.

> A `Drivetrain` **is a** `Subsystem`. A `Circle` **is a** `Shape`. A TeleOp program **is an** `OpMode`.

That "**is-a**" phrase is the test for inheritance. If you can honestly say "*X is a kind of Y*", then `class X extends Y` makes sense.

And the *payoff* is **polymorphism**: because a `Drivetrain` is-a `Subsystem`, you can treat a whole mixed pile of subsystems uniformly — call `update()` on each — and every object automatically runs *its own* version. **Same call, different behavior.** This is the single most important idea for reading real FTC code, because the FTC SDK does exactly this to *your* OpMode.

## Mental model: a family tree, and "fill in the blank"

Inheritance is a family tree. The child inherits the parent's traits and can add its own.

```
              Subsystem              ← the PARENT (general): has name, init(), update()
             /          \
       Drivetrain       Claw         ← CHILDREN (specific): each IS-A Subsystem,
       (own update)   (own update)      each overrides update() its own way
```

- **`extends`** — "is a kind of." `class Claw extends Subsystem`.
- **`super`** — "the parent's version." `super(name)` calls the parent's constructor; `super.init()` calls the parent's `init()`.
- **`@Override`** — "I'm replacing a method the parent already defined." (Java double-checks you actually are.)
- **`abstract`** — "a blueprint with a blank left for the child to fill in." An `abstract` method has no body; the child *must* provide one. You can't `new` an abstract class.

Polymorphism in one picture: you hold the objects in a `Subsystem[]` (the parent type) but each slot secretly holds a specific child, and `s.update()` runs the child's code:

```
   Subsystem[] robot                 s.update() runs...
   ┌────────────────────┐
   │ [0] -> Drivetrain  │  ──────►   Drivetrain.update()   (drives motors)
   │ [1] -> Claw        │  ──────►   Claw.update()         (holds servo)
   └────────────────────┘            ONE call site, TWO behaviors
```

## Worked example 1 — polymorphism with shapes

Open [`examples/Shapes.java`](examples/Shapes.java) and run it:

```bash
java Shapes.java
```

The core: an **abstract** parent that promises an `area()` but won't say how, and children that each fill it in:

```java
abstract class Shape {
    public abstract double area();     // no body — every child MUST provide one
}

class Circle extends Shape {
    @Override public double area() { return Math.PI * radius * radius; }
}
class Rectangle extends Shape {
    @Override public double area() { return width * height; }
}
```

Then one loop treats them all the same:

```java
Shape[] shapes = { new Circle(2.0), new Rectangle(3.0, 4.0) };
for (Shape shape : shapes) {
    System.out.println(shape.area());   // Java runs the RIGHT area() for each object
}
```

We never write `if (it's a circle) ... else if (it's a rectangle) ...`. The objects know their own behavior. **That's** polymorphism, and it's why you *override*.

## Worked example 2 — a subsystem hierarchy (the FTC shape)

Open [`examples/Subsystems.java`](examples/Subsystems.java):

```bash
java Subsystems.java
```

Watch two things:

**1. `super` reuses the parent, then adds to it:**
```java
class Drivetrain extends Subsystem {
    public Drivetrain(String name) {
        super(name);          // run the parent's constructor first (MUST be the first line)
    }
    @Override public void init() {
        super.init();         // do the parent's init...
        System.out.println("...then reset encoders");   // ...and add my own step
    }
}
```

**2. A framework loop that only knows the parent type still runs the child's code:**
```java
for (Subsystem s : robot) s.init();     // Claw inherits base init(); Drivetrain runs its overridden one
for (Subsystem s : robot) s.update();   // each object runs ITS OWN update()
```

That loop is a miniature of the FTC SDK: **it holds your objects by the parent type and calls the overridden method** — without ever naming your specific class. Hold that thought for the FTC section.

### `@Override` isn't decoration — it's a safety net
`@Override` tells the compiler "this should be replacing a parent method." If you typo the name or get the parameters wrong, Java stops you:
```java
@Override public void updaet() { ... }   // typo!
```
```
error: method does not override or implement a method from a supertype
```
Without `@Override`, that typo would silently create a *brand-new* method and your real `update()` would never run — a nasty, silent bug. **Always write `@Override` when you mean to override.**

## ⚠️ Common Traps (with the real errors)

**1. Trying to `new` an abstract class.**
```java
Shape s = new Shape();     // Shape is abstract — it has a blank (area) with no body
```
```
error: Shape is abstract; cannot be instantiated
```
Fix: instantiate a concrete child — `new Circle(2.0)`.

**2. Forgetting to implement an abstract method.**
```java
class Triangle extends Shape { }   // never wrote area()
```
```
error: Triangle is not abstract and does not override abstract method area() in Shape
```
Fix: provide `@Override public double area() { ... }` (or mark `Triangle` abstract too).

**3. `super(...)` not first in the constructor.**
```java
public Drivetrain(String name) {
    System.out.println("hi");   // something before super()
    super(name);
}
```
```
error: call to super must be first statement in constructor
```
Fix: `super(name)` must be the **very first** line.

**4. A silent typo where `@Override` was missing.** No `@Override`, misspelled method name — *no error at all*, but your method never gets called by the framework loop, and the base version runs instead. This is why trap #4 has no error box: the danger is that there *isn't* one. Add `@Override` and the compiler catches it (see trap in the box above).

## 🔍 Decode this error
```
error: constructor Subsystem in class Subsystem cannot be applied to given types;
  required: String
  found:    no arguments
  reason: actual and formal argument lists differ in length
```
- The parent `Subsystem` has only one constructor, and it **requires** a `String`.
- Your child constructor didn't call `super("...")`, so Java tried to call `super()` (no args) automatically — but there is no no-arg constructor.
- `required: String / found: no arguments` → it wanted a name, got nothing.
- **Fix:** call `super(name);` as the first line of the child constructor. *When a parent has no no-arg constructor, the child must call `super(...)` explicitly.*

## 🤖 On the Robot (FTC)

**This is the reason `extends` and `@Override` are all over real OpModes.** Your program doesn't run itself — the FTC SDK runs it. You write a class that **is-an** OpMode and **override** the one method the SDK promises to call:

```java
@TeleOp                                       // annotation (Phase 10) — just "this is a driver OpMode"
public class MyTeleOp extends LinearOpMode {  // inheritance: MyTeleOp IS-A LinearOpMode
    @Override                                  // you are REPLACING the parent's runOpMode()
    public void runOpMode() {
        // your code: set up hardware, waitForStart(), then loop
    }
}
```

Here's the whole point, spelled out:
1. `LinearOpMode` (the parent, written by the SDK) has a `runOpMode()` method that does *nothing* useful on its own — it's meant to be overridden. (In the iterative style, `OpMode` has `init()` and `loop()` you override instead.)
2. You `extends LinearOpMode` and `@Override public void runOpMode()` with *your* robot logic.
3. When the driver hits INIT, **the SDK calls `runOpMode()` on your object** — polymorphism! It holds your object as an `OpMode`, calls the method, and *your* overridden version runs. That's the exact same trick as the `for (Subsystem s : robot) s.update();` loop in Example 2.

So every `@Override` you see on `runOpMode()` / `loop()` means: *"the SDK will call this; here's what I want to happen."* And a subsystem hierarchy (like Example 2's `Subsystem` → `Drivetrain`/`Claw`) is how bigger teams organize a robot. *You'll write a full OpMode this way in Phase 11.*

## 📖 Read a real OpMode

Read and name each piece:

```java
public abstract class BaseAuto extends LinearOpMode {   // a team's OWN base class, still an OpMode
    protected DcMotor left, right;

    protected void setupDrive() {                        // shared helper the children inherit
        left  = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
    }

    @Override
    public void runOpMode() {
        setupDrive();
        waitForStart();
        drive();                                          // <-- calls the abstract method below
    }

    protected abstract void drive();                      // each auto fills in its OWN path
}

@Autonomous
public class RedLeftAuto extends BaseAuto {               // RedLeftAuto IS-A BaseAuto IS-A LinearOpMode
    @Override protected void drive() {
        left.setPower(0.5); right.setPower(0.5);          // this team's specific autonomous path
    }
}
```

**Predict:** Why is `BaseAuto` abstract? When the SDK runs `RedLeftAuto`, which `drive()` executes, and who calls it?

<details><summary>Check yourself</summary>

- `BaseAuto` is `abstract` because it leaves `drive()` blank — it's a reusable skeleton, not a complete OpMode you'd run directly. Each real auto must fill in `drive()`.
- `RedLeftAuto.drive()` runs. The SDK calls `runOpMode()` (inherited from `BaseAuto`); `runOpMode()` calls `drive()`; polymorphism picks `RedLeftAuto`'s overridden `drive()` because the object really is a `RedLeftAuto`. Two levels of inheritance, one clean call.
</details>

## Self-check quiz
1. What does the "**is-a**" test tell you, and when should you use `extends`?
2. What do `super(...)` and `super.method()` each do?
3. What is **polymorphism**, in one sentence? Give the shape example.
4. Why is `@Override` worth writing even though the code often works without it?
5. What is an `abstract` method, and what must a (non-abstract) child class do about it?
6. In FTC, why does your OpMode `extends LinearOpMode` and `@Override runOpMode()`? Who calls `runOpMode()`?

<details><summary>Answers</summary>

1. "Is-a" tells you whether inheritance fits: use `extends` only when the child truly *is a kind of* the parent (a `Circle` is a `Shape`). If it's not an "is-a," don't inherit.
2. `super(...)` calls the parent's **constructor** (must be the first line of the child constructor); `super.method()` calls the parent's version of a **method**, so you can reuse it and add to it.
3. Polymorphism = calling the same method on different objects and each runs its own version. `shape.area()` gives πr² for a `Circle` and w×h for a `Rectangle` — one call, different behavior.
4. `@Override` makes the compiler verify you're really replacing a parent method. Without it, a typo or wrong signature silently creates a *new* method and the intended override never runs — a hard-to-find bug.
5. An `abstract` method has no body — just a promise. A non-abstract child **must** override it and supply a body (otherwise the child must itself be declared `abstract`).
6. `LinearOpMode` (the SDK parent) defines `runOpMode()` as the hook it will call; you override it with your robot logic. **The SDK** calls `runOpMode()` on your object when the match starts — that's polymorphism doing the work.
</details>

## Now do it
1. Run both examples. In `Shapes.java`, add a `Square extends Shape` and drop it into the array — notice you *don't* touch the loop.
2. Do [`exercises/Menagerie.java`](exercises/Menagerie.java) — build a mechanism hierarchy with overridden behavior. Check against [`solutions/`](exercises/solutions/Menagerie.java).
3. Pass [`CHECKPOINT.md`](CHECKPOINT.md) before moving to Phase 10.
