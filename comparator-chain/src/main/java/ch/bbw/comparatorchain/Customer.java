package ch.bbw.comparatorchain;

// import lombok.Value; // Removed Lombok import

import java.util.Comparator;
import java.util.Date;
import java.util.Objects; // Added for Objects.hash and Objects.equals

/**
 * @author Peter Rutschmann
 * @version 05.11.2023
 */
// @Value // Removed Lombok annotation
public class Customer implements Comparable<Customer> {
    private final String lastname;
    private final String firstname;
    private final Date birthdate;
    private final double size;
    private final String phone;

    // All-args constructor
    public Customer(String lastname, String firstname, Date birthdate, double size, String phone) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.size = size;
        this.phone = phone;
    }

    // Getter methods
    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public double getSize() {
        return size;
    }

    public String getPhone() {
        return phone;
    }

    // Existing comparators and compareTo method
    public static Comparator<Customer> comparatorACLastNameBirthdate = new Comparator<Customer>() {
        @Override
        public int compare(Customer o1, Customer o2) {
            int value = o1.getLastname().compareTo(o2.getLastname());
            if (value == 0) {
                // reversed order
                value = o2.getBirthdate().compareTo(o1.getBirthdate());
            }
            return value;
        }
    };

    public static Comparator<Customer> comparatorLaLastNameBirthdate = (o1, o2) -> {
        int value = o1.getLastname().compareTo(o2.getLastname());
        if (value == 0) {
            // reversed order
            value = o2.getBirthdate().compareTo(o1.getBirthdate());
        }
        return value;
    };

    public static Comparator<Customer> comparatorCcLastNameBirthdate = Comparator.comparing(Customer::getLastname)
            .thenComparing(Customer::getBirthdate, Comparator.reverseOrder());

    @Override
    public int compareTo(Customer o) {
        int value = this.getLastname().compareTo(o.getLastname());
        if (value == 0)
            value = this.getFirstname().compareTo(o.getFirstname());
        return value;
    }

    // equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customer customer = (Customer) o;
        return Double.compare(customer.size, size) == 0 &&
                Objects.equals(lastname, customer.lastname) &&
                Objects.equals(firstname, customer.firstname) &&
                Objects.equals(birthdate, customer.birthdate) &&
                Objects.equals(phone, customer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname, birthdate, size, phone);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", birthdate=" + birthdate +
                ", size=" + size +
                ", phone='" + phone + '\'' +
                '}';
    }
}
