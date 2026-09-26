// Phase 4 — a joystick deadzone check: the decision shape you'll use on the robot.
// A stick almost never rests at exactly 0.0, so tiny values would make the robot
// creep. We use an `if` to treat anything close to zero as zero.
// Run:   java Deadzone.java

public class Deadzone {
    public static void main(String[] args) {

        // Pretend stick readings (like -gamepad1.left_stick_y over a few ticks).
        double[] readings = { 0.0, 0.02, -0.03, 0.40, -0.90 };

        for (int i = 0; i < readings.length; i++) {
            double stick = readings[i];

            // Math.abs(x) = distance from zero (turns -0.03 into 0.03).
            // If the stick is within 0.05 of center, ignore it.
            double power;
            if (Math.abs(stick) < 0.05) {
                power = 0.0;        // inside the deadzone -> full stop
            } else {
                power = stick;      // outside -> use the real value
            }

            System.out.printf("stick=%+.2f -> power=%+.2f%n", stick, power);
        }
    }
}
