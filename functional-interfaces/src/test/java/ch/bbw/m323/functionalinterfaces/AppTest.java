package ch.bbw.m323.functionalinterfaces;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void testFilterNumbers_evenNumbers() {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Integer> evenNumbers = App.filterNumbers(numbers, i -> i % 2 == 0);
        assertEquals(List.of(2, 4, 6, 8, 10), evenNumbers);
    }

    @Test
    public void testFilterNumbers_oddNumbers() {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Integer> oddNumbers = App.filterNumbers(numbers, i -> i % 2 != 0);
        assertEquals(List.of(1, 3, 5, 7, 9), oddNumbers);
    }

    @Test
    public void testFilterNumbers_greaterThan5() {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Integer> greaterThan5 = App.filterNumbers(numbers, i -> i > 5);
        assertEquals(List.of(6, 7, 8, 9, 10), greaterThan5);
    }
}
