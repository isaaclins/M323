package ch.bbw.m323.functionalinterfaces;

import org.junit.jupiter.api.Test;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoreTasksTest {

    @Test
    public void testAddTwo() {
        Function<Integer, Integer> addTwo = x -> x + 2;
        assertEquals(7, addTwo.apply(5));
    }

    @Test
    public void testSumUpTo() {
        Function<Integer, Integer> sumUpTo = n -> IntStream.rangeClosed(1, n).sum();
        assertEquals(15, sumUpTo.apply(5));
    }

    @Test
    public void testGrowth() {
        BiFunction<Integer, Integer, Integer> growth = (number, doublings) -> {
            int result = number;
            for (int i = 0; i < doublings; i++) {
                result *= 2;
            }
            return result;
        };
        assertEquals(48, growth.apply(3, 4));
    }

    @Test
    public void testDecay() {
        BiFunction<Integer, Integer, Integer> decay = (number, halvings) -> {
            int result = number;
            for (int i = 0; i < halvings; i++) {
                result /= 2;
            }
            return result;
        };
        assertEquals(3, decay.apply(24, 3));
    }
}
