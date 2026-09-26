// Phase 2 — types on a robot: motor power is a double, encoder ticks are an int.
// Run:   java MotorMath.java

public class MotorMath {
    public static void main(String[] args) {

        // Motor power is a double from -1.0 (full reverse) to 1.0 (full forward).
        double power = 0.5;                 // half speed forward
        System.out.println("power = " + power);

        double half = power / 2;            // 0.25  (doubles keep the fraction — good)
        System.out.println("half power = " + half);

        // Encoder counts ("ticks") are whole numbers -> int.
        int ticksPerRev = 1120;             // this motor reports 1120 ticks per wheel turn
        int ticks = 3360;                   // we've counted 3360 ticks so far

        // How many full revolutions is that? Watch the types!
        int wrongRevs = ticks / ticksPerRev;           // int / int -> 3 (loses any fraction)
        double revs   = (double) ticks / ticksPerRev;  // cast first -> 3.0 here
        System.out.println("wrongRevs = " + wrongRevs);
        System.out.println("revs      = " + revs);

        // A realistic case where the trap actually bites:
        int ticks2 = 1680;
        int wrong    = ticks2 / ticksPerRev;            // 1   (should be 1.5!)
        double right = (double) ticks2 / ticksPerRev;   // 1.5
        System.out.println("wrong = " + wrong + ", right = " + right);
    }
}
