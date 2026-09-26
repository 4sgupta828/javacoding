// Phase 1 — print vs println, and the special characters \n and \t.
// Run:   java Output.java

public class Output {
    public static void main(String[] args) {

        // println adds a newline after each call, so these are on separate lines:
        System.out.println("Line one");
        System.out.println("Line two");

        System.out.println("-----");

        // print does NOT add a newline, so these run together on one line:
        System.out.print("no");
        System.out.print("space");
        System.out.print("here");
        System.out.println();   // an empty println just ends the line

        System.out.println("-----");

        // \n means "newline" and \t means "tab" — special characters inside a string:
        System.out.println("Robot status:\n\tMotors: OK\n\tSensors: OK");

        // You can join text with + :
        int motors = 4;
        System.out.println("This robot has " + motors + " drive motors.");
    }
}
