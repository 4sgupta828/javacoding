// Phase 6 — methods (parameters, return values, scope, overloading)
// and a first look at try/catch.
// Run:   java Methods.java

public class Methods {

    // A method with two PARAMETERS (a, b) that RETURNS an int.
    // "static" just means "belongs to the class, callable from main" for now.
    static int add(int a, int b) {
        return a + b;              // hand the result back to whoever called us
    }

    // OVERLOADING: same method name, DIFFERENT parameter types.
    // Java picks the right one based on the arguments you pass.
    static double add(double a, double b) {
        return a + b;
    }

    // A "void" method returns nothing — it just does something.
    static void greet(String name) {
        System.out.println("Hi, " + name + "!");
    }

    static int square(int x) {
        int result = x * x;        // 'result' is a LOCAL variable...
        return result;
    }                              // ...it stops existing when the method ends (scope).

    public static void main(String[] args) {

        System.out.println(add(2, 3));        // 5   -> calls the int version
        System.out.println(add(2.5, 0.5));    // 3.0 -> calls the double version (overload!)

        greet("driver");                      // Hi, driver!

        System.out.println(square(4));        // 16

        // System.out.println(result);        // ERROR: cannot find symbol —
        //                                       'result' only exists inside square().

        // ---- try/catch: attempt something risky, handle it if it fails ----
        String text = "banana";
        try {
            int number = Integer.parseInt(text);   // this THROWS NumberFormatException
            System.out.println("Parsed: " + number);   // <- skipped when it throws
        } catch (NumberFormatException e) {
            // We land here instead of crashing. 'e' is the exception object.
            System.out.println("Could not turn \"" + text + "\" into a number.");
        }

        System.out.println("The program keeps running after a caught exception.");
    }
}
