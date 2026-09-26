// Phase 10 — Interfaces as CONTRACTS.
// An interface is a PROMISE: "anything that is a Subsystem HAS an update() method."
// Run:   java Subsystems.java
//
// (Java doesn't care what order the classes appear in a file, so we put the class
//  with main() first — that's the one `java Subsystems.java` starts.)

public class Subsystems {
    public static void main(String[] args) {
        Arm arm = new Arm();
        Claw claw = new Claw();
        arm.setTarget(20);
        claw.toggle();

        // THE PAYOFF: we can hold different subsystems in ONE array,
        // because each one IS-A Subsystem. We don't care which is which —
        // we only care that each one PROMISED to have update().
        Subsystem[] robot = { arm, claw };

        for (int loop = 1; loop <= 3; loop++) {
            System.out.println("Loop " + loop + ":");
            for (Subsystem s : robot) {   // treat every part the same way
                s.update();               // the contract GUARANTEES this method exists
            }
        }
    }
}

// The CONTRACT. It lists methods but writes no bodies.
// Any class that says "implements Subsystem" MUST provide all of them.
interface Subsystem {
    String name();     // what is this subsystem called?
    void update();     // do one step of work (on a real robot this runs every loop)
}

// Arm KEEPS its promise: it provides both name() and update().
class Arm implements Subsystem {
    private int angle = 0;
    private int targetAngle = 0;

    public void setTarget(int t) { targetAngle = t; }

    @Override                        // annotation: "I'm fulfilling a method from the contract"
    public String name() { return "Arm"; }

    @Override
    public void update() {
        // pretend to move 5 degrees toward the target each loop
        if (angle < targetAngle) angle += 5;
        else if (angle > targetAngle) angle -= 5;
        System.out.println("  Arm moving... angle = " + angle + " (target " + targetAngle + ")");
    }
}

// Claw also keeps the promise, in its own way.
class Claw implements Subsystem {
    private boolean open = false;

    public void toggle() { open = !open; }

    @Override public String name() { return "Claw"; }

    @Override
    public void update() {
        System.out.println("  Claw is " + (open ? "OPEN" : "CLOSED"));
    }
}
