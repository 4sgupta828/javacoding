// Phase 2 — the everyday types, operators, casting, and the integer-division trap.
// Run:   java Types.java

public class Types {
    public static void main(String[] args) {

        // A variable is a LABELED BOX: it holds one value of one type.
        int ticks = 1120;        // int    = whole numbers (no decimal point)
        double power = 0.75;     // double = numbers WITH a decimal point
        boolean armUp = true;    // boolean = only true or false
        String name = "Servo";   // String = text, in DOUBLE quotes
        char grade = 'A';        // char   = exactly ONE character, in SINGLE quotes (rare; brief mention)

        System.out.println("ticks = " + ticks);
        System.out.println("power = " + power);
        System.out.println("armUp = " + armUp);
        System.out.println("name  = " + name);
        System.out.println("grade = " + grade);

        // ---- Operators ----
        System.out.println(2 + 3);   // 5
        System.out.println(2 * 3);   // 6
        System.out.println(7 % 3);   // 1  (%  = remainder: 7 / 3 leaves 1 left over)

        // ---- THE INTEGER-DIVISION TRAP ----
        // When BOTH sides are int, Java throws away the fractional part (no rounding).
        System.out.println(1 / 2);   // prints 0, NOT 0.5  !!
        System.out.println(7 / 2);   // prints 3, NOT 3.5

        // Fix: make at least ONE side a double.
        System.out.println(1.0 / 2); // 0.5
        System.out.println(1 / 2.0); // 0.5

        // ---- Casting: converting between types on purpose ----
        double exact = 3.9;
        int chopped = (int) exact;   // (int) CHOPS the decimal -> 3 (it does NOT round to 4)
        System.out.println(chopped); // 3

        int a = 7, b = 2;
        double answer = (double) a / b;  // cast a to double FIRST, then divide -> 3.5
        System.out.println(answer);      // 3.5
    }
}
