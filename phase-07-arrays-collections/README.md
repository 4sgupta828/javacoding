# Phase 7 — Arrays & Collections

> **Prereqs:** Phase 6 (Methods & Errors) done. You can write methods and read a stack trace, and you remember `.equals()` vs `==` from Phase 3.

## The Big Idea: hold many values under one name

So far every variable held **one** thing: one `int`, one `String`. But a robot has *four* drive motors, a match has *many* scores, a season has *many* teams. You don't want `motor1, motor2, motor3, motor4` — you want one name that holds all of them and a way to walk through them with a loop.

That's what **arrays** and **collections** are for. An **array** is a fixed-size row of same-typed boxes. An **`ArrayList`** is a list that grows and shrinks as you add and remove. A **`HashMap`** lets you look a value up by a key (a name), like a dictionary. Grouping data is what turns "four separate variables" into "one loop over the drivetrain."

## Mental model: an array is a row of numbered lockers

```
   index:    0     1     2      <- positions ALWAYS start at 0
           ┌────┬────┬────┐
  scores:  │ 10 │ 20 │ 30 │     length = 3
           └────┴────┴────┘
             ▲              ▲
          scores[0]      scores[2]   <- last valid index is length - 1
```

The **index** is the locker number. The first locker is `0`, not `1`. An array of length 3 has lockers `0, 1, 2` — the last one is `length - 1`. Reach for locker `3` and there's no locker there: `ArrayIndexOutOfBoundsException`.

A **collection** (`ArrayList`, `HashMap`) is the same idea with superpowers: it can grow, shrink, and — for a map — be indexed by a name instead of a number.

## Worked examples

### Arrays and the for-each loop — [`examples/ArrayBasics.java`](examples/ArrayBasics.java)

```java
int[] scores = {10, 20, 30};        // three ints, indexes 0..2
System.out.println(scores[0]);      // 10  (read by index)
System.out.println(scores.length);  // 3   (length is a FIELD — no parentheses)

// index loop: note  i < length  (never <=)
for (int i = 0; i < scores.length; i++) {
    System.out.println(scores[i]);
}

// enhanced for ("for-each"): cleaner when you don't need the index
for (int score : scores) {
    System.out.println(score);      // read as "for each score in scores"
}
```

### ArrayList and HashMap — [`examples/ListsAndMaps.java`](examples/ListsAndMaps.java)

```java
ArrayList<String> team = new ArrayList<>();   // a list of Strings
team.add("Ada");                 // grows on demand
team.add("Grace");
System.out.println(team.size()); // 2   (size() is a METHOD — with parentheses)
System.out.println(team.get(0)); // Ada (get(i), not [i])

HashMap<String, Integer> points = new HashMap<>();   // name -> number
points.put("Ada", 42);
System.out.println(points.get("Ada"));    // 42
System.out.println(points.get("Nobody")); // null  (missing key -> null)
```

Two things to notice:
- Array uses `scores.length` and `scores[i]`. `ArrayList` uses `team.size()` and `team.get(i)`. Different words for the same ideas — mixing them up is a common slip.
- A `HashMap` returns `null` for a key it doesn't have. Trust nothing you didn't `put` in. (We keep `HashMap` light here; it comes back in later phases.)

## ⚠️ Common Traps (with the real errors)

**1. The aliasing trap — "I changed one and the other changed too."** Arrays and lists are **references**: the variable holds an *arrow to* the data, not the data itself. Assigning one variable to another copies the **arrow**, so both point at the *same* object:

```java
int[] a = {1, 2, 3};
int[] b = a;          // b points at the SAME array — NOT a copy!
b[0] = 99;
System.out.println(a[0]);   // prints 99, not 1  😱
```
The same thing bites with lists:
```java
ArrayList<String> listTwo = listOne;   // same list, two names
listTwo.add("x");                      // listOne.size() also went up
```
This is the #1 confusing bug with collections. If you truly want a separate copy, you must make a new one (e.g. `new ArrayList<>(listOne)`), not just assign. Compare with a plain `int`: `int y = x;` *does* copy, because `int` is a value, not a reference.

**2. `ArrayIndexOutOfBoundsException` — reaching past the end.** The last valid index is `length - 1`:

```java
int[] scores = {10, 20, 30};   // valid indexes: 0, 1, 2
System.out.println(scores[3]); // there is no index 3
```
```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
	at ArrayBasics.main(ArrayBasics.java:12)
```
This is the loop off-by-one from Phase 5, now with teeth. `for (i = 0; i <= scores.length; i++)` will do exactly this on the last pass. Use `i < scores.length`.

