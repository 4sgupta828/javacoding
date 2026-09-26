# How to Use This Repo 🚀

**A guide for you — the learner.** This repo turns "I've never coded" into "I can read a real FTC robot program." Here's exactly how to work through it so it actually sticks.

> First time here? Read this whole page once (5 min). Then start Phase 1.

---

## 🗺️ The map

```
   YOU ARE HERE
        │
        ▼
  ┌───────────┐
  │  SET UP   │  install Java on your Mac  →  docs/getting-started.md
  └─────┬─────┘
        │
        ▼
  ╔═══════════════════════════════════════════════════════════════╗
  ║   TIER 1 · FOUNDATIONS → FTC-READY                             ║
  ║                                                               ║
  ║   1  Output        →  2  Variables    →  3  Input & Loop      ║
  ║        │                                        │             ║
  ║        └───────────────┐            ┌───────────┘             ║
  ║                        ▼            ▼                         ║
  ║   4  Decisions     →  5  Loops   →  6  Methods & Errors ⭐    ║
  ║        │                                        │             ║
  ║        └───────────────┐            ┌───────────┘             ║
  ║                        ▼            ▼                         ║
  ║   7  Arrays        →  8  Classes  →  9  Inheritance          ║
  ║        │                                        │             ║
  ║        └───────────────┐            ┌───────────┘             ║
  ║                        ▼            ▼                         ║
  ║  10  Interfaces/Enums  →  11  FTC-STYLE (read a real OpMode) ⭐⭐
  ╚═══════════════════════════════════════════════════════════════╝
        │
        ▼
   TIER 2 → 3 → 4 → 5   (intermediate → design → tooling → advanced)
                         see learningmethod.md
```

⭐ = a capstone you can show off.

**Go in order.** Each phase assumes the one before it. Don't skip.

---

## 🔁 The learning loop (do this for EVERY phase)

This is the whole method in one picture. Repeat it for each phase folder.

```
        ┌─────────────────────────────────────────────┐
        │                                             │
        ▼                                             │
   ① READ ──► ② RUN ──► ③ BREAK ──► ④ EXERCISE ──► ⑤ CHECKPOINT
   README     examples   on purpose   with TODOs      self-graded
   .md        (run them!) (see errors) (solutions/)    │
                                                       │
                                              pass? ───┘──► NEXT PHASE
                                              stuck? ──► ⑥ GET UNSTUCK (below)
```

| Step | What you do | Why |
|------|-------------|-----|
| ① **Read** | Read the phase `README.md` | Get the Big Idea + mental model |
| ② **Run** | Run every file in `examples/` with `java File.java` | Reading ≠ knowing. See it work. |
| ③ **Break** | Change values. Delete a `;`. Rerun. | Errors are teachers, not enemies. |
| ④ **Exercise** | Do the `exercises/` TODOs yourself first | This is where learning happens |
| ⑤ **Checkpoint** | Do `CHECKPOINT.md`, self-grade | Proof you're ready to move on |

> 🧠 **The golden rule:** *Type it, don't copy-paste it.* Your fingers learn what your eyes skim past.

---

## 📁 What's in each phase folder

```
phase-05-loops/
├── README.md        📖 START HERE — the lesson, traps, and FTC connection
├── examples/        ▶️  RUN THESE — small programs to run & tinker with
│   ├── Loops.java
│   └── TimesTable.java
├── exercises/       ✍️  DO THESE — starter files with TODOs
│   ├── GuessingGame.java
│   └── solutions/   ✅ peek ONLY after you've tried (worked answers)
└── CHECKPOINT.md    🏁 PROVE IT — self-graded tasks before moving on
```

**How to run any example** (from inside its folder):
```bash
java Loops.java
```
Quick experiment without a file? Use the REPL:
```bash
jshell
```

---

## 🛠️ When you get stuck (the debugging flowchart)

Everyone gets stuck. Stuck ≠ bad at this. Follow this:

```
   Something's wrong
          │
          ▼
   Did it even compile? ──No──► READ THE ERROR. Find the file + line number.
          │                     Check: missing ; ? typo? wrong CAPS? curly "quotes"?
          │Yes                  → See the phase's "🔍 Decode this error" section
          ▼
   Does it run but do the       ──► ADD PRINTS. System.out.println("here x=" + x);
   wrong thing?                     Run again. Where does reality differ from your
          │                          expectation? That's your bug.
          ▼
   Crashed with a red            ──► READ TOP TO BOTTOM. The FIRST line names the
   stack trace?                       problem (e.g. NullPointerException), the line
          │                           number tells you WHERE. (Phase 6 teaches this.)
          ▼
   Still stuck after 15 min?     ──► Rubber-duck it: explain the code out loud, line
                                      by line, to a pet/wall. You'll often catch it
                                      yourself. Then check docs/glossary.md.
```

