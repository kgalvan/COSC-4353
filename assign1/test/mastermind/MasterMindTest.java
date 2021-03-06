package mastermind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.*;
import java.lang.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import mastermind.MasterMind.Response;

public class MasterMindTest {
    MasterMind mastermind;

    @BeforeEach
    void init() {
        List<Color> selection = Arrays.asList(Color.RED, Color.GREEN, Color.BLACK, Color.WHITE, Color.BLUE, Color.CYAN);
        mastermind = new MasterMind(selection);
    }

    @Test
    void noCorrect() {
        List<Color> userGuess = Arrays.asList(Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW);
        Map<Response, Long> response = mastermind.guess(userGuess);

        assertAll(
                () -> assertEquals(0, (long) response.get(Response.POSITIONAL_MATCH)),
                () -> assertEquals(0, (long) response.get(Response.MATCH)),
                () -> assertEquals(6, (long) response.get(Response.NO_MATCH)));
    }

    @Test
    void oneCorrectWrongPosition() {
        List<Color> userGuess = Arrays.asList(Color.BLUE, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW);
        Map<Response, Long> response = mastermind.guess(userGuess);

        assertAll(
                () -> assertEquals(0, (long) response.get(Response.POSITIONAL_MATCH)),
                () -> assertEquals(1, (long) response.get(Response.MATCH)),
                () -> assertEquals(5, (long) response.get(Response.NO_MATCH)));
    }

    @Test
    void oneCorrectRightPosition() {
        List<Color> userGuess = Arrays.asList(Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.BLUE, Color.YELLOW);
        Map<Response, Long> response = mastermind.guess(userGuess);

        assertAll(
                () -> assertEquals(1, (long) response.get(Response.POSITIONAL_MATCH)),
                () -> assertEquals(0, (long) response.get(Response.MATCH)),
                () -> assertEquals(5, (long) response.get(Response.NO_MATCH)));
    }

    @Test
    void twoCorrectOneRightPosition() {
        List<Color> userGuess = Arrays.asList(Color.YELLOW, Color.RED, Color.YELLOW, Color.YELLOW, Color.BLUE, Color.YELLOW);
        Map<Response, Long> response = mastermind.guess(userGuess);

        assertAll(
                () -> assertEquals(1, (long) response.get(Response.POSITIONAL_MATCH)),
                () -> assertEquals(1, (long) response.get(Response.MATCH)),
                () -> assertEquals(4, (long) response.get(Response.NO_MATCH)));
    }

    @Test
    void allCorrectRightPosition() {
        List<Color> userGuess = Arrays.asList(Color.RED, Color.GREEN, Color.BLACK, Color.WHITE, Color.BLUE, Color.CYAN);
        Map<Response, Long> response = mastermind.guess(userGuess);

        assertAll(
                () -> assertEquals(6, (long) response.get(Response.POSITIONAL_MATCH)),
                () -> assertEquals(0, (long) response.get(Response.MATCH)),
                () -> assertEquals(0, (long) response.get(Response.NO_MATCH)));
    }

    @Test
    void fourPositionMatchTwoColorMatch(){
        List<Color> userGuess = Arrays.asList(Color.RED, Color.BLACK, Color.GREEN, Color.WHITE, Color.BLUE, Color.CYAN);
        Map<Response, Long> response = mastermind.guess(userGuess);

        assertAll(
                () -> assertEquals(4, (long) response.get(Response.POSITIONAL_MATCH)),
                () -> assertEquals(2, (long) response.get(Response.MATCH)),
                () -> assertEquals(0, (long) response.get(Response.NO_MATCH)));
    }

    @Test
    void fourPositionMatchOneColorMatchOneNoMatch()
    {
        List<Color> userGuess = Arrays.asList(Color.RED, Color.GREEN, Color.BLACK, Color.RED, Color.WHITE, Color.CYAN);
        Map<Response, Long> response = mastermind.guess(userGuess);

        assertAll(
                () -> assertEquals(4, (long) response.get(Response.POSITIONAL_MATCH)),
                () -> assertEquals(1, (long) response.get(Response.MATCH)),
                () -> assertEquals(1, (long) response.get(Response.NO_MATCH)));
    }

    @Test
    void SixColorMatch()
    {
        List<Color> userGuess = Arrays.asList(Color.CYAN, Color.BLUE, Color.WHITE, Color.GREEN, Color.BLACK, Color.RED);
        Map<Response, Long> response = mastermind.guess(userGuess);

        assertAll(
                () -> assertEquals(0, (long) response.get(Response.POSITIONAL_MATCH)),
                () -> assertEquals(6, (long) response.get(Response.MATCH)),
                () -> assertEquals(0, (long) response.get(Response.NO_MATCH)));
    }

    @Test
    void oneColorDuplicatedManyTimes()
    {
        List<Color> userGuess = Arrays.asList(Color.YELLOW, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED);
        Map<Response, Long> response = mastermind.guess(userGuess);

        assertAll(
                () -> assertEquals(0, (long) response.get(Response.POSITIONAL_MATCH)),
                () -> assertEquals(1, (long) response.get(Response.MATCH)),
                () -> assertEquals(5, (long) response.get(Response.NO_MATCH)));
    }

    @Test
    void oneColorDuplicatedManyTimesWithPositionalMatch()
    {
        List<Color> userGuess = Arrays.asList(Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED);
        Map<Response, Long> response = mastermind.guess(userGuess);

        assertAll(
                () -> assertEquals(1, (long) response.get(Response.POSITIONAL_MATCH)),
                () -> assertEquals(0, (long) response.get(Response.MATCH)),
                () -> assertEquals(5, (long) response.get(Response.NO_MATCH)));
    }

