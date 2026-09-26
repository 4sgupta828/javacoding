// Phase 3 — the ROBOT LOOP MODEL: polling, not blocking. (No robot needed.)
// A robot never stops to "ask and wait". ~50 times a second it READS the
// current value of every control and reacts. We fake that here with arrays of
// pretend readings and a loop that stands in for the robot's control loop.
// Run:   java PollingVsBlocking.java

public class PollingVsBlocking {
    public static void main(String[] args) {

        // Pretend the driver produced these readings over 6 loop ticks.
        // (On a real robot you'd read gamepad1.left_stick_y and gamepad1.a instead.)
        double[]  stickYReadings  = { 0.0, -0.5, -1.0, 0.0, 0.8, 0.0 };
        boolean[] aButtonReadings = { false, false, true, true, false, false };

        // This for-loop stands in for the robot's control loop.
        for (int tick = 0; tick < stickYReadings.length; tick++) {

            // POLLING: just read the controls' current values THIS tick.
            double  stickY   = stickYReadings[tick];
            boolean aPressed = aButtonReadings[tick];

            // A gamepad's Y axis is INVERTED: pushing UP gives a NEGATIVE number.
            // So forward drive power = -stickY.
            double drivePower = -stickY;

            System.out.printf("tick %d: stickY=%+.1f -> drivePower=%+.1f, A=%b%n",
                    tick, stickY, drivePower, aPressed);
        }

        System.out.println("Loop ended. Notice we NEVER waited for input —");
        System.out.println("we just read whatever the sticks/buttons were each tick.");
    }
}
