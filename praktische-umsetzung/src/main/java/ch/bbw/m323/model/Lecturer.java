package ch.bbw.m323.model;

import java.util.Objects;

public record Lecturer(String lecturerId,String name,String department)implements Comparable<Lecturer>{

@Override public int compareTo(Lecturer other){return this.lecturerId.compareTo(other.lecturerId());}

@Override public boolean equals(Object o){if(this==o)return true;if(o==null||getClass()!=o.getClass())return false;Lecturer lecturer=(Lecturer)o;return Objects.equals(lecturerId,lecturer.lecturerId);}

@Override public int hashCode(){return Objects.hash(lecturerId);}}
