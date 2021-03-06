package guessinggame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SpellCheckerTest {
  SpellChecker spellChecker;

    @BeforeEach
    void init() {
        spellChecker = new SpellCheckerService();
    }

    @Test
    void spellingIsCorrect () {
        assertTrue(spellChecker.isSpellingCorrect("good"));
    }

    @Test
    void spellingIsIncorrect() {
        assertFalse(spellChecker.isSpellingCorrect("ney"));
    }

    @Test
    void networkFailureException() throws IOException{
        SpellCheckerService spellChecker = spy(SpellCheckerService.class);
        String message = "network failure";
        when(spellChecker.getResponseFromURL(anyString())).thenThrow(new IOException(message));

        assertThrows(RuntimeException.class, () -> spellChecker.isSpellingCorrect("whatever"), message);
    }

    @Test
    void networkFailure() {
        SpellChecker spellChecker = spy(SpellCheckerService.class);
        String message = "network failure";
        when(spellChecker.isSpellingCorrect(anyString())).thenThrow(new RuntimeException(message));
        GuessingGame guessingGame = new GuessingGame(spellChecker);

        assertThrows(RuntimeException.class, () -> guessingGame.score("whatever","whatever"), message);
    }
}

