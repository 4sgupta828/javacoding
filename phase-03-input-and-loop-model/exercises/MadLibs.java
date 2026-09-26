// Phase 3 EXERCISE — a Mad Libs generator (console scaffolding with Scanner).
// Goal: ask the user for a few words, then print a silly robot story using them.
//
// Remember: Scanner is console-only. A robot never waits for typed input —
//           this is just a fun way to practice input + Strings + printf.
//
// Run:   java MadLibs.java     then type answers when asked.
// Check your work against exercises/solutions/MadLibs.java when done.

import java.util.Scanner;

public class MadLibs {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        // TODO 1: prompt for an adjective and read it into a String called `adjective`.
        //         (Hint: System.out.print("...") then in.nextLine();)

        // TODO 2: prompt for a noun (a thing) and read it into `noun`.

        // TODO 3: prompt for a team number and read it into an int called `team`.
        //         (Hint: in.nextInt();)

        // TODO 4: prompt for a speed as a decimal and read it into a double called `speed`.
        //         (Hint: in.nextDouble();)

        // TODO 5: print a story that uses ALL FOUR values, for example:
        //         "Team 4828's robot grabbed the shiny cube at 0.75 power!"
        //         Use printf with %s, %d, and %.2f.

        in.close();
    }
}
