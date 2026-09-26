// Phase 4 EXERCISE — Rock, Paper, Scissors vs. the computer.
// Goal: read the player's move, pick a random move for the computer, and use
//       if / else if / else with && and || to decide who wins.
//
// Run:   java RockPaperScissors.java     then type rock, paper, or scissors.
// Check your work against exercises/solutions/RockPaperScissors.java when done.

import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Choose rock, paper, or scissors: ");
        String player = in.nextLine();

        // Pick a random move for the computer: (int)(Math.random() * 3) -> 0, 1, or 2.
        int roll = (int) (Math.random() * 3);
        String computer;
        if (roll == 0)      computer = "rock";
        else if (roll == 1) computer = "paper";
        else                computer = "scissors";

        System.out.println("Computer chose: " + computer);

        // TODO 1: handle the TIE first — if player and computer are the SAME move,
        //         print "Tie!" and stop. (Remember: compare Strings with .equals(),
        //         not ==.)

        // TODO 2: decide if the PLAYER wins. The player wins in exactly these cases:
        //           player rock     AND computer scissors
        //           player paper    AND computer rock
        //           player scissors AND computer paper
        //         Use .equals() joined with && and ||. Print "You win!" or "You lose!".

        // TODO 3 (STRETCH): if the player typed something that isn't rock/paper/scissors,
        //         print "That's not a move!" instead of guessing.

        in.close();
    }
}
