// Phase 9 EXERCISE — a subsystem hierarchy with polymorphism.
// Goal: finish the classes so main() runs and prints EXACTLY:
//
//   [left_motor] init done
//   [claw] init done
//   [left_motor] spinning at power 0.60
//   [claw] servo -> position 0.90
//
// The framework loop in main() only knows about `Mechanism`. Your two subclasses
// must OVERRIDE act() so each prints its own line. That's polymorphism.
//
// Run:   java Menagerie.java
// Check your work against exercises/solutions/Menagerie.java when done.

public class Menagerie {
    public static void main(String[] args) {
        Mechanism[] parts = {
            new Motor("left_motor", 0.60),
            new ClawServo("claw", 0.90)
        };

        for (Mechanism m : parts) m.init();   // inherited init() runs for both
        for (Mechanism m : parts) m.act();    // OVERRIDDEN act() runs the right version for each
    }
}

class Mechanism {
    protected final String name;              // shared state, visible to subclasses

    // TODO 1: write a constructor Mechanism(String name) that stores name in this.name.

    public void init() {
        System.out.println("[" + name + "] init done");   // inherited by everyone as-is
    }

    // TODO 2: add a method `act()` here (it can print a placeholder or be overridden).
    //         Subclasses will override it.
}

class Motor extends Mechanism {
    private final double power;

    // TODO 3: constructor Motor(String name, double power).
    //         Call super(name) FIRST, then store power.

    // TODO 4: @Override act() to print:   [<name>] spinning at power 0.60
    //         (use String.format or printf with %.2f for the power)
}

class ClawServo extends Mechanism {
    private final double position;

    // TODO 5: constructor ClawServo(String name, double position). super(name) first.

    // TODO 6: @Override act() to print:   [<name>] servo -> position 0.90
}
