# Java for FTC 🤖

Learn Java from zero, in small runnable programs, aimed at programming an **FTC (FIRST Tech Challenge)** robot. Built for a 9th grader with no prior coding experience.

By the end you'll be able to **write** Java, **read** the complex-looking code in a real FTC OpMode, and **debug** when things go wrong.

## How this repo works

You move through **11 phases**, simple → medium-hard. Each phase folder has the same layout:

```
phase-NN-name/
  README.md      ← read this first: the idea, worked examples, traps, and the FTC connection
  examples/      ← small programs you run and tinker with
  exercises/     ← starter files with TODOs (answers in exercises/solutions/)
  CHECKPOINT.md  ← prove you're ready for the next phase (answers included)
```

**The rule: don't just read — run and change every example.** Break it on purpose. See the error. Fix it.

## The roadmap

| # | Phase | What you learn | On the robot |
|---|-------|----------------|--------------|
| 1 | [Getting Started & Output](phase-01-getting-started/) | how code runs; printing | telemetry basics |
| 2 | [Variables, Types & Math](phase-02-variables-types-math/) | storing data, doing math | motor power, encoder ticks |
| 3 | [Data In & Out — the Robot Loop Model](phase-03-input-and-loop-model/) | input, strings, polling vs waiting | reading the gamepad |
| 4 | [Decisions](phase-04-decisions/) | if/else, boolean logic | "if button, open claw" |
| 5 | [Loops](phase-05-loops/) | repeating things | `while(opModeIsActive())` |
| 6 | [Methods & Errors](phase-06-methods-and-errors/) | reusable methods; exceptions | `drive()`, `turn()` helpers |
| 7 | [Arrays & Collections](phase-07-arrays-collections/) | lists of things | a drivetrain of motors |
| 8 | [Classes & Objects](phase-08-classes-objects/) | modeling with objects | a `Robot` class, `hardwareMap` |
| 9 | [Inheritance & Polymorphism](phase-09-inheritance-polymorphism/) | building on other classes | `extends LinearOpMode` |
| 10 | [Interfaces, Enums & Annotations](phase-10-interfaces-enums-annotations/) | contracts, states, `@tags` | `@TeleOp`, state machines |
| 11 | [Putting It Together — FTC-Style](phase-11-ftc-style/) | reading a real OpMode | the whole thing |

Track your progress in [`docs/PROGRESS.md`](docs/PROGRESS.md).

## Get set up first (macOS)

You need Java installed before Phase 1. Follow **[`docs/getting-started.md`](docs/getting-started.md)** — it walks you through installing the JDK on a Mac, checking it works, and running your first program.

Quick check (once set up):

```bash
java -version     # should print something like: openjdk version "21.x.x"
```

## Helpful references
- [`SPEC.md`](SPEC.md) — the full curriculum design (and the panel review that shaped it).
- [`docs/glossary.md`](docs/glossary.md) — every new word, explained plainly.
- [`docs/reading-real-code.md`](docs/reading-real-code.md) — how to read real FTC OpModes without a robot.
- [`docs/getting-started.md`](docs/getting-started.md) — macOS setup + how to run programs.

## A note on the goal
Real FTC code looks scary at first — annotations, `extends`, `hardwareMap`, generics. That's not because it's hard; it's because you haven't met the pieces yet. This repo introduces the pieces one at a time, always showing where each one shows up on a robot. By Phase 11 you'll read a real OpMode and think "oh — I know every line of this."
