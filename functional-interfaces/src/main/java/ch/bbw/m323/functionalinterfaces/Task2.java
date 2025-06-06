package ch.bbw.m323.functionalinterfaces;

import java.util.function.*;

public class Task2 {

    public static void main(String[] args) {
        // Predicate<String>: boolean hasSpezialZeichen(String s)
        Predicate<String> hasSpecialChar = s -> s.matches(".*[^a-zA-Z0-9].*");
        System.out.println("Has special char 'abc.def': " + hasSpecialChar.test("abc.def"));
        System.out.println("Has special char 'abcdef': " + hasSpecialChar.test("abcdef"));

        // UnaryOperator<String>: String toLower(String s)
        UnaryOperator<String> toLower = String::toLowerCase;
        System.out.println("To lower 'HELLO': " + toLower.apply("HELLO"));

        // Supplier<String>: String getJodel()
        Supplier<String> jodelSupplier = () -> "hodeladiö";
        System.out.println("Jodel: " + jodelSupplier.get());

        // Consumer<String>: void print(String s)
        Consumer<String> printer = System.out::println;
        printer.accept("Printing this sentence.");

        // ToIntFunction<String>: int stringLength(String s)
        ToIntFunction<String> stringLength = String::length;
        System.out.println("Length of 'test': " + stringLength.applyAsInt("test"));

        // BiPredicate<Integer, Integer>: boolean beidePositiv(int i1, int i2)
        BiPredicate<Integer, Integer> bothPositive = (i1, i2) -> i1 > 0 && i2 > 0;
        System.out.println("Both positive (5, 10): " + bothPositive.test(5, 10));
        System.out.println("Both positive (-5, 10): " + bothPositive.test(-5, 10));

        // BinaryOperator<Integer>: int sum(int i1, int i2)
        BinaryOperator<Integer> sum = Integer::sum;
        System.out.println("Sum of 5 and 10: " + sum.apply(5, 10));
    }
}
