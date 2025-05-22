package ch.bbw.m323;

import java.time.LocalDate;
import java.util.Objects;

public class Student implements Comparable<Student> {
    private String studentId;
    private String firstName;
    private String lastName;
    private int age;
    private LocalDate enrollmentDate;
    private Major major;
    private double gpa;

    public Student(String studentId, String firstName, String lastName, int age, LocalDate enrollmentDate, Major major,
            double gpa) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.enrollmentDate = enrollmentDate;
        this.major = major;
        this.gpa = gpa;
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public Major getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    // Setters
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", enrollmentDate=" + enrollmentDate +
                ", major=" + major +
                ", gpa=" + gpa +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Student student = (Student) o;
        return age == student.age && Double.compare(student.gpa, gpa) == 0
                && Objects.equals(studentId, student.studentId) && Objects.equals(firstName, student.firstName)
                && Objects.equals(lastName, student.lastName) && Objects.equals(enrollmentDate, student.enrollmentDate)
                && major == student.major;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName, age, enrollmentDate, major, gpa);
    }

    // Natural order: by studentId
    @Override
    public int compareTo(Student other) {
        return this.studentId.compareTo(other.studentId);
    }
}
