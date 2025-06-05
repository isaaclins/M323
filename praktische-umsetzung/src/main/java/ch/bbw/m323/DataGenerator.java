package ch.bbw.m323;

import ch.bbw.m323.model.Course;
import ch.bbw.m323.model.Lecturer;
import ch.bbw.m323.model.Major;
import ch.bbw.m323.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private static final Random random = new Random();
    private static final String[] FIRST_NAMES = { "Alice", "Bob", "Charlie", "David", "Eve", "Fiona", "George",
            "Hannah", "Ivy", "Jack", "Prof. Albert", "Dr. Marie", "Dr. Isaac", "Prof. Lise", "Dr. Max" };
    private static final String[] LAST_NAMES = { "Smith", "Jones", "Williams", "Brown", "Davis", "Miller", "Wilson",
            "Moore", "Taylor", "Anderson", "Einstein", "Curie", "Newton", "Meitner", "Planck" };
    private static final Major[] MAJORS = Major.values();
    private static final String[] DEPARTMENTS = { "Physics", "Computer Science", "Mathematics", "Engineering",
            "Humanities" };
    private static final String[] COURSE_PREFIXES = { "PHY", "CS", "MAT", "ENG", "HUM" };
    private static final String[] COURSE_NAMES = { "Introduction to ", "Advanced ", "Principles of ", "Modern ",
            "Applied " };
    private static final String[] COURSE_SUFFIXES = { "Mechanics", "Programming", "Calculus", "Thermodynamics",
            "Literature", "Algorithms", "Quantum Physics", "Data Structures" };

    public static List<Student> generateStudents(int count) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String studentId = "S" + (10000 + random.nextInt(90000)); // 5 digit student ID
            String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
            int age = 18 + random.nextInt(13); // Age between 18 and 30
            LocalDate enrollmentDate = LocalDate.now().minusYears(random.nextInt(5)).minusMonths(random.nextInt(12))
                    .minusDays(random.nextInt(30));
            Major major = MAJORS[random.nextInt(MAJORS.length)];
            double gpa = Math.round((2.0 + random.nextDouble() * 2.0) * 100.0) / 100.0; // GPA between 2.0 and 4.0

            students.add(new Student(studentId, firstName, lastName, age, enrollmentDate, major, gpa));
        }
        return students;
    }

    public static List<Lecturer> generateLecturers(int count) {
        List<Lecturer> lecturers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String lecturerId = "L" + (1000 + random.nextInt(9000));
            String name = (random.nextBoolean() ? "Dr. " : "Prof. ") + FIRST_NAMES[random.nextInt(FIRST_NAMES.length)]
                    + " " + LAST_NAMES[random.nextInt(LAST_NAMES.length)];
            String department = DEPARTMENTS[random.nextInt(DEPARTMENTS.length)];
            lecturers.add(new Lecturer(lecturerId, name, department));
        }
        return lecturers;
    }

    public static List<Course> generateCourses(int count, List<Lecturer> lecturers) {
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String coursePrefix = COURSE_PREFIXES[random.nextInt(COURSE_PREFIXES.length)];
            String courseId = coursePrefix + (100 + random.nextInt(900));
            String courseName = COURSE_NAMES[random.nextInt(COURSE_NAMES.length)]
                    + COURSE_SUFFIXES[random.nextInt(COURSE_SUFFIXES.length)];
            int credits = 1 + random.nextInt(5); // 1 to 5 credits
            Lecturer lecturer = (lecturers != null && !lecturers.isEmpty())
                    ? lecturers.get(random.nextInt(lecturers.size()))
                    : null;
            courses.add(new Course(courseId, courseName, credits, lecturer));
        }
        return courses;
    }
}
