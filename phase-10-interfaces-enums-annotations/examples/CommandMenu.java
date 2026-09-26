// Phase 10 — TANGIBLE WIN: a command menu driven by an interface + an enum.
// Also shows how to READ lambdas / Runnable (modern FTC libraries use them a lot).
// Run:   java CommandMenu.java

public class CommandMenu {

    // A Command is a CONTRACT with exactly one method: "I can be run."
    // An interface with one method is called "functional" — it can be filled by a lambda.
    interface Command {
        void run();
    }

    // The fixed, named list of things the menu can do.
    enum MenuItem { OPEN_CLAW, CLOSE_CLAW, ARM_UP, QUIT }

    public static void main(String[] args) {
        // A LAMBDA is a tiny throwaway function.
        // Read   () -> { ... }   as  "a Command whose run() does { ... }".
        // This is EXACTLY the shape FTCLib uses:  button.whenPressed(() -> claw.open());
        Command openClaw  = () -> System.out.println("  Claw OPEN");
        Command closeClaw = () -> System.out.println("  Claw CLOSED");
        Command armUp     = () -> System.out.println("  Arm rising to 300 ticks");

        // Runnable is Java's built-in "() -> void" interface — same idea, standard name.
        Runnable startup = () -> System.out.println("Menu ready.");
        startup.run();

        // Pretend the driver picks these in order (no keyboard needed for the demo):
        MenuItem[] presses = { MenuItem.ARM_UP, MenuItem.OPEN_CLAW, MenuItem.CLOSE_CLAW, MenuItem.QUIT };

        for (MenuItem choice : presses) {
            System.out.println("Driver picked: " + choice);
            switch (choice) {                 // pick which command to run
                case OPEN_CLAW:  openClaw.run();  break;
                case CLOSE_CLAW: closeClaw.run(); break;
                case ARM_UP:     armUp.run();     break;
                case QUIT:
                    System.out.println("  Bye!");
                    return;                   // leaving main() ends the program
            }
        }
    }
}
