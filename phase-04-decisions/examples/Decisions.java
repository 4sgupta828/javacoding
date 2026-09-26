// Phase 4 — if / else if / else, comparison operators, && || !, and switch.
// Run:   java Decisions.java

public class Decisions {
    public static void main(String[] args) {

        int score = 75;

        // ---- if / else if / else: check conditions top to bottom, take the FIRST match ----
        if (score >= 90) {
            System.out.println("Grade: A");
        } else if (score >= 80) {
            System.out.println("Grade: B");
        } else if (score >= 70) {
            System.out.println("Grade: C");     // 75 lands here
        } else {
            System.out.println("Grade: below C");
        }

        // ---- comparison operators make a boolean:  ==  !=  <  >  <=  >=  ----
        boolean passed = score >= 70;
        System.out.println("passed? " + passed);   // true

        // ---- combine booleans:   &&  = AND    ||  = OR    !  = NOT ----
        boolean batteryOk = true;
        boolean armClear  = false;
        if (batteryOk && !armClear) {               // battery ok AND arm NOT clear
            System.out.println("Battery fine, but arm isn't clear -> don't move.");
        }
        if (score >= 60 || batteryOk) {             // either side true -> whole thing true
            System.out.println("At least one condition passed.");
        }

        // ---- switch (light intro): pick ONE branch by an exact value ----
        int mode = 2;
        switch (mode) {
            case 1:
                System.out.println("mode 1: drive");
                break;                              // break stops us falling into the next case
            case 2:
                System.out.println("mode 2: turn");
                break;
            default:                                // runs if nothing else matched
                System.out.println("unknown mode");
        }
    }
}
