// Phase 3 EXERCISE — worked solution.
// Run:   java MadLibs.java     then type answers when asked.

import java.util.Scanner;

public class MadLibs {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        // TODO 1
        System.out.print("Give me an adjective: ");
        String adjective = in.nextLine();

        // TODO 2
        System.out.print("Give me a noun (a thing): ");
        String noun = in.nextLine();

        // TODO 3
        System.out.print("Team number: ");
        int team = in.nextInt();

        // TODO 4
        System.out.print("Speed (a decimal like 0.75): ");
        double speed = in.nextDouble();

        // TODO 5 — use all four values.
        System.out.printf("Team %d's robot grabbed the %s %s at %.2f power!%n",
                team, adjective, noun, speed);

        in.close();
    }
}
