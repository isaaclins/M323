package ch.bbw.m323.model;

import java.util.Objects;

public record Course(String courseId,String courseName,int credits,Lecturer lecturer)implements Comparable<Course>{

public Course(String courseId,String courseName,int credits){this(courseId,courseName,credits,null);}

@Override public int compareTo(Course other){return this.courseId.compareTo(other.courseId);}

@Override public String toString(){String lecturerName=(lecturer!=null)?lecturer.name():"N/A";return"Course{"+"courseId='"+courseId+'\''+", courseName='"+courseName+'\''+", credits="+credits+", lecturer="+lecturerName+'}';}

@Override public boolean equals(Object o){if(this==o)return true;if(o==null||getClass()!=o.getClass())return false;Course course=(Course)o;return Objects.equals(courseId,course.courseId);}

@Override public int hashCode(){return Objects.hash(courseId);}}