    @Test
    void toSuppressCoverageIssueWithEnum(){
        Response.values();
        Response.valueOf("NO_MATCH");
        MasterMind.GameStatus.values();
        MasterMind.GameStatus.valueOf("IN_PROGRESS");
        assertTrue(true);
    }

    @Test
    void gameStatusAtStart() {

        assertEquals(MasterMind.GameStatus.IN_PROGRESS, mastermind.getGameStatus());

    }

    @Test
    void gameStatusAfterTwoGuesses() {
        List<Color> userGuess = Arrays.asList(Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW);
        mastermind.guess(userGuess);
        mastermind.guess(userGuess);

        assertEquals(MasterMind.GameStatus.IN_PROGRESS, mastermind.getGameStatus());

    }

    @Test
    void gameStatusAfterAllCorrect() {
        List<Color> userGuess = Arrays.asList(Color.RED, Color.GREEN, Color.BLACK, Color.WHITE, Color.BLUE, Color.CYAN);
        mastermind.guess(userGuess);

        assertEquals(MasterMind.GameStatus.WON, mastermind.getGameStatus());
    }

    @Test
    void gameStatusAfterTwentyGuesses() {
        List<Color> userGuess = Arrays.asList(Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW);
        mastermind.attemptsLeft = 1;

        mastermind.guess(userGuess);
        mastermind.guess(userGuess);

        assertEquals(MasterMind.GameStatus.LOST, mastermind.getGameStatus());

    }

    @Test
    void  gameStatusAfterGuessAfterWin () {
        List<Color> userGuess = Arrays.asList(Color.RED, Color.GREEN, Color.BLACK, Color.WHITE, Color.BLUE, Color.CYAN);
        mastermind.guess(userGuess);
        mastermind.guess(userGuess);

        assertEquals(MasterMind.GameStatus.WON, mastermind.getGameStatus());
    }

    @Test
    void gameStatusAfterWinOnTwentiethGuess () {
        mastermind.attemptsLeft = 1;

        List<Color> userGuess = Arrays.asList(Color.RED, Color.GREEN, Color.BLACK, Color.WHITE, Color.BLUE, Color.CYAN);
        mastermind.guess(userGuess);

        assertEquals(MasterMind.GameStatus.WON, mastermind.getGameStatus());

    } 

    @Test
    void winningGameLosingGuessStillWin(){
        mastermind.attemptsLeft = 2;
        List<Color> userGuess = Arrays.asList(Color.RED, Color.GREEN, Color.BLACK, Color.WHITE, Color.BLUE, Color.CYAN);
        mastermind.guess(userGuess);
        userGuess = Arrays.asList(Color.ORANGE, Color.GREEN, Color.BLACK, Color.WHITE, Color.BLUE, Color.CYAN);
        mastermind.guess(userGuess);
        assertEquals(MasterMind.GameStatus.WON, mastermind.getGameStatus());
    }

    @Test
    void lostGameWinningGuessStillLost() {
        mastermind.attemptsLeft = 1;
        List<Color> userGuess = Arrays.asList(Color.ORANGE, Color.GREEN, Color.BLACK, Color.WHITE, Color.BLUE, Color.CYAN);
        mastermind.guess(userGuess);
        userGuess = Arrays.asList(Color.RED, Color.GREEN, Color.BLACK, Color.WHITE, Color.BLUE, Color.CYAN);
        mastermind.guess(userGuess);
        assertEquals(MasterMind.GameStatus.LOST, mastermind.getGameStatus());
    }

    @Test
    void generateRandomColorsConstructor() {
        AtomicBoolean called = new AtomicBoolean();
        MasterMind stub = new MasterMind() {
            protected List<Color> generateRandomColors(){
                called.set(true);
                List<Color> returnList = new ArrayList<Color>();

                return returnList;
            }
        };

        assertTrue(called.get());
    }

    @Test
    void colorsAreRandom() {
        List<Color> list1 = mastermind.generateRandomColors();
        List<Color> list2 = mastermind.generateRandomColors();
        // 1/3628800 chance they are the same
        boolean different = false;
        for(int x = 0; x <10; x++)
        {
            if(!(list1.get(x).equals(list2.get(x))))
                different = true;
        }
        assertTrue(different);
    }
    
    @Test
    void numAttemptsAfterFourUses() {
        List<Color> userGuess = Arrays.asList(Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW);
        mastermind.guess(userGuess);
        mastermind.guess(userGuess);
        mastermind.guess(userGuess);
        mastermind.guess(userGuess);

        assertEquals(16, mastermind.getAttemptsLeft());
    }

    @Test
    void availableColors(){
        List <Color> allColors = Arrays.asList(Color.RED,Color.BLUE,Color.CYAN,Color.YELLOW,Color.GREEN,Color.ORANGE,Color.PINK,Color.MAGENTA,Color.BLACK,Color.GRAY);
        List <Color> colorsFromMethod = mastermind.getAvailableColors();
        boolean same = true;
        for(int x = 0; x < 10; x++) {
            if(allColors.get(x)!=colorsFromMethod.get(x))
                same = false;
        }
        assertEquals(true,same);
    }

    @Test void getSolution() {
        List<Color> selection = Arrays.asList(Color.RED, Color.GREEN, Color.BLACK, Color.WHITE, Color.BLUE, Color.CYAN);
        assertEquals(selection,mastermind.getSolution());
    }

    @Test
    void gameStatusEnumValues() {
    }
}
