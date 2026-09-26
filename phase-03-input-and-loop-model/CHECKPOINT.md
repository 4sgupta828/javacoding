# Phase 3 Checkpoint ✅

Do these without looking back at the README. Answers/criteria at the bottom.

1. **Echo with a Scanner.** In a new file `Echo.java`, ask the user for a word and print it back in ALL CAPS with its length, e.g. `ROBOT (5 letters)`.

2. **The String trap.** Explain in one sentence why this prints nothing even when the user types `go`, then write the corrected line:
   ```java
   String cmd = in.nextLine();
   if (cmd == "go") { System.out.println("driving!"); }
   ```

3. **printf practice.** Using a single `printf`, print exactly:
   ```
   Team 4828 @ 0.80 power
   ```
   from an `int team = 4828;` and a `double power = 0.8;`.

4. **Blocking vs polling.** In your own words: what is the difference, and which one does a robot's gamepad use?

5. **FTC connection.** Write the one line that reads the left stick's Y axis into a `double drive` such that pushing the stick *up* gives a *positive* drive value. What type is `gamepad1.a`?

## Self-grading criteria
1. ✅ if it reads a line and prints `word.toUpperCase()` plus `word.length()`. Example: `System.out.printf("%s (%d letters)%n", word.toUpperCase(), word.length());`
2. ✅ `==` compares object identity, not the text, so it's false for two different String objects. Fix: `if (cmd.equals("go"))` (or `"go".equals(cmd)`).
3. ✅ `System.out.printf("Team %d @ %.2f power%n", team, power);` → `Team 4828 @ 0.80 power`.
4. ✅ Blocking = stop and wait for input before continuing; polling = read the current value every loop pass without waiting. A robot uses **polling**.
5. ✅ `double drive = -gamepad1.left_stick_y;` (negated because the Y axis is inverted). `gamepad1.a` is a `boolean`.

**Passed all 5?** Update [`docs/PROGRESS.md`](../docs/PROGRESS.md) and go to Phase 4. 🎉
