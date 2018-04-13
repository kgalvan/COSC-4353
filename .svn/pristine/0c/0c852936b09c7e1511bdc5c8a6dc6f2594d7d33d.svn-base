package characterprocessor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharacterProcessorTest {
    @Test
    void Canary() {
        assertTrue(true);
    }

    @Test
    void ConvertsCharacterToUpper() {
        CharacterProcessor charProcessor = new CharacterProcessor();
        assertAll(
                () -> assertEquals("A", charProcessor.upperCaseConverter("a")),
                () -> assertEquals("AA", charProcessor.upperCaseConverter("aa")),
                () -> assertEquals("ABC", charProcessor.upperCaseConverter("aBc")),
                () -> assertEquals("A1B", charProcessor.upperCaseConverter("a1B"))
                );
    }

    @Test
    void ConvertsCharacterToLower() {
        CharacterProcessor charProcessor = new CharacterProcessor();

        assertAll(
                () -> assertEquals("a", charProcessor.lowerCaseConverter("a")),
                () -> assertEquals("aa", charProcessor.lowerCaseConverter("aa")),
                () -> assertEquals("abc", charProcessor.lowerCaseConverter("aBc")),
                () -> assertEquals("a1b", charProcessor.lowerCaseConverter("a1B"))
        );
    }
}
