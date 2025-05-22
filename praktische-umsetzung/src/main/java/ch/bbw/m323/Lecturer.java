package ch.bbw.m323;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lecturer implements Comparable<Lecturer> {
    private String lecturerId;
    private String name;
    private String department;
    private List<Course> coursesTaught;

    public Lecturer(String lecturerId, String name, String department) {
        this.lecturerId = lecturerId;
        this.name = name;
        this.department = department;
        this.coursesTaught = new ArrayList<>();
    }

    // Getters
    public String getLecturerId() {
        return lecturerId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    // Setters
    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCoursesTaught(List<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    public void addCourse(Course course) {
        this.coursesTaught.add(course);
        if (course.getLecturer() != this) { // Maintain bi-directional consistency
            course.setLecturer(this);
        }
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "lecturerId='" + lecturerId + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", coursesTaughtCount=" + (coursesTaught != null ? coursesTaught.size() : 0) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Lecturer lecturer = (Lecturer) o;
        return Objects.equals(lecturerId, lecturer.lecturerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lecturerId);
    }

    @Override
    public int compareTo(Lecturer other) {
        return this.lecturerId.compareTo(other.lecturerId); // Natural order by ID
    }
}
