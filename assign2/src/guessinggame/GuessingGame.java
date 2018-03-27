package guessinggame;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class GuessingGame{
    private SpellChecker spellChecker;

    public GuessingGame(SpellChecker SpellCheckerInput){
        spellChecker = SpellCheckerInput;
    }

    public int score(String scrambledWord, String guessWord){

        if(!spellChecker.isSpellingCorrect(guessWord)) {
            return 0;
        }

        String guessLowerCase = guessWord.toLowerCase();

        Map<String, Long> frequencyOfLettersInGuess = Stream.of(guessLowerCase.split(""))
          .collect(groupingBy(letter -> letter, counting()));

        Map<String, Long> frequencyOfLettersWord = Stream.of(scrambledWord.split(""))
          .collect(groupingBy(letter -> letter, counting()));

        if(frequencyOfLettersInGuess.keySet().stream()
          .filter(letter -> frequencyOfLettersInGuess.get(letter) > frequencyOfLettersWord.computeIfAbsent(letter, key -> 0L))
          .count() > 0) return 0;

        List<String> VOWELS = new ArrayList<String>();
        VOWELS.add("a");
        VOWELS.add("e");
        VOWELS.add("i");
        VOWELS.add("o");
        VOWELS.add("u");

        return Stream.of(guessLowerCase.split(""))
          .mapToInt(letter -> VOWELS.contains(letter) ? 1 : 2)
          .sum();
    } 
    
    public String scramble(String word, int seed) {
        char splitWord[] = word.toCharArray();
        Random r = new Random(seed);
        for(int i = 0; i < splitWord.length; i++) {
            int j = r.nextInt(splitWord.length);

            char temp = splitWord[i];
            splitWord[i] = splitWord[j];
            splitWord[j] = temp;
        }

        return new String(splitWord);
    }

    public String pickAWord(List<String> words, int seed) throws IOException {
        File file = new File("inputList.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String string;
        while ((string = br.readLine()) != null) {
            words.add(string);
        }
        Random r = new Random(seed);
        int index = r.nextInt(words.size() + 1);

        return words.get(index);
    }
}