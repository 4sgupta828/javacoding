// Phase 5 EXERCISE — the number-guessing game. 🎯
// The computer picks a secret number from 1 to 100. You keep guessing
// until you get it. After each guess it says "Too low" or "Too high".
//
// This is your first real loop that runs an unknown number of times —
// exactly the shape of a robot loop: read input -> decide -> repeat.
//
// Run:   java GuessingGame.java
// Check your work against exercises/solutions/GuessingGame.java when done.

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // random.nextInt(100) gives 0..99, so + 1 makes it 1..100.
        int secret = random.nextInt(100) + 1;

        int guess = 0;     // 0 can never equal the secret (1..100), so the loop starts
        int tries = 0;

        System.out.println("I'm thinking of a number from 1 to 100.");

        // TODO 1: write a while loop that keeps going while guess != secret.
        //         Inside the loop:
        //           - print a prompt like "Your guess: "
        //           - read the number with:   guess = scanner.nextInt();
        //           - add 1 to tries
        //           - if guess < secret, print "Too low."
        //             else if guess > secret, print "Too high."
        //             else print "Correct! You got it in " + tries + " tries."

        // TODO 2: after the loop, close the scanner:   scanner.close();

        // STRETCH: give the player only 7 tries. Use break to leave the loop
        //          early, and after the loop check whether they ran out.
    }
}
