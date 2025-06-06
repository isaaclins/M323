package ch.bbw.m323.functionalinterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task3 {

    public static void main(String[] args) {
        // forEach: Performs the given action for each element of the Iterable until all
        // elements have been processed or the action throws an exception.
        System.out.println("--- forEach ---");
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        names.forEach(name -> System.out.println("Hello, " + name));

        // removeIf: Removes all of the elements of this collection that satisfy the
        // given predicate.
        System.out.println("\\n--- removeIf ---");
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Original numbers: " + numbers);
        numbers.removeIf(n -> n % 2 == 0); // Remove even numbers
        System.out.println("After removing even numbers: " + numbers);

        // replaceAll: Replaces each element of this list with the result of applying
        // the operator to that element.
        System.out.println("\\n--- replaceAll ---");
        List<String> greetings = new ArrayList<>(Arrays.asList("hello", "world", "java"));
        System.out.println("Original greetings: " + greetings);
        greetings.replaceAll(String::toUpperCase);
        System.out.println("Uppercase greetings: " + greetings);

        // sort: Sorts this list according to the order induced by the specified
        // Comparator.
        System.out.println("\\n--- sort ---");
        List<Integer> unsortedNumbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        System.out.println("Unsorted numbers: " + unsortedNumbers);
        unsortedNumbers.sort((n1, n2) -> n1.compareTo(n2)); // Natural order
        System.out.println("Sorted numbers: " + unsortedNumbers);
        unsortedNumbers.sort((n1, n2) -> n2.compareTo(n1)); // Reverse order
        System.out.println("Reverse sorted numbers: " + unsortedNumbers);
    }
}
