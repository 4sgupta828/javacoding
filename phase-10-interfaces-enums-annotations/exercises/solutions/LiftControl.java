// Phase 10 EXERCISE — worked solution.
// Run:   java LiftControl.java

public class LiftControl {

    // TODO 1 — the fixed set of named heights.
    enum LiftState { GROUND, LOW, HIGH }

    // The contract.
    interface Subsystem {
        void update();
    }

    // TODO 2 — Lift keeps the promise: it provides update().
    static class Lift implements Subsystem {
        private LiftState state = LiftState.GROUND;   // starts on the ground

        public void setState(LiftState s) { state = s; }

        // pick the height for the current state
        private int heightTicks() {
            switch (state) {
                case GROUND: return 0;
                case LOW:    return 100;
                case HIGH:   return 250;
                default:     return 0;
            }
        }

        @Override
        public void update() {
            System.out.println("Lift at " + state + " (" + heightTicks() + " ticks)");
        }
    }

    public static void main(String[] args) {
        // TODO 3 — build it, command it, run it through the contract.
        Lift lift = new Lift();
        lift.setState(LiftState.HIGH);

        Subsystem[] robot = { lift };     // could hold many subsystems here
        for (Subsystem s : robot) {
            s.update();
        }
    }
}