**3. `.length` vs `.length()` vs `.size()`.** Three different things:
- array → `scores.length` (a field, **no** parentheses)
- String → `name.length()` (a method, **with** parentheses)
- ArrayList → `team.size()` (a method)

Get it wrong and you'll see, for an array:
```java
scores.length()
```
```
error: cannot find symbol
  symbol:   method length()
```

**4. Wrong element type in a typed collection.** If a list is `ArrayList<String>`, you can't add an `int`:
```java
ArrayList<String> names = new ArrayList<>();
names.add(5);
```
```
error: incompatible types: int cannot be converted to String
```

## 🔍 Decode this error
```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 4 out of bounds for length 4
	at Drivetrain.setAll(Drivetrain.java:8)
```
- `ArrayIndexOutOfBoundsException` → you used an index that doesn't exist (runtime crash).
- `Index 4 out of bounds for length 4` → the array has **4** elements, so valid indexes are `0,1,2,3`. Index **4** is one past the end.
- `at Drivetrain.setAll(Drivetrain.java:8)` → go to **line 8**. Look for a loop condition using `<=` instead of `<`, or a hard-coded `[4]`.

## 🤖 On the Robot (FTC)
A drivetrain is four motors — a perfect array. Instead of writing four near-identical lines, put them in an array and loop:

```java
DcMotor[] drivetrain = { leftFront, rightFront, leftBack, rightBack };

// set the same power on all four, in one loop:
for (DcMotor motor : drivetrain) {
    motor.setPower(0.5);
}
```

Same idea for a bank of sensors — loop over a list and act on each:

```java
for (DcMotor motor : drivetrain) {
    telemetry.addData("pos", motor.getCurrentPosition());
}
telemetry.update();
```

> **Heads up:** you'll soon meet `hardwareMap` (Phase 8). Even though it *sounds* like a HashMap and you look things up by name, it is **not** a collection you build — it's a lookup object the FTC framework hands you. Don't confuse the two. Arrays and `ArrayList` are things *you* create and fill.

*(You'll wire up a real `DcMotor[]` in Phase 8+.)*

## 📖 Read a real OpMode

Read, don't run. This is a real pattern for driving four motors from one array.

```java
private DcMotor[] drivetrain;   // a field holding four motors (Phase 8 fills it)

@Override
public void runOpMode() {
    // ... each motor is initialized from hardwareMap ...
    waitForStart();
    while (opModeIsActive()) {
        double power = -gamepad1.left_stick_y;
        for (DcMotor motor : drivetrain) {   // for-each over the array (Phase 7!)
            motor.setPower(power);
        }
        telemetry.update();
    }
}
```

**Predict:** If `drivetrain` has 4 motors, how many times does the inner `for` body run *per loop pass*? Over a whole match (~6,000 passes), roughly how many `setPower` calls is that? *(Answers: 4 per pass; ~24,000 total.)*

## Self-check quiz
1. What is the index of the **first** element of an array, and of the **last** (in terms of `length`)?
2. You write `int[] b = a;` then change `b[0]`. What happens to `a[0]`, and why?
3. What's the difference between `array.length`, `string.length()`, and `list.size()`?
4. What does a `HashMap` return when you `get()` a key that was never `put()` in?
5. When does an `ArrayIndexOutOfBoundsException` happen, and what's the usual off-by-one cause in a loop?
6. Why is it wrong to call `hardwareMap` a HashMap?

<details><summary>Answers</summary>

1. The first element is index **0**; the last is index **`length - 1`**.
2. `a[0]` also becomes the new value. `b = a` copies the **reference** (the arrow), so `a` and `b` point at the *same* array — that's aliasing.
3. `array.length` is a **field** (no parentheses). `string.length()` and `list.size()` are **methods** (with parentheses). They all mean "how many," but the syntax differs by type.
4. `null`. A missing key has no value, so `get` returns `null` — always check before using it.
5. When you use an index outside `0 .. length - 1` (e.g. an index equal to `length`). In loops it's usually a header that uses `<=` where it should use `<`.
6. `hardwareMap` is a lookup object the FTC framework gives you (covered in Phase 8), not a collection you create and fill like `ArrayList`/`HashMap`. It only resembles a map because you look devices up by name.
</details>

## Now do it
1. Run both examples. In `ArrayBasics.java`, uncomment the `scores[3]` line and read the real `ArrayIndexOutOfBoundsException`.
2. Build the scoreboard: open [`exercises/Scoreboard.java`](exercises/Scoreboard.java) and follow the TODOs. That's your tangible win. 📋
3. Do [`CHECKPOINT.md`](CHECKPOINT.md) before moving to Phase 8.
