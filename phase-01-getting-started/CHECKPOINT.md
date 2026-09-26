# Phase 1 Checkpoint ✅

Do these without looking back at the README. Answers/criteria at the bottom.

1. **Write from scratch.** In a new file `MyFirst.java`, print your name and your FTC team number on two separate lines. Run it with `java MyFirst.java`.

2. **Compile explicitly.** Run `javac MyFirst.java`, then `ls`. What new file appeared, and what is it?

3. **Break and read.** Delete a semicolon in `MyFirst.java` and run it. Copy the error message. What is Java telling you?

4. **One line, two outputs.** Using a single `System.out.println`, print:
   ```
   Motors: OK
   Sensors: OK
   ```
   (Hint: `\n`.)

5. **FTC connection.** In real FTC code, what two method calls show text to the driver, and which one actually makes it appear?

## Self-grading criteria
1. ✅ if it runs and prints two lines. (Class name must match file name: `MyFirst`.)
2. ✅ `MyFirst.class` appeared — that's the **bytecode** the JVM runs.
3. ✅ if you found `error: ';' expected` and can say "a statement was missing its semicolon."
4. ✅ `System.out.println("Motors: OK\nSensors: OK");`
5. ✅ `telemetry.addData(...)` queues it and `telemetry.update()` actually sends it to the Driver Station.

**Passed all 5?** Update [`docs/PROGRESS.md`](../docs/PROGRESS.md) and go to Phase 2. 🎉
