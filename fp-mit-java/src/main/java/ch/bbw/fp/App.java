package ch.bbw.fp;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Functional Programming with Java
 */
public class App {
    public static void main(String[] args) {
        System.out.println("--- Filter & ForEach ---");
        List<String> list = Arrays.asList("Apple", "Banana", "Cherry", "Date", "Elderberry");
        System.out.println("Original list: " + list);

        List<String> filteredList = list.stream()
                .filter(s -> s.startsWith("A") || s.startsWith("B"))
                .collect(Collectors.toList());

        System.out.println("Filtered list: " + filteredList);

        System.out.println("\n--- Map & Collect ---");
        List<String> mappedList = list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(mappedList);

        System.out.println("\n--- Reduce & Optional ---");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> sum = numbers.stream()
                .reduce((a, b) -> a + b);
        sum.ifPresent(s -> System.out.println("Sum: " + s));

        Optional<Integer> product = numbers.stream()
                .reduce((a, b) -> a * b);
        product.ifPresent(p -> System.out.println("Product: " + p));

        // Example with an empty list for reduce
        List<Integer> emptyList = Arrays.asList();
        Optional<Integer> emptySum = emptyList.stream()
                .reduce((a, b) -> a + b);
        System.out.println("Sum of empty list: " + (emptySum.isPresent() ? emptySum.get() : "N/A"));

        System.out.println("\n--- Lambda Expressions ---");
        // Lambda for a simple Runnable
        Runnable r = () -> System.out.println("Lambda Expression for Runnable");
        new Thread(r).start();

        // Lambda for a custom functional interface
        MathOperation addition = (a, b) -> a + b;
        System.out.println("5 + 3 = " + addition.operate(5, 3));

        System.out.println("\n--- FlatMap & Map ---");
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d", "e"),
                Arrays.asList("f", "g"));

        List<String> flatMappedList = listOfLists.stream()
                .flatMap(List::stream) // Flattens the stream of lists into a stream of strings
                .map(String::toUpperCase) // Maps each string to its uppercase version
                .collect(Collectors.toList());
        System.out.println(flatMappedList);

        // Another example for flatMap often used with Optional or other structures
        List<User> users = Arrays.asList(
                new User("Alice", Optional.of("alice@example.com")),
                new User("Bob", Optional.empty()),
                new User("Charlie", Optional.of("charlie@example.com")));

        List<String> emails = users.stream()
                .flatMap(user -> user.getEmail().stream()) // .stream() on Optional converts Optional<T> to Stream<T>
                                                           // (empty if Optional is empty)
                .collect(Collectors.toList());
        System.out.println("User emails: " + emails);
    }

    // Custom Functional Interface for Lambda example
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }

    // User class for flatMap example
    static class User {
        private String name;
        private Optional<String> email;

        public User(String name, Optional<String> email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public Optional<String> getEmail() {
            return email;
        }
    }
}
