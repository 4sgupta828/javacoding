# Learning Method — how this curriculum is built, and how to grow it

This document captures the **method** behind this repo, not its content. It exists so anyone (a mentor, a future contributor, or a future AI session) can (a) understand *why* the curriculum is shaped the way it is, and (b) **extend it to higher levels of Java** using the same repeatable process.

Think of this as the "spec for making the spec."

---

## 1. Purpose

Teach a motivated beginner (originally: a 9th grader aiming at FTC robotics) to reach three outcomes, at every level:
1. **Write** Java from a blank file.
2. **Read** complex, unfamiliar, real-world Java and understand it.
3. **Debug** — read errors, instrument code, reason about failure.

The method is level-agnostic. It got a beginner from "what is a program" to "I can read an FTC OpMode." The *same method* takes them from there to intermediate, to design, to professional tooling, to advanced Java.

---

## 2. Core philosophy (the seven principles)

These are the load-bearing ideas. Every phase, at every level, obeys them.

1. **Fundamentals first, syntax second.** Teach the *idea* ("a loop repeats work until a condition changes") before the keyword (`for`). The idea transfers; the syntax is just today's spelling of it.
2. **Run early, run often.** The learner runs real code in the first lesson, not the fifth. Reading about code ≠ learning to code. Every example is meant to be run, changed, and broken on purpose.
3. **Spiral, not cliff.** Hard ideas get their own unit. Concepts reappear and deepen (loops show up again inside methods, inside classes, inside a robot loop). Never stack 5 hard concepts into one lesson.
4. **A real-world bridge in every unit.** Every concept ends with "here's where this shows up in the thing you actually want to build." For Tier 1 that's an FTC robot (`🤖 On the Robot`). Higher tiers point at real libraries and real engineering.
5. **Reading is a taught skill.** From the moment it's plausible, include *reading* real, unsimplified code and predicting its behavior — not just writing toy code. Highlight the lines you recognize; let more light up each unit.
6. **Traps are first-class.** Beginners are stopped by silent bugs and cryptic errors, not by syntax. Every unit has a required **"Common Traps"** section that shows the *real error text* and the *wrong output*, plus a **"Decode this error"** walkthrough.
7. **Frequent tangible wins.** Something showable or playable every 2–3 units, plus periodic capstones. Motivation is fuel; "I made a thing" is what sustains a self-driven learner.

---

## 3. The authoring process (how each level is produced)

This is the exact sequence used to build Tier 1. Reuse it for every new level.

```
   ┌─────────┐     ┌──────────┐     ┌───────────┐     ┌─────────┐     ┌──────┐
   │  DRAFT  │ ──► │  PANEL   │ ──► │   BUILD   │ ──► │ VERIFY  │ ──► │ SHIP │
   │  spec   │     │  review  │     │ to template│     │         │     │      │
   └─────────┘     └──────────┘     └───────────┘     └─────────┘     └──────┘
```

**Step 1 — Draft the spec.** Write a `SPEC.md`-style document: who it's for, the outcomes, the principles above, the tooling decisions, the per-unit template, the unit-by-unit table (concept → language surface → real-world bridge → tangible win), cross-cutting threads, and out-of-scope. End it with **explicit open questions for reviewers.**

**Step 2 — Panel it.** Review the draft from **three independent perspectives** before building anything. For Tier 1 these were:
- **The domain mentor** (FTC coach) — is the real-world bridge accurate and well-timed? What would leave the learner unable to read real code?
- **The pedagogy specialist** (CS educator) — is the sequencing prerequisite-clean? Any cognitive-overload unit? Are the "whys" strong? Which beginner traps are under-served?
- **The technical expert** (senior Java engineer) — is anything technically wrong or outdated? Are the tooling choices low-friction and correct? What's missing at the language level for reading real code?

Run the three in parallel, each producing a short critique ending in "Top 3 changes I'd make." **Synthesize the consensus and revise the spec** (record the changes in a change log). For higher tiers, swap the domain mentor for the relevant expert (a robotics software architect, a performance engineer, etc.).

**Step 3 — Build one exemplar unit fully, then the rest to match.** Author the *first* unit completely — it becomes the gold-standard template. Then build the remaining units to exactly that structure (parallelize if you can), each writer given the spec + the exemplar to match.

**Step 4 — Verify.** Confirm structure is uniform, class/file names are consistent, examples run (or are inspected carefully if no runtime is available), no smart quotes, cross-links resolve.

**Step 5 — Ship.** Commit, push, update the top-level roadmap and `docs/PROGRESS.md`.

---

## 4. The unit template (the atom of learning)

Every unit is a `phase-NN-name/` (or `levelN-phase-NN-name/`) folder:

