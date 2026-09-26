// Phase 5 EXERCISE — worked solution: the number-guessing game.
// Run:   java GuessingGame.java

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int secret = random.nextInt(100) + 1;   // 1..100
        int guess = 0;                           // starts different from any 1..100
        int tries = 0;

        System.out.println("I'm thinking of a number from 1 to 100.");

        // Keep looping until the guess matches the secret.
        while (guess != secret) {
            System.out.print("Your guess: ");
            guess = scanner.nextInt();
            tries++;

            if (guess < secret) {
                System.out.println("Too low.");
            } else if (guess > secret) {
                System.out.println("Too high.");
            } else {
                System.out.println("Correct! You got it in " + tries + " tries.");
            }
        }

        scanner.close();

        // ---- STRETCH version (limited to 7 tries) would look like this: ----
        // int maxTries = 7;
        // boolean won = false;
        // for (int t = 1; t <= maxTries; t++) {
        //     System.out.print("Your guess (" + t + "/" + maxTries + "): ");
        //     guess = scanner.nextInt();
        //     if (guess == secret) {
        //         System.out.println("Correct in " + t + " tries!");
        //         won = true;
        //         break;               // leave the loop early — we won
        //     } else if (guess < secret) {
        //         System.out.println("Too low.");
        //     } else {
        //         System.out.println("Too high.");
        //     }
        // }
        // if (!won) {
        //     System.out.println("Out of tries! The number was " + secret + ".");
        // }
    }
}
