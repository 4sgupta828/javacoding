// Phase 8 EXERCISE — worked solution.
// Run:   java RobotCommands.java

public class RobotCommands {
    public static void main(String[] args) {
        Robot bot = new Robot("Wall-E");

        bot.status();

        System.out.println("> open claw");
        bot.openClaw();
        bot.status();

        System.out.println("> raise arm to 45");
        bot.raiseArm(45);
        bot.status();

        System.out.println("> close claw");
        bot.closeClaw();
        bot.status();

        // STRETCH:
        System.out.println("Robots built: " + Robot.robotsBuilt);
    }
}

class Claw {
    private boolean open;                         // TODO 1

    public Claw() {                               // TODO 2
        this.open = false;
    }

    public void open()  { this.open = true;  }    // TODO 3
    public void close() { this.open = false; }

    public String label() {                       // TODO 4
        return open ? "OPEN" : "CLOSED";
    }
}

class Arm {
    private int angleDeg;                          // TODO 5

    public Arm() {                                 // TODO 6
        this.angleDeg = 0;
    }

    public void raiseTo(int degrees) {             // TODO 7
        this.angleDeg = degrees;
    }

    public int getAngle() {
        return this.angleDeg;
    }
}

class Robot {
    public static int robotsBuilt = 0;             // STRETCH: shared by the class

    private final String name;                     // TODO 8
    private Claw claw;
    private Arm arm;

    public Robot(String name) {                    // TODO 9
        this.name = name;
        this.claw = new Claw();
        this.arm = new Arm();
        robotsBuilt++;
    }

    public void openClaw()  { claw.open();  }       // TODO 10
    public void closeClaw() { claw.close(); }
    public void raiseArm(int degrees) { arm.raiseTo(degrees); }

    public void status() {                          // TODO 11
        System.out.println(name + ": claw " + claw.label() + ", arm at " + arm.getAngle() + " deg");
    }
}
