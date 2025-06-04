package ch.bbw.m323.model;

import java.time.LocalDate;
// Major is now in ch.bbw.m323.model, so no separate import needed if Student is
// in the same package.
// If Major were elsewhere, it would be: import ch.bbw.m323.model.Major;

public record Student(String studentId,String firstName,String lastName,int age,LocalDate enrollmentDate,Major major,double gpa)implements Comparable<Student>{

// Implicit canonical constructor.
// All fields are implicitly final.
// Accessors like studentId(), firstName() are auto-generated.
// equals(), hashCode(), and toString() are auto-generated.

// Natural order: by studentId
@Override public int compareTo(Student other){
// Use accessor methods if 'this' is ambiguous or for consistency, though direct
// field access works in records.
return this.studentId.compareTo(other.studentId());}}
