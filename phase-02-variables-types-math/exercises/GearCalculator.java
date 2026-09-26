// Phase 2 EXERCISE — a gear-ratio / unit calculator.
// Goal: fill in the TODOs so the program prints the gear ratio and tick counts.
//
// Background: a small DRIVER gear turns a bigger DRIVEN gear. The gear ratio is
//   drivenTeeth / driverTeeth. A ratio of 3.0 means the output turns 3x slower,
//   so the motor must spin 3 times to turn the output shaft once.
//
// Run:   java GearCalculator.java
// Check your work against exercises/solutions/GearCalculator.java when done.

public class GearCalculator {
    public static void main(String[] args) {

        int driverTeeth = 20;          // teeth on the motor's gear (DRIVER)
        int drivenTeeth = 60;          // teeth on the wheel's gear (DRIVEN)
        int ticksPerMotorRev = 1120;   // encoder ticks for ONE motor revolution
        double targetOutputRevs = 2.5; // we want to turn the output shaft this many turns

        // TODO 1: compute the gear ratio = drivenTeeth / driverTeeth.
        //         Store it in a double called `gearRatio`.
        //         WATCH OUT for the integer-division trap! (Hint: cast one side.)

        // TODO 2: compute how many encoder ticks make ONE output revolution:
        //         ticksPerOutputRev = ticksPerMotorRev * gearRatio.
        //         Store it in a double called `ticksPerOutputRev`.

        // TODO 3: compute the ticks needed to reach targetOutputRevs:
        //         ticksNeeded = ticksPerOutputRev * targetOutputRevs.

        // TODO 4: print all three results, each with a label, e.g.:
        //         gearRatio = 3.0
        //         ticksPerOutputRev = 3360.0
        //         ticksNeeded = 8400.0

    }
}
