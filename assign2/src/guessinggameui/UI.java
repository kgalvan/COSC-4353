package guessinggameui;

import guessinggame.GuessingGame;
import guessinggame.SpellChecker;
import guessinggame.SpellCheckerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        Random randSeed = new Random();

        int seed = randSeed.nextInt();
        String wordChoice = " ";
        String scrambledWord;
        String guessWord = " ";
        Scanner in = new Scanner(System.in);
        int score = 0;

        SpellChecker spellCheckerService = new SpellCheckerService();
        System.out.println("Guessing Game");
        GuessingGame game = new GuessingGame(spellCheckerService);
        try {
            wordChoice = game.pickAWord(words, seed);
        } catch (IOException e) {
            e.printStackTrace();
        }
        scrambledWord = game.scramble(wordChoice, seed);
        System.out.println("Scrambled word is: " + scrambledWord);

        while (!wordChoice.equals(guessWord)) {

            System.out.println("Guess correct word");
            guessWord = in.nextLine();

            score = game.score(scrambledWord, guessWord);

            System.out.println("Current score: " + score);
        }
        if (wordChoice.equals(guessWord)) {
            System.out.println("You win!");
        }
    }

}
