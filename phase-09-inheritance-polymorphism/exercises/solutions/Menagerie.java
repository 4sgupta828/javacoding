// Phase 9 EXERCISE — worked solution.
// Run:   java Menagerie.java

public class Menagerie {
    public static void main(String[] args) {
        Mechanism[] parts = {
            new Motor("left_motor", 0.60),
            new ClawServo("claw", 0.90)
        };

        for (Mechanism m : parts) m.init();
        for (Mechanism m : parts) m.act();
    }
}

class Mechanism {
    protected final String name;

    public Mechanism(String name) {              // TODO 1
        this.name = name;
    }

    public void init() {
        System.out.println("[" + name + "] init done");
    }

    public void act() {                          // TODO 2 — base version (overridden below)
        System.out.println("[" + name + "] (no action)");
    }
}

class Motor extends Mechanism {
    private final double power;

    public Motor(String name, double power) {    // TODO 3
        super(name);                             // parent constructor first
        this.power = power;
    }

    @Override                                    // TODO 4
    public void act() {
        System.out.printf("[%s] spinning at power %.2f%n", name, power);
    }
}

class ClawServo extends Mechanism {
    private final double position;

    public ClawServo(String name, double position) {  // TODO 5
        super(name);
        this.position = position;
    }

    @Override                                    // TODO 6
    public void act() {
        System.out.printf("[%s] servo -> position %.2f%n", name, position);
    }
}
