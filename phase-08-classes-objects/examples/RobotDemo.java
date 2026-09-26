// Phase 8 — a class is a BLUEPRINT; an object is a THING built from it.
// This file defines three classes (Claw, Arm, and the Robot that owns them),
// then main() builds objects and calls methods on them.
//
// Run:   java RobotDemo.java
//
// NOTE on running multi-class files: with `java File.java`, Java compiles every
// class in the file and runs main() from the FIRST class declared. So RobotDemo
// (the one with main) comes first. Only ONE class in a file may be `public`.

public class RobotDemo {                       // <-- has main(), declared first, matches file name
    public static void main(String[] args) {

        // "Robot" is a blueprint. This line builds one actual robot OBJECT.
        // `new Robot(...)` calls the constructor and hands back a reference we name `bot`.
        Robot bot = new Robot("Optimus");

        // We call METHODS on the object. Each method acts on THIS object's own state.
        bot.status();                          // Optimus: claw CLOSED, arm at 0 deg

        bot.openClaw();                         // change the claw's state
        bot.raiseArm(90);                       // change the arm's state
        bot.status();                          // Optimus: claw OPEN, arm at 90 deg

        // Build a SECOND object from the same blueprint. It has its OWN separate state.
        Robot backup = new Robot("Bumblebee");
        backup.status();                       // Bumblebee: claw CLOSED, arm at 0 deg  (unaffected by `bot`)

        // Static vs instance: the count belongs to the CLASS, not any one robot.
        System.out.println("Robots built so far: " + Robot.robotsBuilt);
    }
}

// ---------------------------------------------------------------------------
// A small subsystem class. A claw has ONE piece of state: open or closed.
class Claw {
    private boolean open;                       // FIELD (state). `private` = only Claw's code touches it.

    // Constructor: runs once when `new Claw()` is called. Sets the starting state.
    public Claw() {
        this.open = false;                      // `this.open` = THIS object's field (start closed)
    }

    public void open()  { this.open = true;  }  // instance methods change this object's state
    public void close() { this.open = false; }

    // A method that RETURNS a value the caller can use.
    public String label() {
        return open ? "OPEN" : "CLOSED";
    }
}

// ---------------------------------------------------------------------------
// Another subsystem: an arm that remembers its angle in degrees.
class Arm {
    private int angleDeg;                        // state: current angle

    public Arm() {
        this.angleDeg = 0;
    }

    public void raiseTo(int degrees) {
        this.angleDeg = degrees;                 // parameter `degrees` sets the field
    }

    public int getAngle() {
        return this.angleDeg;
    }
}

// ---------------------------------------------------------------------------
// The Robot class OWNS a Claw and an Arm (objects can hold other objects).
// This is exactly how a real FTC `Robot` class holds its subsystems.
class Robot {
    // static field: shared by the whole CLASS, not per object. Counts every robot ever built.
    public static int robotsBuilt = 0;

    private final String name;                   // `final` = set once (in the constructor), never changes
    private Claw claw;                           // this robot HAS a claw
    private Arm arm;                             // ...and an arm

    // Constructor: build the subsystems and store the name.
    public Robot(String name) {
        this.name = name;                        // `this.name` (field) = `name` (parameter)
        this.claw = new Claw();                  // each robot gets its OWN claw object
        this.arm = new Arm();
        robotsBuilt++;                           // bump the shared class-level counter
    }

    public void openClaw()  { claw.open();  }    // delegate to the subsystem
    public void closeClaw() { claw.close(); }
    public void raiseArm(int degrees) { arm.raiseTo(degrees); }

    // Print this robot's full state. Reads its subsystems' state via their methods.
    public void status() {
        System.out.println(name + ": claw " + claw.label() + ", arm at " + arm.getAngle() + " deg");
    }
}
