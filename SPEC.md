# Java for FTC — Curriculum Spec (v1.0, panel-reviewed)

> Reviewed by a 3-person panel: an FTC mentor, a CS-education/pedagogy specialist, and a senior Java engineer. Their feedback is folded into this version. Change log at the bottom.

## Who this is for
A 9th-grade student learning Java from scratch to program an **FTC (FIRST Tech Challenge)** robot. No prior programming experience assumed. Student is on **macOS**.

## Goals (what "done" looks like)
By the end, the student can:
1. **Write** clean, working Java from a blank file.
2. **Read and understand** complex, "generated-looking" Java — including real FTC OpModes full of annotations, inheritance, and library calls.
3. **Debug**: read stack traces and compiler errors, use telemetry/print output, reason about failures.
4. **Connect** every language concept to how it appears on a robot.

## Design principles
- **Fundamentals first, every phase** — teach the *idea* (what a loop is and why), not just syntax.
- **Small programs, run early** — real code runs in Phase 1.
- **FTC bridge in every phase** — a required "On the Robot" section shows a *real* (if simplified) FTC shape, not a contrived analogy.
- **Spiral, not cliff** — concepts reappear and deepen; hard ideas get their own phase.
- **Reading is a taught skill** — a recurring "Read a real OpMode" sidebar runs from Phase 4 on (needs no toolchain).
- **Tangible wins often** — something showable/playable every 2–3 phases, not just two capstones.
- **Traps are first-class** — a required "Common Traps" section with *real error text* in every phase.
- **Age-appropriate pacing** — ~1–2 weeks/phase at a hobby pace; each phase self-contained.

## Tooling decisions
- **JDK:** Eclipse Temurin **21 (LTS)** via Homebrew. (Java 25 exists in 2026, but 21 is stable and closer to FTC's language level; more capable models/features aren't needed here.)
- **Run workflow (Phases 1–10):** default to the **single-file launcher** — `java Hello.java` (no separate compile step). Demonstrate the explicit `javac Hello.java` → `java Hello` **once** in Phase 1 so "compile" is concrete, then drop the friction.
- **Exploration:** use **`jshell`** (the REPL) for Phases 2–5 to get instant feedback on types, math, strings, booleans.
- **Editor:** VS Code + Java Extension Pack (note: budget setup time for JDK detection). IntelliJ IDEA Community is a fine alternative and shares lineage with Android Studio.
- **No build tool (Gradle/Maven) until Phase 11**, and only *conceptually* then. FTC uses Android Studio/Gradle; we do **not** require it. We read real OpModes from GitHub (no tooling) and mention **OnBot Java** as a no-Android-Studio way to run code on a real robot.
- **FTC language-level caveat:** FTC's SDK targets an older Java language level (Java 8 historically; ~17 in recent seasons). Teach the durable core on 21 but flag features that **won't appear in / may not compile in** real FTC code: records, sealed types, text blocks, pattern-matching & switch *expressions*. (`var` and the classic enhanced `switch` statement are safe.)

## Per-phase structure (uniform template)
Each `phase-NN-name/` folder contains:
- `README.md` — learning material, in this order:
  1. **The Big Idea** (the fundamental, plainly stated) + why it matters.
  2. **Mental model** (e.g. "variable = labeled box"), with a small diagram where it helps.
  3. **Worked examples**, heavily commented.
  4. **Common Traps** — *required*; each trap shows the real error text or wrong output (`==` vs `.equals()`, integer division, `NullPointerException`, off-by-one, etc.).
  5. **Decode this error** — one real compiler/runtime message pulled apart.
  6. **On the Robot (FTC)** — the concept in real FTC code; ends with "...you'll write this in Phase 11" where apt.
  7. **Read a real OpMode** (from Phase 4 on) — a snippet to read and predict.
  8. **Self-check quiz** — with answers.
- `examples/` — small runnable `.java` files, heavily commented.
- `exercises/` — starter files with `TODO`s + a `solutions/` subfolder (worked answers, since the learner is often solo).
- `CHECKPOINT.md` — 3–5 "can you do this?" tasks **with self-grading criteria/answers**.

## The 11 phases

