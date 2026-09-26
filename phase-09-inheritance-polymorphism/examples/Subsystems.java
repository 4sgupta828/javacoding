// Phase 9 — a subsystem hierarchy that mirrors how FTC OpModes work.
//
// `super` lets a subclass reuse the parent's setup, then add its own.
// This file also shows WHY @Override matters in FTC: a base class calls a method,
// and YOUR subclass supplies the real behavior. Same idea as `extends LinearOpMode`.
//
// Run:   java Subsystems.java

public class Subsystems {
    public static void main(String[] args) {

        // Each variable's DECLARED type is the parent (Subsystem), but the OBJECT
        // is a specific subclass. Polymorphism decides which init()/update() runs.
        Subsystem[] robot = {
            new Drivetrain("drive"),
            new Claw("claw")
        };

        // The "framework" loop: it only knows about Subsystem. It never mentions
        // Drivetrain or Claw — yet the right code runs for each. This is exactly how
        // the FTC SDK calls YOUR runOpMode()/loop() without knowing your class name.
        for (Subsystem s : robot) s.init();
        System.out.println("---- running ----");
        for (Subsystem s : robot) s.update();
    }
}

// Base class: shared state (name) + shared behavior (init), plus a promise (update).
class Subsystem {
    protected final String name;       // `protected` = visible to this class AND its subclasses

    public Subsystem(String name) {
        this.name = name;
    }

    public void init() {
        System.out.println("[" + name + "] base init: hardware handles acquired");
    }

    // Not abstract here (so Subsystem stays runnable on its own), but MEANT to be overridden.
    public void update() {
        System.out.println("[" + name + "] base update: (nothing to do)");
    }
}

// Drivetrain IS-A Subsystem, and adds its own behavior.
class Drivetrain extends Subsystem {
    public Drivetrain(String name) {
        super(name);                   // call the PARENT constructor first (required, must be line 1)
    }

    @Override
    public void init() {
        super.init();                  // reuse the parent's init...
        System.out.println("[" + name + "] ...then reset encoders and set motor directions");  // ...then add ours
    }

    @Override
    public void update() {
        System.out.println("[" + name + "] driving: leftPower=0.5 rightPower=0.5");
    }
}

// Claw IS-A Subsystem, and overrides update() differently.
class Claw extends Subsystem {
    public Claw(String name) {
        super(name);
    }

    @Override
    public void update() {
        System.out.println("[" + name + "] holding servo at position 0.20");
    }
    // NOTE: Claw does NOT override init(), so it INHERITS Subsystem's base init() unchanged.
}
