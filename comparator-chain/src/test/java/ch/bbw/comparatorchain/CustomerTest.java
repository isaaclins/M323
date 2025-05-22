package ch.bbw.comparatorchain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private List<Customer> customers;
    private List<Customer> expectedSortedCustomers;

    // Helper to parse dates without try-catch in test setup
    private Date parseDate(String dateStr) {
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {
        customers = new ArrayList<>();
        // Unsorted list
        customers.add(new Customer("Smith", "John", parseDate("1980-05-15"), 1.80, "555-1234"));
        customers.add(new Customer("Doe", "Jane", parseDate("1990-01-01"), 1.65, "555-5678"));
        customers.add(new Customer("Smith", "Alice", parseDate("1985-01-10"), 1.70, "555-8765")); // Younger Smith
        customers.add(new Customer("Apple", "Tim", parseDate("2000-01-01"), 1.75, "555-1111"));
        customers.add(new Customer("Doe", "Peter", parseDate("1988-12-31"), 1.90, "555-4321")); // Older Doe
        customers.add(new Customer("Smith", "Bob", parseDate("1980-05-15"), 1.72, "555-2222")); // Same last name and
                                                                                                // birthdate as John

        // Expected order: Lastname ASC, Birthdate DESC (younger first for same
        // lastname)
        expectedSortedCustomers = Arrays.asList(
                new Customer("Apple", "Tim", parseDate("2000-01-01"), 1.75, "555-1111"),
                new Customer("Doe", "Jane", parseDate("1990-01-01"), 1.65, "555-5678"),
                new Customer("Doe", "Peter", parseDate("1988-12-31"), 1.90, "555-4321"),
                new Customer("Smith", "Alice", parseDate("1985-01-10"), 1.70, "555-8765"),
                // For Smith John and Smith Bob with the same birthdate, their relative order is
                // not strictly defined by the comparator
                // as it only compares lastname and birthdate. However, Collections.sort is
                // stable, so their original relative order might be preserved if not otherwise
                // specified.
                // For simplicity in assertion, we list them. If the test fails here, it might
                // be due to instability if a different sort algo was used elsewhere.
                // Given the comparators, any order of these two is fine as long as Alice is
                // before them.
                new Customer("Smith", "John", parseDate("1980-05-15"), 1.80, "555-1234"),
                new Customer("Smith", "Bob", parseDate("1980-05-15"), 1.72, "555-2222"));
    }

    private void assertCorrectSort(List<Customer> sortedList) {
        assertEquals(expectedSortedCustomers.size(), sortedList.size(), "List sizes should match.");
        for (int i = 0; i < expectedSortedCustomers.size(); i++) {
            Customer expected = expectedSortedCustomers.get(i);
            Customer actual = sortedList.get(i);
            assertEquals(expected.getLastname(), actual.getLastname(), "Lastname mismatch at index " + i);
            assertEquals(expected.getBirthdate(), actual.getBirthdate(),
                    "Birthdate mismatch for " + expected.getLastname() + " at index " + i);
            // We don't need to check firstname, size, phone as they are not part of this
            // specific sort criteria
        }
    }

    @Test
    @DisplayName("Test Anonymous Class Comparator (LastName ASC, Birthdate DESC)")
    void testComparatorACLastNameBirthdate() {
        Collections.sort(customers, Customer.comparatorACLastNameBirthdate);
        assertCorrectSort(customers);
    }

    @Test
    @DisplayName("Test Lambda Expression Comparator (LastName ASC, Birthdate DESC)")
    void testComparatorLaLastNameBirthdate() {
        Collections.sort(customers, Customer.comparatorLaLastNameBirthdate);
        assertCorrectSort(customers);
    }

    @Test
    @DisplayName("Test Comparator Chain (LastName ASC, Birthdate DESC)")
    void testComparatorCcLastNameBirthdate() {
        Collections.sort(customers, Customer.comparatorCcLastNameBirthdate);
        assertCorrectSort(customers);
    }
}
