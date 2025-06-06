package ch.bbw.m323.functionalinterfaces;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static List<Integer> filterNumbers(int[] numbers, MyIntPredicate predicate) {
        List<Integer> result = new ArrayList<>();
        for (int number : numbers) {
            if (predicate.test(number)) {
                result.add(number);
            }
        }
        return result;
    }

    public static void print(List<Integer> numbers) {
        numbers.forEach(System.out::println);
    }

    public static void main(String[] args) {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        System.out.println("Even numbers (anonymous class):");
        List<Integer> evenNumbers = filterNumbers(numbers, new MyIntPredicate() {
            @Override
            public boolean test(int i) {
                return i % 2 == 0;
            }
        });
        print(evenNumbers);

        System.out.println("\\nOdd numbers (lambda):");
        List<Integer> oddNumbers = filterNumbers(numbers, i -> i % 2 != 0);
        print(oddNumbers);

        System.out.println("\\nNumbers greater than 5 (lambda):");
        List<Integer> greaterThan5 = filterNumbers(numbers, i -> i > 5);
        print(greaterThan5);
    }
}
