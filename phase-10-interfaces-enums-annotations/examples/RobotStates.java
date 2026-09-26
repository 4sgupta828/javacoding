// Phase 10 — ENUMS: a type with a FIXED SET of named values.
// Perfect for robot states: an arm is DOWN, MID, or UP — never "banana".
// Run:   java RobotStates.java

public class RobotStates {

    // A plain enum: the ONLY legal values are the three you list here.
    enum ArmState { DOWN, MID, UP }

    // Enums can also carry data + methods. Each RunMode "knows" if a driver controls it.
    enum RunMode {
        TELEOP(true),        // these call the constructor below...
        AUTONOMOUS(false);

        private final boolean driverControlled;
        RunMode(boolean driverControlled) {           // enum constructor
            this.driverControlled = driverControlled;
        }
        boolean isDriverControlled() { return driverControlled; }
    }

    // Given a state, return the arm's target height.
    // This is the classic `switch` STATEMENT — safe to use in FTC code.
    // Note: inside a switch on an enum you write DOWN, not ArmState.DOWN.
    static int heightFor(ArmState state) {
        switch (state) {
            case DOWN: return 0;
            case MID:  return 150;
            case UP:   return 300;
            default:   return 0;   // never happens, but keeps the compiler happy
        }
    }

    // The "next" state when the driver taps "up".
    static ArmState next(ArmState state) {
        switch (state) {
            case DOWN: return ArmState.MID;
            case MID:  return ArmState.UP;
            case UP:   return ArmState.UP;   // already at the top; stay
            default:   return ArmState.DOWN;
        }
    }

    public static void main(String[] args) {
        // .values() gives every value of the enum, in order — great for looping.
        System.out.println("All arm states and their heights:");
        for (ArmState s : ArmState.values()) {
            System.out.println("  " + s + " -> " + heightFor(s) + " ticks");
        }

        // Walk the arm up, one tap at a time.
        System.out.println("\nTapping 'up' three times:");
        ArmState arm = ArmState.DOWN;
        for (int i = 0; i < 3; i++) {
            arm = next(arm);
            System.out.println("  now: " + arm + " (" + heightFor(arm) + " ticks)");
        }

        // Enums that carry data:
        System.out.println("\nRun modes:");
        for (RunMode m : RunMode.values()) {
            System.out.println("  " + m + " — driver controlled? " + m.isDriverControlled());
        }
    }
}
