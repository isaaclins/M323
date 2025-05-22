package ch.bbw.comparatorchain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Customer> customers = new ArrayList<>();

        try {
            customers.add(new Customer("Smith", "John", sdf.parse("1980-05-15"), 1.80, "555-1234"));
            customers.add(new Customer("Doe", "Jane", sdf.parse("1992-08-20"), 1.65, "555-5678"));
            customers.add(new Customer("Smith", "Alice", sdf.parse("1980-05-15"), 1.70, "555-8765"));
            customers.add(new Customer("Brown", "Charlie", sdf.parse("1975-11-30"), 1.90, "555-4321"));
            customers.add(new Customer("Doe", "John", sdf.parse("1990-01-10"), 1.75, "555-9876"));
            customers.add(new Customer("Smith", "Peter", sdf.parse("1985-02-25"), 1.78, "555-1122"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Original list:");
        customers.forEach(System.out::println);

        // --- Solution WITHOUT Comparator-Chain (using lambda from Customer.java) ---
        // Create a mutable copy for sorting
        List<Customer> sortedCustomersLambda = new ArrayList<>(customers);
        System.out.println("\nSorted with Lambda (Lastname ASC, Birthdate DESC):");
        Collections.sort(sortedCustomersLambda, Customer.comparatorLaLastNameBirthdate);
        sortedCustomersLambda.forEach(System.out::println);

        // --- Solution WITH Comparator-Chain (using comparator chain from
        // Customer.java) ---
        // Create another mutable copy for sorting
        List<Customer> sortedCustomersChain = new ArrayList<>(customers);
        System.out.println("\nSorted with Comparator-Chain (Lastname ASC, Birthdate DESC):");
        Collections.sort(sortedCustomersChain, Customer.comparatorCcLastNameBirthdate);
        sortedCustomersChain.forEach(System.out::println);

        // --- Explanation for the incorrect comparator ---
        System.out.println("\n--- Explanation of incorrect comparator ---");
        System.out.println(
                "The comparator 'Comparator.comparing(Customer::getFirstname).thenComparing(Customer::getBirthdate).reversed()' does NOT sort as required (lastname ASC, birthdate DESC) because:");
        System.out.println("1. It primarily sorts by 'firstname', not 'lastname'.");
        System.out.println("2. The '.reversed()' method at the end reverses the ENTIRE comparison logic.");
        System.out.println(
                "   This means it sorts by 'firstname' (descending) and then by 'birthdate' (descending, if firstnames are equal).");
        System.out.println(
                "   To sort birthdate in reverse order for a specific field (like lastname), you should use 'thenComparing(Customer::getBirthdate, Comparator.reverseOrder())' after comparing by lastname.");
    }
}
