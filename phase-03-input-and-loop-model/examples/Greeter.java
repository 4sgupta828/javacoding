// Phase 3 — reading keyboard input with Scanner, plus String methods and printf.
// IMPORTANT: Scanner is CONSOLE-ONLY scaffolding. It does NOT exist on a robot!
//            (A robot never "waits" for you to type. See PollingVsBlocking.java.)
// Run:   java Greeter.java     then type your answers when asked.

import java.util.Scanner;   // borrow the Scanner class from Java's library

public class Greeter {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);   // make a Scanner that reads the keyboard

        System.out.print("What's your name? ");  // print (no newline) makes a tidy prompt
        String name = in.nextLine();              // BLOCKS here: waits until you press Enter

        System.out.print("What's your team number? ");
        int team = in.nextInt();                  // read a whole number

        // ---- String methods (a String can do things to itself) ----
        System.out.println("Hi, " + name.toUpperCase() + "!");          // ALL CAPS
        System.out.println("Your name has " + name.length() + " letters.");

        // ---- printf: a fill-in-the-blanks template ----
        //   %s = a String,  %d = an int,  %.2f = a double with 2 decimals,  %n = newline
        double avg = 123.0 / 4;
        System.out.printf("Team %d scored %.2f points on average.%n", team, avg);

        in.close();   // done reading
    }
}
