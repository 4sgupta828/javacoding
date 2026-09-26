// Phase 8 EXERCISE — build a "robot" that obeys typed commands.
// Goal: finish the Claw, Arm, and Robot classes so main() below runs and prints:
//
//   Wall-E: claw CLOSED, arm at 0 deg
//   > open claw
//   Wall-E: claw OPEN, arm at 0 deg
//   > raise arm to 45
//   Wall-E: claw OPEN, arm at 45 deg
//   > close claw
//   Wall-E: claw CLOSED, arm at 45 deg
//
// Run:   java RobotCommands.java
// Check your work against exercises/solutions/RobotCommands.java when done.

public class RobotCommands {
    public static void main(String[] args) {
        Robot bot = new Robot("Wall-E");         // build one robot object

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
    }
}

class Claw {
    // TODO 1: add a private boolean field `open`.

    // TODO 2: write a constructor `Claw()` that starts the claw CLOSED (open = false).

    // TODO 3: write methods open() and close() that set the field.

    // TODO 4: write String label() that returns "OPEN" or "CLOSED".
}

class Arm {
    // TODO 5: add a private int field `angleDeg`.

    // TODO 6: constructor `Arm()` that starts angleDeg at 0.

    // TODO 7: raiseTo(int degrees) sets angleDeg; getAngle() returns it.
}

class Robot {
    // TODO 8: add fields: a private final String name, a private Claw, a private Arm.

    // TODO 9: constructor Robot(String name) that stores the name and builds a new Claw and Arm.

    // TODO 10: openClaw(), closeClaw(), raiseArm(int degrees) that delegate to the subsystems.

    // TODO 11: status() that prints:  <name>: claw <OPEN/CLOSED>, arm at <angle> deg

    // STRETCH: add a static counter `robotsBuilt` that goes up by 1 in the constructor,
    //          and print Robot.robotsBuilt at the end of main.
}
