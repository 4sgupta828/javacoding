// Phase 4 EXERCISE — worked solution.
// Run:   java RockPaperScissors.java     then type rock, paper, or scissors.

import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Choose rock, paper, or scissors: ");
        String player = in.nextLine();

        int roll = (int) (Math.random() * 3);
        String computer;
        if (roll == 0)      computer = "rock";
        else if (roll == 1) computer = "paper";
        else                computer = "scissors";

        System.out.println("Computer chose: " + computer);

        // TODO 3 (done first): reject anything that isn't a real move.
        boolean valid = player.equals("rock")
                     || player.equals("paper")
                     || player.equals("scissors");
        if (!valid) {
            System.out.println("That's not a move!");
            in.close();
            return;   // stop here
        }

        // TODO 1: tie.
        if (player.equals(computer)) {
            System.out.println("Tie!");
        }
        // TODO 2: the three ways the player beats the computer.
        else if ((player.equals("rock")     && computer.equals("scissors"))
              || (player.equals("paper")    && computer.equals("rock"))
              || (player.equals("scissors") && computer.equals("paper"))) {
            System.out.println("You win!");
        }
        else {
            System.out.println("You lose!");
        }

        in.close();
    }
}
