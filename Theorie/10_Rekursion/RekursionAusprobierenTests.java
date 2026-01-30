import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RekursionAusprobierenTests {
    @ParameterizedTest
    @ValueSource(strings = {"ava", "otto"})
    public void isPalindromTest(String s) {
        assertTrue(RekursionAusprobieren.isPalindrom(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ava", "avA", "Otto", "otTo", "peter"})
    public void isNoPalindromTest(String s) {
        assertFalse(RekursionAusprobieren.isPalindrom(s));
    }
}
