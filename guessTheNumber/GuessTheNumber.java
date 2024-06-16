package guessTheNumber;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Enter the Number of round you want to play: ");
        int maxRounds = scanner.nextInt();
        int totalScore = 0;

        for (int round = 1; round <= maxRounds; round++) {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 10;
            boolean hasGuessedCorrectly = false;

            System.out.println("Round " + round + ":");
            System.out.println("Guess the number between 1 and 100:");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    hasGuessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            if (hasGuessedCorrectly) {
                int score = (maxAttempts - attempts + 1) * 10;
                totalScore += score;
                System.out.println("Congratulations! You've guessed the number in " + attempts + " attempts. Your score: " + score);
            } else {
                System.out.println("Sorry, you've used all attempts. The number was: " + numberToGuess);
            }
        }

        System.out.println("Game over! Your total score: " + totalScore +"/"+maxRounds*100);
        scanner.close();
    }
}