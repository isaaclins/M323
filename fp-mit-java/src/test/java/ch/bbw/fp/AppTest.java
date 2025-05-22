package ch.bbw.fp;

import junit.framework.TestCase;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Unit test for App.
 */
public class AppTest extends TestCase {

    public void testFilterAndForEach() {
        List<String> list = Arrays.asList("Apple", "Banana", "Cherry", "Date");
        List<String> result = list.stream()
                .filter(s -> s.startsWith("A") || s.startsWith("B"))
                .collect(Collectors.toList());
        assertEquals(2, result.size());
        assertTrue(result.contains("Apple"));
        assertTrue(result.contains("Banana"));
    }

    public void testMapAndCollect() {
        List<String> list = Arrays.asList("Apple", "Banana");
        List<String> result = list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        assertEquals(2, result.size());
        assertEquals("APPLE", result.get(0));
        assertEquals("BANANA", result.get(1));
    }

    public void testReduceAndOptional() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> sum = numbers.stream().reduce((a, b) -> a + b);
        assertTrue(sum.isPresent());
        assertEquals(Optional.of(15), sum);

        List<Integer> emptyList = Arrays.asList();
        Optional<Integer> emptySum = emptyList.stream().reduce((a, b) -> a + b);
        assertFalse(emptySum.isPresent());
    }

    public void testFlatMapAndMap() {
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d", "e"));
        List<String> result = listOfLists.stream()
                .flatMap(List::stream)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        assertEquals(5, result.size());
        assertEquals(Arrays.asList("A", "B", "C", "D", "E"), result);
    }

    public void testMathOperationLambda() {
        App.MathOperation addition = (a, b) -> a + b;
        assertEquals(8, addition.operate(5, 3));

        App.MathOperation subtraction = (a, b) -> a - b;
        assertEquals(2, subtraction.operate(5, 3));
    }

    public void testUserClassWithOptionalEmail() {
        App.User userWithEmail = new App.User("Alice", Optional.of("alice@example.com"));
        assertTrue(userWithEmail.getEmail().isPresent());
        assertEquals("alice@example.com", userWithEmail.getEmail().get());

        App.User userWithoutEmail = new App.User("Bob", Optional.empty());
        assertFalse(userWithoutEmail.getEmail().isPresent());
    }
}
