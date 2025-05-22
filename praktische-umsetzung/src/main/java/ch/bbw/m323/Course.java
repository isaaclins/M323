package ch.bbw.m323;

import java.util.Objects;

public class Course implements Comparable<Course> {
    private String courseId;
    private String courseName;
    private int credits;
    private Lecturer lecturer; // Association to Lecturer

    public Course(String courseId, String courseName, int credits, Lecturer lecturer) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        setLecturer(lecturer); // Use setter to maintain consistency
    }

    public Course(String courseId, String courseName, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        // Lecturer can be set later or be null initially
    }

    // Getters
    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    // Setters
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setLecturer(Lecturer lecturer) {
        if (this.lecturer != null && this.lecturer != lecturer) {
            this.lecturer.getCoursesTaught().remove(this);
        }
        this.lecturer = lecturer;
        if (lecturer != null && !lecturer.getCoursesTaught().contains(this)) {
            lecturer.getCoursesTaught().add(this); // Maintain bi-directional consistency
        }
    }

    @Override
    public String toString() {
        String lecturerName = (lecturer != null) ? lecturer.getName() : "N/A";
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", lecturer=" + lecturerName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }

    @Override
    public int compareTo(Course other) {
        return this.courseId.compareTo(other.courseId); // Natural order by course ID
    }
}
