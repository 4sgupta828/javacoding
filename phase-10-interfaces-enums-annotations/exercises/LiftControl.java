// Phase 10 EXERCISE — build a mini subsystem driven by an enum state.
// Goal: a "Lift" that moves between three NAMED heights, treated through a contract.
//
// Run:   java LiftControl.java
// Check your work against exercises/solutions/LiftControl.java when done.

public class LiftControl {

    // TODO 1: define an enum called LiftState with exactly three values:
    //         GROUND, LOW, HIGH


    // This interface is your CONTRACT. Leave it exactly as-is.
    interface Subsystem {
        void update();
    }

    // TODO 2: make Lift implement Subsystem.
    //   - give it a LiftState field that starts at GROUND
    //   - add a method  setState(LiftState s)  that stores the new state
    //   - write update() so it prints:   Lift at <STATE> (<height> ticks)
    //       use a switch on the state to choose the height:
    //         GROUND -> 0,  LOW -> 100,  HIGH -> 250
    static class Lift /* implements Subsystem */ {

        // ... your fields and methods here ...

    }

    public static void main(String[] args) {
        // TODO 3: make a Lift, set it to HIGH.
        //         Put it in a  Subsystem[]  array.
        //         Loop over the array and call update() on every subsystem.
    }
}
