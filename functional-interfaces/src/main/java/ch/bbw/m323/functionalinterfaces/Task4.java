package ch.bbw.m323.functionalinterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Task4 {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        Stream<String> stream = words.stream();

        // filter: Returns a stream consisting of the elements of this stream that match
        // the given predicate.
        System.out.println("--- filter ---");
        words.stream()
                .filter(s -> s.startsWith("c"))
                .forEach(System.out::println);

        // map: Returns a stream consisting of the results of applying the given
        // function to the elements of this stream.
        System.out.println("\\n--- map ---");
        words.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        // reduce: Performs a reduction on the elements of this stream, using an
        // associative accumulation function, and returns an Optional describing the
        // reduced value.
        System.out.println("\\n--- reduce ---");
        Optional<String> concatenated = words.stream()
                .reduce((s1, s2) -> s1 + ", " + s2);
        concatenated.ifPresent(System.out::println);

        // count: Returns the count of elements in this stream.
        System.out.println("\\n--- count ---");
        long count = words.stream().count();
        System.out.println("Count: " + count);

        // anyMatch: Returns whether any elements of this stream match the provided
        // predicate.
        System.out.println("\\n--- anyMatch ---");
        boolean anyStartsWithA = words.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println("Any word starts with 'a': " + anyStartsWithA);

        // allMatch: Returns whether all elements of this stream match the provided
        // predicate.
        System.out.println("\\n--- allMatch ---");
        boolean allLengthGreaterThan3 = words.stream().allMatch(s -> s.length() > 3);
        System.out.println("All words have length > 3: " + allLengthGreaterThan3);

        // noneMatch: Returns whether no elements of this stream match the provided
        // predicate.
        System.out.println("\\n--- noneMatch ---");
        boolean noneStartsWithZ = words.stream().noneMatch(s -> s.startsWith("z"));
        System.out.println("No word starts with 'z': " + noneStartsWithZ);

        // findFirst: Returns an Optional describing the first element of this stream,
        // or an empty Optional if the stream is empty.
        System.out.println("\\n--- findFirst ---");
        Optional<String> first = words.stream().findFirst();
        first.ifPresent(s -> System.out.println("First word: " + s));

        // findAny: Returns an Optional describing some element of the stream, or an
        // empty Optional if the stream is empty.
        System.out.println("\\n--- findAny ---");
        Optional<String> any = words.stream().findAny();
        any.ifPresent(s -> System.out.println("Any word: " + s));
    }
}
