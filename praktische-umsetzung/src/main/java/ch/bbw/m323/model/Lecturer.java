package ch.bbw.m323.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public record Lecturer(String lecturerId,String name,String department,List<Course>coursesTaught)implements Comparable<Lecturer>{

// Canonical constructor with defensive copy for coursesTaught
public Lecturer(String lecturerId,String name,String department,List<Course>coursesTaught){this.lecturerId=lecturerId;this.name=name;this.department=department;this.coursesTaught=coursesTaught==null?List.of():List.copyOf(coursesTaught);}

// Additional constructor for convenience (e.g., when lecturer is created before
// courses are assigned)
public Lecturer(String lecturerId,String name,String department){this(lecturerId,name,department,new ArrayList<>()); // Initially
                                                                                                                     // no
                                                                                                                     // courses
}

// addCourse logic is removed as records are immutable and don't have
// setters/mutators.
// Bi-directional consistency must be handled by the data generation/management
// logic.

@Override public String toString(){return"Lecturer{"+"lecturerId='"+lecturerId+'\''+", name='"+name+'\''+", department='"+department+'\''+", coursesTaughtCount="+(coursesTaught!=null?coursesTaught.size():0)+'}';}

@Override public int compareTo(Lecturer other){return this.lecturerId.compareTo(other.lecturerId());}
// equals() and hashCode() are auto-generated based on all fields.
}
