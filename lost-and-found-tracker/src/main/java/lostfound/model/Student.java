package lostfound.model;

/**
 * Per the revised UML, Student is a data holder,
 * No other attributes and methods are needed for now.
 * If more functionality is needed, we can add them later.
 */

public class Student extends User{
    public Student(String id, String name){
        super(id, name);
    }
}
