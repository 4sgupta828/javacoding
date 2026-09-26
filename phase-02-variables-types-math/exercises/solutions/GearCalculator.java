// Phase 2 EXERCISE — worked solution.
// Run:   java GearCalculator.java

public class GearCalculator {
    public static void main(String[] args) {

        int driverTeeth = 20;
        int drivenTeeth = 60;
        int ticksPerMotorRev = 1120;
        double targetOutputRevs = 2.5;

        // TODO 1: cast one side to double so we don't hit integer division.
        double gearRatio = (double) drivenTeeth / driverTeeth;   // 60 / 20 = 3.0

        // TODO 2: one output turn needs ticksPerMotorRev * gearRatio motor ticks.
        double ticksPerOutputRev = ticksPerMotorRev * gearRatio; // 1120 * 3.0 = 3360.0

        // TODO 3: scale up to the target number of output revolutions.
        double ticksNeeded = ticksPerOutputRev * targetOutputRevs; // 3360.0 * 2.5 = 8400.0

        // TODO 4: print the results.
        System.out.println("gearRatio = " + gearRatio);
        System.out.println("ticksPerOutputRev = " + ticksPerOutputRev);
        System.out.println("ticksNeeded = " + ticksNeeded);

        // Neater output with printf (%.1f = one decimal place):
        System.out.printf("To turn the output %.1f revs, run the motor %.0f ticks.%n",
                targetOutputRevs, ticksNeeded);
    }
}
