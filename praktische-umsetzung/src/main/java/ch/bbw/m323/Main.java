package ch.bbw.m323;

import ch.bbw.m323.model.Course;
import ch.bbw.m323.model.Lecturer;
import ch.bbw.m323.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
        public static void main(String[] args) {
                // Datengenerierung: 100 Studentendatensätze
                List<Student> students = DataGenerator.generateStudents(100);

                System.out.println("--- Generated Students (Unsorted) ---");
                students.forEach(System.out::println);

                // --- Minimale Anforderung: Sorting ---

                // Natural Order (Student.studentId)
                System.out.println("\n--- Sorted by Natural Order (studentId) ---");
                List<Student> studentsSortedById = new ArrayList<>(students);
                Collections.sort(studentsSortedById); // Uses compareTo in Student
                studentsSortedById.forEach(System.out::println);

                // Reverse Order (Student.studentId)
                System.out.println("\n--- Sorted by Natural Order (studentId) - Reverse ---");
                List<Student> studentsSortedByIdReversed = new ArrayList<>(students);
                Collections.sort(studentsSortedByIdReversed, Collections.reverseOrder());
                studentsSortedByIdReversed.forEach(System.out::println);

                // Comparator: Derived Class (Student.age)
                System.out.println("\n--- Sorted by Age (Comparator: Derived Class) ---");
                List<Student> studentsSortedByAge = new ArrayList<>(students);
                Collections.sort(studentsSortedByAge, new AgeComparator());
                studentsSortedByAge.forEach(System.out::println);

                // Comparator: Anonymous Class (Student.gpa descending)
                System.out.println("\n--- Sorted by GPA (Descending) (Comparator: Anonymous Class) ---");
                List<Student> studentsSortedByGpaDesc = new ArrayList<>(students);
                Collections.sort(studentsSortedByGpaDesc, new Comparator<Student>() {
                        @Override
                        public int compare(Student s1, Student s2) {
                                return Double.compare(s2.gpa(), s1.gpa()); // s2 vs s1 for descending
                        }
                });
                studentsSortedByGpaDesc.forEach(System.out::println);

                // Comparator: Lambda Expression (Student.lastName, then Student.firstName)
                System.out.println("\n--- Sorted by Last Name, then First Name (Comparator: Lambda) ---");
                List<Student> studentsSortedByName = new ArrayList<>(students);
                studentsSortedByName.sort((s1, s2) -> {
                        int lastNameCompare = s1.lastName().compareTo(s2.lastName());
                        if (lastNameCompare != 0) {
                                return lastNameCompare;
                        }
                        return s1.firstName().compareTo(s2.firstName());
                });
                studentsSortedByName.forEach(System.out::println);

                // Comparator: Comparator Chain (Student.major, then Student.gpa descending)
                // Example of ./Comparator-Chain similar logic
                System.out.println("\n--- Sorted by Major, then GPA (Descending) (Comparator: Chain) ---");
                List<Student> studentsSortedByMajorThenGpa = new ArrayList<>(students);
                studentsSortedByMajorThenGpa.sort(
                                Comparator.comparing(Student::major) // Sort by Major first
                                                .thenComparing(Student::gpa, Comparator.reverseOrder()) // Then by
                                                                                                        // GPA
                                                                                                        // descending
                );
                studentsSortedByMajorThenGpa.forEach(System.out::println);

                // Comparator: Using a comparator for enrollmentDate (Student.enrollmentDate)
                System.out.println("\n--- Sorted by Enrollment Date (Comparator: Lambda) ---");
                List<Student> studentsSortedByEnrollmentDate = new ArrayList<>(students);
                studentsSortedByEnrollmentDate.sort(Comparator.comparing(Student::enrollmentDate));
                studentsSortedByEnrollmentDate.forEach(System.out::println);

                // --- Erweiterte Anforderung 1: Sorting with Association (Lecturer and Course)
                // ---
                // Datengenerierung: Kurse und Dozenten
                System.out.println("\n\n--- Erweiterte Anforderung: Lecturer and Course Sorting ---");

                List<Lecturer> lecturers = DataGenerator.generateLecturers(5);
                List<Course> courses = DataGenerator.generateCourses(20, lecturers);

                System.out.println("\n--- Generated Lecturers (Unsorted) ---");
                final Map<Lecturer, Long> courseCountByLecturer = courses.stream()
                                .filter(c -> c.lecturer() != null)
                                .collect(Collectors.groupingBy(Course::lecturer, Collectors.counting()));

                lecturers.forEach(l -> {
                        long courseCount = courseCountByLecturer.getOrDefault(l, 0L);
                        System.out.println("Lecturer{" +
                                        "lecturerId='" + l.lecturerId() + '\'' +
                                        ", name='" + l.name() + '\'' +
                                        ", department='" + l.department() + '\'' +
                                        ", coursesTaughtCount=" + courseCount +
                                        '}');
                });

                System.out.println("\n--- Generated Courses (Unsorted, with assigned lecturers) ---");
                courses.forEach(System.out::println);

                // Sort Lecturers by the number of courses they teach (descending)
                System.out.println("\n--- Lecturers sorted by number of courses taught (descending) ---");
                List<Lecturer> lecturersByCourseCount = new ArrayList<>(lecturers);
                lecturersByCourseCount
                                .sort(Comparator.comparingInt(
                                                (Lecturer l) -> courseCountByLecturer.getOrDefault(l, 0L).intValue())
                                                .reversed());
                lecturersByCourseCount.forEach(l -> {
                        long courseCount = courseCountByLecturer.getOrDefault(l, 0L);
                        System.out.println("Lecturer{" +
                                        "lecturerId='" + l.lecturerId() + '\'' +
                                        ", name='" + l.name() + '\'' +
                                        ", department='" + l.department() + '\'' +
                                        ", coursesTaughtCount=" + courseCount +
                                        '}');
                });

                // Sort Courses by Lecturer's name, then by Course name
                System.out.println("\n--- Courses sorted by Lecturer Name, then Course Name ---");
                List<Course> coursesByLecturerThenName = new ArrayList<>(courses);
                coursesByLecturerThenName.sort(
                                Comparator
                                                .comparing((Course c) -> c.lecturer() != null
                                                                ? c.lecturer().name()
                                                                : "", // Handhabung von null Lecturers
                                                                Comparator.nullsLast(String::compareTo))
                                                .thenComparing(Course::courseName));
                coursesByLecturerThenName.forEach(System.out::println);

                // Sort Lecturers by their department, then by their name
                System.out.println("\n--- Lecturers sorted by Department, then Name ---");
                List<Lecturer> lecturersByDeptThenName = new ArrayList<>(lecturers);
                lecturersByDeptThenName.sort(
                                Comparator.comparing(Lecturer::department)
                                                .thenComparing(Lecturer::name));
                lecturersByDeptThenName.forEach(System.out::println);

                // --- End Erweiterte Anforderung 1 ---

                // --- Erweiterte Anforderung 2: Deep Dive into Java Sorting (Explanations) ---
                // Detaillierte Erklärungen zu Sortiermechanismen in Java
                System.out.println("\n\n--- Erweiterte Anforderung: Deep Dive into Java Sorting ---");

                System.out.println("\n1. How Java applies Natural Order when sorting:");
                System.out.println(
                                "   - When a class implements the `Comparable` interface, it defines its 'natural order' by implementing the `compareTo()` method.");
                System.out.println(
                                "   - `Collections.sort(list)` or `list.sort(null)` (for List<E extends Comparable<? super E>>) uses this `compareTo()` method of the elements.");
                System.out.println(
                                "   - Example: Our `Student` class implements `Comparable<Student>` and its `compareTo` sorts by `studentId`.");
                System.out.println("     `Collections.sort(studentsSortedById);` used this natural order.");
                System.out.println("   - The `compareTo` method should return:");
                System.out.println("     - a negative integer if this object < other object");
                System.out.println("     - zero if this object == other object");
                System.out.println("     - a positive integer if this object > other object");

                System.out.println("\n2. How Java applies Reverse Order to a class:");
                System.out.println(
                                "   - `Collections.reverseOrder()` returns a comparator that imposes the reverse of the natural ordering.");
                System.out.println(
                                "   - `Comparator.reverseOrder()` (for a given comparator) returns a comparator that imposes the reverse of that comparator's ordering.");
                System.out.println(
                                "   - Example: `Collections.sort(studentsSortedByIdReversed, Collections.reverseOrder());` sorted students by studentId in descending order.");
                System.out.println(
                                "   - If you have a specific comparator, e.g., `byGpa = Comparator.comparing(Student::gpa)`, you can reverse it with `byGpa.reversed()`.");
                System.out.println(
                                "     `students.sort(Comparator.comparing(Student::gpa).reversed());` would sort by GPA descending.");

                System.out.println("\n3. Dive into sorting in Java - How does Java sort? (Underlying Algorithms):");
                System.out.println(
                                "   - For Lists of Objects: `Collections.sort()` and `List.sort()` typically use **Timsort**.");
                System.out.println(
                                "     - Timsort is a hybrid stable sorting algorithm, derived from merge sort and insertion sort, designed to perform well on many kinds of real-world data.");
                System.out.println(
                                "     - It has a time complexity of O(n log n) in average and worst cases, and O(n) in the best case (already sorted/nearly sorted data).");
                System.out.println(
                                "   - For Arrays of Objects: `Arrays.sort(Object[])` also uses Timsort (since Java 7 for `Arrays.sort(Object[])`, previously merge sort).");
                System.out.println(
                                "   - For Arrays of Primitives: `Arrays.sort(primitive[])` uses different algorithms:");
                System.out.println(
                                "     - For `int[]`, `long[]`, `short[]`, `char[]`, `byte[]`, `float[]`, `double[]`: A highly optimized dual-pivot quicksort (since Java 7).");
                System.out.println(
                                "     - These are generally not stable, but stability is often not a concern for primitives unless associated with other data implicitly.");

                System.out.println("\n4. How Java solves/provides sorting capabilities:");
                System.out.println("   Java provides a flexible and powerful sorting framework through:");
                System.out.println("   a) The `Comparable` Interface (`java.lang.Comparable`):");
                System.out.println("      - Allows objects to define their own natural ordering.");
                System.out.println("      - Classes like `String`, `Integer`, `LocalDate` implement `Comparable`.");
                System.out.println(
                                "      - Used when you want a default sort order for your objects (e.g., `Student` by `studentId`).");
                System.out.println("   b) The `Comparator` Interface (`java.util.Comparator`):");
                System.out
                                .println("      - Allows defining external, custom sort orders, or multiple sort orders for objects.");
                System.out.println("      - Can be implemented using:");
                System.out.println("         - Separate (derived) classes (e.g., `AgeComparator`).");
                System.out.println("         - Anonymous inner classes (e.g., sorting `studentsSortedByGpaDesc`).");
                System.out.println(
                                "         - Lambda expressions (since Java 8), making it very concise (e.g., sorting `studentsSortedByName`).");
                System.out.println("      - Provides utility methods for creating comparators: ");
                System.out
                                .println("         - `Comparator.comparing(Function)`, `Comparator.comparingInt(ToIntFunction)`, etc.");
                System.out.println(
                                "         - `thenComparing(Comparator)` for multi-level sorting (e.g., `studentsSortedByMajorThenGpa`).");
                System.out.println("         - `reversed()` to get the reverse order of a comparator.");
                System.out.println("         - `nullsFirst(Comparator)` / `nullsLast(Comparator)` for handling nulls.");
                System.out.println("   c) Utility Methods in `Collections` and `Arrays` classes:");
                System.out.println(
                                "      - `Collections.sort(List<T> list)`: Sorts based on natural order if T implements Comparable.");
                System.out.println(
                                "      - `Collections.sort(List<T> list, Comparator<? super T> c)`: Sorts using the provided Comparator.");
                System.out.println("      - `Arrays.sort(...)`: Similar methods for arrays of objects and primitives.");
                System.out.println(
                                "      - `List.sort(Comparator<? super E> c)`: Default method in List interface (Java 8+).");
                System.out.println(
                                "   This combination of interfaces and utility methods provides a comprehensive way to handle various sorting needs in Java.");

                // --- End Erweiterte Anforderung 2 ---

                // Sicherstellung, dass alle Attribute für Sortierung verwendet wurden
                // (Testfall-Exposition)
                // studentId: Natural Order
                // age: AgeComparator (Derived Class)
                // gpa: Anonymous Class (desc), Lambda (asc in chain)
                // lastName, firstName: Lambda
                // major: Comparator Chain
                // enrollmentDate: Lambda

                // Mehrstufige Sortierungen (Testfall-Exposition)
                // - Sort by Last Name, then First Name (Lambda)
                // - Sort by Major, then GPA descending (Comparator Chain)
                // - Sort by Lecturer Name, then Course Name
                // - Sort by Department, then Lecturer Name
        }

        // Comparator as a derived class (for sorting by age)
        // Erfüllt Anforderung: Comparator als abgeleitete Klasse
        static class AgeComparator implements Comparator<Student> {
                @Override
                public int compare(Student s1, Student s2) {
                        return Integer.compare(s1.age(), s2.age());
                }
        }
}
