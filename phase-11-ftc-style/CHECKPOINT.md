# Phase 11 Checkpoint — FINAL CAPSTONE ⭐⭐

This is the whole course, put together. It has two halves: **build** a simulated robot, and **read** a real one. Do both. Answers/criteria at the bottom.

## Part A — Build the simulation
Finish [`exercises/RobotSim.java`](exercises/RobotSim.java) so it runs with `java RobotSim.java`.

1. **Autonomous state machine.** Complete `AutoOpMode`: an `enum State { DRIVE_FORWARD, TURN, STOP, DONE }`, and a `loop()` that (a) sets motor powers from the current state, (b) prints telemetry, (c) advances state on a timer — drive 2s, turn 1s, stop. It must end on its own (`isDone()` returns true at `DONE`).

2. **TeleOp loop.** Complete `TeleOpMode.loop()`: read the (flipped) left stick, drive both motors with it, and toggle the claw when `A` is pressed. Print telemetry each loop.

3. **Extend it.** Add ONE new thing of your choice and test it. Ideas: a `STRAFE` state in autonomous; a deadzone on the stick (`Math.abs(drive) < 0.05 ? 0 : drive`); a second button that runs an "arm up" action.

## Part B — Read a real OpMode
4. **Explain every line.** Open `BasicOpMode_Linear.java` from the FtcRobotController samples on GitHub (see [`docs/reading-real-code.md`](../docs/reading-real-code.md)). For each meaningful line, write one sentence: what it does **and** which phase taught it. Mark any line you can't explain — that's your next thing to learn.

5. **Compare the shapes.** Is that sample a `LinearOpMode` or an iterative `OpMode`? How can you tell in two seconds? Where is its control loop?

## Self-grading criteria
1. ✅ Running it prints, in order, `DRIVE_FORWARD` (~4 loops), `TURN` (~2 loops), `STOP`, then `Autonomous complete`-style end. Motor lines show both wheels forward, then left-forward/right-reverse, then both zero. Your output should closely match `exercises/solutions/RobotSim.java`. ✅ if the loop **ends by itself** (no infinite loop) — that proves `isDone()` and the `DONE` transition work.
2. ✅ Stick value `-1.0` drives the motors at `+1.0` (the flip: pushing up is negative → forward), and pressing `A` flips `clawOpen` and prints it. Telemetry shows `drive` and `clawOpen` each loop.
3. ✅ Your addition runs without crashing and does something visible in the output. (A `STRAFE` state ✅ if it's a new `case` in *both* switches — the act switch and the decide switch.)
4. ✅ You can name the phase for the big pieces: `@TeleOp` → annotation (10); `extends LinearOpMode` → inheritance (9); the motor/`hardwareMap` fields → objects & access modifiers (8); `waitForStart()`/`while (opModeIsActive())` → loop (5); `gamepad1.left_stick_y` → polling, Y inverted (3); `telemetry.addData/update` → output (1). Naming ~80% is a pass; listing what you *couldn't* name is part of passing.
5. ✅ It's a `LinearOpMode` — the tell is `extends LinearOpMode` and a single `runOpMode()` you can read top-to-bottom. Its control loop is the `while (opModeIsActive()) { ... }` **you** write (not a system-called `loop()`).

**Passed both parts?** You've hit both course goals: you can **write** clean Java from scratch and **read** a real FTC OpMode line by line. Update [`docs/PROGRESS.md`](../docs/PROGRESS.md) — every box. You're done. 🏁🎉
