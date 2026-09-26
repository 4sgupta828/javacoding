# Phase 9 Checkpoint ✅

Do these without looking back at the README. Answers/criteria at the bottom.

1. **Build a hierarchy from scratch.** In a new file `Animals.java`, write an `abstract class Animal` with an `abstract String sound()`. Add `Dog` and `Cat` that each `extends Animal` and override `sound()` ("Woof" / "Meow"). In `main`, put a `Dog` and a `Cat` in an `Animal[]` and loop, printing each sound. Run with `java Animals.java`.

2. **Prove polymorphism.** In that loop you wrote, did you use any `if` to decide which sound to print? Explain in one sentence why you didn't need one.

3. **`super`.** Give `Animal` a constructor that stores a `String name`, and print `name + " says " + sound()`. Make `Dog` and `Cat` call `super(name)`. What happens if you forget `super(name)` and `Animal` has no no-arg constructor?

4. **Break it on purpose.** Try `new Animal("x")`. Run it, copy the error, and say why it's not allowed.

5. **FTC connection.** In `public class MyTeleOp extends LinearOpMode`, (a) what is the "is-a" relationship, (b) which method do you `@Override`, and (c) who actually calls it?

## Self-grading criteria
1. ✅ `Animal` is abstract with an abstract `sound()`; `Dog`/`Cat` override it; the loop prints "Woof" then "Meow". Runs clean.
2. ✅ No `if`. Because each object carries its own `sound()`; calling `a.sound()` runs the version for that object's real type (polymorphism) — the loop doesn't need to know which animal it is.
3. ✅ Children call `super(name)` as the first constructor line; output like "Rex says Woof". Forgetting it gives `error: constructor Animal ... cannot be applied to given types` (Java tries a no-arg `super()` that doesn't exist).
4. ✅ `error: Animal is abstract; cannot be instantiated`. An abstract class has an unfilled method (`sound()`), so it's an incomplete blueprint — you must instantiate a concrete child instead.
5. ✅ (a) `MyTeleOp` **is-a** `LinearOpMode`. (b) You override `runOpMode()` (or `init()`/`loop()` in the iterative `OpMode` style). (c) The **FTC SDK** calls it on your object when the match starts.

**Passed all 5?** Update [`docs/PROGRESS.md`](../docs/PROGRESS.md) and go to Phase 10. 🎉