```
phase-NN-name/
  README.md      the learning material (see section order below)
  examples/      1–3 small, heavily-commented, runnable programs
  exercises/     starter files with TODOs + solutions/ (worked answers)
  CHECKPOINT.md  3–5 "can you do this?" tasks WITH self-grading answers
```

`README.md` sections, **in this order** (this order is the method):
1. **The Big Idea** — the fundamental, plainly stated + why it matters.
2. **Mental model** — a picture/analogy ("variable = labeled box"; "class = blueprint, object = house").
3. **Worked examples** — heavily commented, pointing at the `examples/` files.
4. **⚠️ Common Traps** — *required*; each shows the real error text or wrong output.
5. **🔍 Decode this error** — one real compiler/runtime message pulled apart.
6. **🤖 On the Robot / In the Real World** — the concept in real, unsimplified code; where possible ends with "...you'll use this in <later unit>."
7. **📖 Read real code** — a real snippet to read and predict (once plausible).
8. **Self-check quiz** — with a `<details>` answers block (the learner is often solo).

**Non-negotiables:** every example runs; every checkpoint self-grades; every unit has a trap section with real errors; every unit has a real-world bridge.

---

## 5. How to unfold to higher levels

The current repo is **Tier 1: Foundations → FTC-ready** (11 phases). Higher tiers apply the identical method (sections 2–4) with a level-appropriate expert on the panel. Proposed roadmap:

### Tier 2 — Intermediate Java
- **Concepts:** generics *authoring* (not just using), lambdas & functional interfaces, the Streams API, `Optional`, deeper exceptions (custom exceptions, try-with-resources), file & data I/O, enums with behavior, modern features (records, sealed types, `switch` expressions — and *when they're safe* vs. FTC's older target).
- **Real-world bridge:** FTCLib command-based patterns, Road Runner trajectories, JSON configs.
- **Tangible wins:** a data-driven scouting/stats tool; a trajectory builder.

### Tier 3 — Software design
- **Concepts:** composition over inheritance, SOLID-lite, immutability, and the design patterns that *actually appear in robot code* — **State** (autonomous state machines), **Command** (command-based frameworks), **Observer** (button bindings/events), **Strategy** (swappable drive modes).
- **Real-world bridge:** architecting a full command-based robot; subsystem/command separation.
- **Tangible win:** refactor Tier 1's simulated robot into a clean command-based design.

### Tier 4 — Tooling & engineering practice
- **Concepts:** packages & the module system, **Gradle** (finally!), unit testing with **JUnit**, using a real debugger (breakpoints, watches), logging frameworks (`java.util.logging`, SLF4J) vs. telemetry, Git workflow.
- **Real-world bridge:** the actual FTC Android Studio / Gradle project; FTC Dashboard; PID tuning.
- **Tangible win:** stand up, test, and debug a real (or realistic) FTC project.

### Tier 5 — Advanced Java
- **Concepts:** concurrency & threads, performance & profiling, core data structures & algorithms, a taste of networking.
- **Real-world bridge:** computer vision (OpenCV / EasyOpenCV pipelines), odometry math, sensor fusion, loop-time budgets.
- **Tangible win:** a vision pipeline or an odometry localizer.

### To add a new unit or tier
1. Slot it into the roadmap table and write/extend the tier's `SPEC.md`.
2. **Panel it** (section 3, step 2) with the right experts.
3. Build to the template (section 4).
4. Verify, then update `README.md`'s roadmap and `docs/PROGRESS.md`.

**Rule of thumb for granularity:** if a single unit needs to teach more than ~2–3 genuinely hard ideas, split it. (Tier 1's original Phase 9 tried to teach five and was split into two after panel review — that's the method working.)

---

## 6. Quality bar (checklist for any new unit)

- [ ] Teaches a *fundamental*, not just syntax — the "why" is explicit.
- [ ] Has a mental model (analogy or diagram).
- [ ] Every example runs and is heavily commented.
- [ ] Has a **Common Traps** section with **real error text**.
- [ ] Has a **Decode this error** walkthrough.
- [ ] Has a **real-world bridge** to something the learner wants to build.
- [ ] Includes **read-real-code** practice (where plausible).
- [ ] Checkpoint tasks **self-grade** (answers included).
- [ ] Produces or builds toward a **tangible win**.
- [ ] Was **panel-reviewed** before building.
- [ ] Doesn't cram >2–3 hard concepts into one unit.

---

## 7. Why the panel matters (don't skip it)

The single highest-leverage step is the **panel review before building**. In Tier 1 it caught, among other things: an overloaded phase (split 10→11 units), a *wrong mental model* for input (blocking vs. polling) that would have crippled the "read real code" goal, and several missing load-bearing topics (exceptions, `hardwareMap`, timers). None of these were obvious from a single author's viewpoint. Three cheap parallel critiques changed the whole shape of the curriculum for the better. Always panel it.