**Top beginner errors and what they mean** (you'll meet these — that's normal):

| The error says | It usually means |
|----------------|------------------|
| `';' expected` | You forgot a semicolon |
| `cannot find symbol` | A typo, or you forgot an `import` |
| `incompatible types` | You put the wrong *type* in a box (e.g. a `double` into an `int`) |
| `NullPointerException` | You used an object you never set up (Phase 6 + Phase 8) |
| `ArrayIndexOutOfBounds` | You went past the end of an array (Phase 7) |

---

## 📅 A practice rhythm that works

You don't need to grind. Consistency beats marathons.

```
   A good week:
   ┌──────┬──────┬──────┬──────┬──────┬──────┬──────┐
   │ Mon  │ Tue  │ Wed  │ Thu  │ Fri  │ Sat  │ Sun  │
   ├──────┼──────┼──────┼──────┼──────┼──────┼──────┤
   │ read │ run+ │ rest │ exer-│ check│ build│ rest │
   │ ①    │ break│      │ cise │ point│ some-│      │
   │      │ ②③   │      │ ④    │ ⑤    │ thing│      │
   └──────┴──────┴──────┴──────┴──────┴──────┴──────┘
   ~1–2 phases per week at a hobby pace. Some phases are bigger — that's fine.
```

- **20–40 min a sitting** beats a 3-hour cram.
- **End each session by running something.** Momentum is everything.
- **Stuck? Sleep on it.** Bugs solve themselves overnight surprisingly often.

---

## 📊 Track your progress

Open [`docs/PROGRESS.md`](docs/PROGRESS.md) and check boxes as you go. Watching it fill is the point.

```
   Tier 1 progress
   Phase:  1   2   3   4   5   6   7   8   9  10  11
          [■] [■] [■] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
                    ▲ you are here — 27% of the way to reading a real OpMode
```

Also keep your own notes in [`docs/glossary.md`](docs/glossary.md) — **writing your own definition of a new word is one of the best ways to lock it in.**

---

## 📖 How to read real robot code (your superpower goal)

From Phase 4 on, each README has a **"📖 Read a real OpMode"** section. Real code looks scary — do this:

```
   ┌─ Don't try to understand every line at once. ─┐
   │                                               │
   │  1. Find the SHAPE, not the details:          │
   │       set up hardware → wait → loop{ read,     │
   │       decide, set outputs }                    │
   │                                               │
   │  2. Highlight the lines you DO recognize.      │
   │       (More light up every phase.)             │
   │                                               │
   │  3. Name each piece:                           │
   │       "@TeleOp = annotation (Phase 10)"        │
   │       "extends = inheritance (Phase 9)"        │
   │       "hardwareMap.get(...) = object (Phase 8)"│
   └───────────────────────────────────────────────┘
```

Full guide: [`docs/reading-real-code.md`](docs/reading-real-code.md).

---

## 🎯 Uplevel: after you finish Tier 1

You'll be **FTC-ready** — able to write and read real OpMode code. To go further, the same 5-step loop applies to higher tiers:

```
   TIER 1  Foundations → FTC-ready          ◄── this repo, phases 1–11
   TIER 2  Intermediate  (generics, lambdas, streams)
   TIER 3  Design        (state/command/observer patterns)
   TIER 4  Tooling       (Gradle, JUnit, debugger)
   TIER 5  Advanced      (concurrency, vision, odometry)
```

Each tier is built with the exact method in [`learningmethod.md`](learningmethod.md). Want a new tier or extra practice phases? That file explains how to add them.

**Want to run code on a real robot sooner?** See "Two ways to run code on a real robot" in [`docs/reading-real-code.md`](docs/reading-real-code.md) — **OnBot Java** needs no Android Studio.

---

## ✅ The one-screen cheat sheet

```
   Setup once ........... docs/getting-started.md   (java -version → 21.x)
   Run a file ........... java File.java
   Quick experiment ..... jshell
   For EACH phase ....... READ → RUN → BREAK → EXERCISE → CHECKPOINT
   Golden rule .......... type it, don't paste it
   Stuck ................ read the error → add prints → read the stack trace → rubber-duck
   Track it ............. docs/PROGRESS.md
   Never .............. skip a phase, or copy a solution before trying
```

**Now go start [Phase 1](phase-01-getting-started/README.md). You've got this. 🤖**
