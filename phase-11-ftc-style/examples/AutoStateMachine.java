// Phase 11 — a STATE MACHINE for autonomous, using enum + switch + a simulated timer.
// The "robot" drives forward for 2s, turns for 1s, then stops — printing telemetry each loop.
// Run:   java AutoStateMachine.java
//
// A state machine is just: "I'm in one named state at a time; when a condition is met,
// I move to the next state." Autonomous routines are built exactly this way.

public class AutoStateMachine {

    // The named steps of the routine (Phase 10: an enum is a fixed set of states).
    enum AutoState { DRIVE_FORWARD, TURN, STOP, DONE }

    public static void main(String[] args) {
        AutoState state = AutoState.DRIVE_FORWARD;

        // ---- simulated stopwatch ------------------------------------------------
        // On a real robot you'd use FTC's ElapsedTime and read the wall clock.
        // Here we FAKE time so the demo is instant and repeatable: each loop = 0.1s.
        double time = 0.0;   // seconds spent in the CURRENT state
        double dt   = 0.1;   // how much time passes each loop

        double leftPower = 0, rightPower = 0;

        // The control loop. On a robot this is  while (opModeIsActive())  (Phase 5).
        while (state != AutoState.DONE) {

            // 1) ACT — choose motor powers for whatever state we're in right now.
            switch (state) {
                case DRIVE_FORWARD: leftPower = 1.0;  rightPower =  1.0; break;  // both forward
                case TURN:          leftPower = 1.0;  rightPower = -1.0; break;  // spin in place
                case STOP:          leftPower = 0.0;  rightPower =  0.0; break;
                case DONE:          break;
            }

            // 2) REPORT — telemetry ALWAYS matches the powers we just set.
            System.out.printf("t=%4.1fs   %-13s   L=%+.1f  R=%+.1f%n",
                              time, state, leftPower, rightPower);

            // 3) DECIDE — advance the clock; when this state's time is up, move on.
            time += dt;
            switch (state) {
                case DRIVE_FORWARD: if (time >= 2.0) { state = AutoState.TURN; time = 0; } break;
                case TURN:          if (time >= 1.0) { state = AutoState.STOP; time = 0; } break;
                case STOP:          state = AutoState.DONE; break;   // stop one loop, then finish
                case DONE:          break;
            }
        }
        System.out.println("Autonomous complete. Motors off.");
    }
}
