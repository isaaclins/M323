package ch.bbw.m323.functionalinterfaces;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

public class MoreTasks {

    public static void main(String[] args) {
        // Aufgabe: Addieren Sie zu einer Zahl 2 hinzu
        Function<Integer, Integer> addTwo = x -> x + 2;
        System.out.println("5 + 2 = " + addTwo.apply(5));

        // Aufgabe: Summe bis n mit Hilfe einer Functional Interface
        Function<Integer, Integer> sumUpTo = n -> IntStream.rangeClosed(1, n).sum();
        System.out.println("Sum of 1 to 5 = " + sumUpTo.apply(5));

        // Aufgabe: Wachstum
        BiFunction<Integer, Integer, Integer> growth = (number, doublings) -> {
            int result = number;
            for (int i = 0; i < doublings; i++) {
                result *= 2;
            }
            return result;
        };
        System.out.println("Growth of 3 with 4 doublings = " + growth.apply(3, 4));

        // Aufgabe: Zerfall
        BiFunction<Integer, Integer, Integer> decay = (number, halvings) -> {
            int result = number;
            for (int i = 0; i < halvings; i++) {
                result /= 2;
            }
            return result;
        };
        System.out.println("Decay of 24 with 3 halvings = " + decay.apply(24, 3));
    }
}
