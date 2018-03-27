package mastermind;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static mastermind.MasterMind.Response.*;

public class MasterMind {

    protected int attemptsLeft = 20;

    public enum Response{ NO_MATCH, MATCH, POSITIONAL_MATCH }
    public enum GameStatus { IN_PROGRESS, WON, LOST }

    private GameStatus gameStatus = GameStatus.IN_PROGRESS;
    private final int SIZE = 6;
    private List<Color> solution;

    public MasterMind() {
        solution = generateRandomColors();

        //Color randomColor
        //Color randomColor;
        //solution = new ArrayList<Color>();
        //gameStatus = GameStatus.IN_PROGRESS;
        //for (int x = 0; x < 6; x++) {
        // randomColor = generateRandomColors();
        // solution.add(randomColor); }

    }

    public MasterMind(List<Color> selection) {
        solution = selection;
    }

    public Map<Response,Long> guess(List<Color> userGuess) {
        IntFunction<Response> computeMatchAtPosition = index ->
          solution.get(index) == userGuess.get(index) ? POSITIONAL_MATCH :
            userGuess.contains(solution.get(index)) ? MATCH : NO_MATCH;

        Map<Response, Long> response =
          IntStream.range(0, SIZE)
            .mapToObj(computeMatchAtPosition)
            .collect(groupingBy(Function.identity(), counting()));

        response.computeIfAbsent(NO_MATCH, key -> 0L);
        response.computeIfAbsent(MATCH, key -> 0L);
        response.computeIfAbsent(POSITIONAL_MATCH, key -> 0L);

        attemptsLeft--;            

        updateGameStatus(response);

        return response;
    }

    private void updateGameStatus (Map<Response, Long> response) {
        /*
        if (response.get(Response.POSITIONAL_MATCH) == 6 && gameStatus == GameStatus.IN_PROGRESS) {
            gameStatus = GameStatus.WON;
        }
        else if (attemptsLeft == 0) {
            gameStatus = GameStatus.LOST;
        }
       */
       if(gameStatus != GameStatus.IN_PROGRESS) return;

       if(response.get(Response.POSITIONAL_MATCH) == 6) {
           //if(gameStatus!=GameStatus.LOST)
                gameStatus = GameStatus.WON;
       }
        if(attemptsLeft == 0) {
           if(gameStatus!=GameStatus.WON)
            gameStatus = GameStatus.LOST;
        }
    }

    public List<Color> getAvailableColors() {
        List<Color> allColorList = Arrays.asList(Color.RED,Color.BLUE,Color.CYAN,Color.YELLOW,Color.GREEN,Color.ORANGE,Color.PINK,Color.MAGENTA,Color.BLACK,Color.GRAY);

        return allColorList;
    }

    protected List<Color> generateRandomColors() {

        List<Color> allColorList = getAvailableColors();
        Collections.shuffle(allColorList);
        return allColorList;

        //        List<Color> allColorList = getAvailableColors();
        //        Collections.shuffle(allColorList);
        //        return allColorList.get(0);
    }

    public List<Color> getSolution() { return solution; }

    public GameStatus getGameStatus() {return gameStatus;}

    public int getAttemptsLeft() {return attemptsLeft;}
}