| # | Title | Core concept | Java surface | FTC bridge | Tangible win |
|---|-------|-------------|--------------|-----------|--------------|
| 1 | Getting Started & Output | how a program runs (source→bytecode→JVM); sequencing | `main`, `println`, comments; `package`/`import` "what the top lines mean"; compile once then `java File.java` | telemetry is an *object* needing `.update()`; `System.out` → logcat, not Driver Station | "Hello, robot" + a formatted banner |
| 2 | Variables, Types & Math | storing/expressing data | `int double boolean String` (`char` = mention), operators, casting, **integer division**; `jshell` | motor power (`double`), encoder ticks (`int`) | a unit/gear-ratio calculator |
| 3 | Data In & Out — the Robot Loop Model | I/O; **polling vs blocking** | `Scanner` (flagged as console-only scaffolding), String methods, `printf`; **`==` vs `.equals()`** | gamepad = **non-blocking polling** every loop; sticks `float` −1..1, **Y inverted**, buttons `boolean` | a greeter / mad-libs |
| 4 | Decisions | branching & boolean logic | `if/else`, `switch` (light), `&& \|\| !`; **`=` vs `==`** | "if button pressed, open claw"; deadzone check | rock-paper-scissors |
| 5 | Loops | repetition & iteration | `for`, `while`, `do-while`, nested, `break/continue`; **off-by-one** | `while (opModeIsActive())` control loop | **number-guessing game** |
| 6 | Methods & Errors | decomposition, reuse; what an exception *is* | methods, params, return, scope, overloading; **try/catch/`throws`**, **`NullPointerException`**, reading stack traces | `drive()`/`turn()` helpers; `runOpMode()` `throws InterruptedException` | **capstone: FTC match scorer** |
| 7 | Arrays & Collections | grouping data | arrays, `ArrayList`, `HashMap` (light); **reference vs value / aliasing**, `ArrayIndexOutOfBounds` | `DcMotor[] drivetrain`, loop over a list of sensors (NOT `hardwareMap`) | a scoreboard / roster |
| 8 | Classes & Objects | modeling with OOP | fields, constructors, `this`, methods; **class vs object** mental model; **access modifiers**, **static vs instance** | `Robot`/subsystem class; `hardwareMap.get(DcMotor.class,"name")`; `ElapsedTime`; `DcMotor`/`Servo` API | **a "robot" that obeys typed commands** |
| 9 | Inheritance & Polymorphism | abstraction, "is-a" | `extends`, `super`, `@Override`, `abstract` | `extends LinearOpMode` / `OpMode`; why you override `runOpMode()`/`loop()` | subsystem hierarchy |
| 10 | Interfaces, Enums & Annotations | contracts, named states, metadata | `interface`, `enum`, annotations; **reading** lambdas/`Runnable` (light) | `@TeleOp`/`@Autonomous`; a `Subsystem` interface promising `update()`; `RunMode` & state enums | a command menu with an interface |
| 11 | Putting It Together — FTC-Style | integration, logging, state machines, **reading real code** | everything + a tiny custom logging/telemetry-style class | **anatomy of a real OpMode line-by-line**; `LinearOpMode` vs iterative `OpMode`; state machine (`enum`+`switch`+`ElapsedTime`); Java-21-vs-FTC language note | **capstone: simulated robot + explain a real OpMode** |

## Cross-cutting threads
- **Logging/telemetry habit:** a ~15-line **student-built `Log` class** (level + timestamp over `System.out`, shaped like FTC's `addData`/`update`) introduced in Phase 6 and reused. Phase 11 notes real options exist (`java.util.logging`, SLF4J, FTC's `RobotLog`) — and that **telemetry ≠ logging** in FTC.
- **Debugging** grows each phase: named top compiler errors (`cannot find symbol`, `; expected`, `incompatible types`) and runtime ones (`NullPointerException`, `ArrayIndexOutOfBounds`).
- **Read a real OpMode** sidebar from Phase 4 on.
- **Glossary** (`docs/glossary.md`) and a **skills tracker** (`docs/PROGRESS.md`) for a solo learner's momentum.

## Assessment
- Per-phase `CHECKPOINT.md` gates progress, **with worked answers/criteria** (learner is often unsupervised).
- Capstones after Phase 6 (match scorer) and Phase 11 (simulated robot + read/explain a real OpMode).

## Out of scope (v1)
Generics *authoring* (only light use/reading), concurrency/threads, networking, unit-test frameworks (shown, not taught), full Gradle/Android Studio mastery, PID control (encoders are in reach; PID is not).

## Change log (v0.1 → v1.0)
- **Split** the overloaded OOP phase → Phase 9 (Inheritance & Polymorphism) + Phase 10 (Interfaces/Enums/Annotations); curriculum grew 10→11. *(all 3 reviewers)*
- **Reworked Phase 3**: replaced Scanner-as-gamepad with the **polling/loop model**; Scanner flagged as console-only scaffolding. *(FTC mentor, Java eng.)*
- **Added** `ElapsedTime`, `hardwareMap` (moved to Phase 8, out of Collections), DcMotor/Servo API, exceptions/try-catch/NPE, packages/imports, access modifiers, static-vs-instance, LinearOpMode-vs-iterative, an **OpMode anatomy** teardown. *(FTC mentor, Java eng.)*
- **Elevated** "Common Traps" to a required, error-text-carrying section; added mental-model beats. *(CS educator)*
- **Added** frequent tangible wins + self-grading checkpoints. *(CS educator)*
- **Tooling**: default `java File.java` + `jshell`; Temurin 21 with an FTC language-level caveat; custom logging class over JUL/SLF4J. *(Java eng.)*
