package lostfound.model;

/**
 * A student files a lostReport when the **lose**
 * an item. The LostReport contains:
 *  - Who is filing the report (the Student)
 *  - The id of the found item that matches the lost item (if any)
 *  - 
 */

import lostfound.enums.ItemCategory;
import lostfound.enums.ItemStatus;
import java.time.LocalDateTime;

public class LostReport extends Item{
    private Student student;
    private int matchedFoundId; // Must be null (-1) in the beginning.

    public LostReport(int ITEM_ID, ItemCategory category, String color, String description, LocalDateTime occuredAt, ItemStatus status, Student student) {
        super(ITEM_ID, category, color, description, occuredAt, status);
        this.student = student;
        this.matchedFoundId = -1; // No match initially
    }

    public Student getStudent() {return student;}
    public int getMatchedFoundId() {return matchedFoundId;}

    // This is equivalent to setFoundItemId() but only for setting the new Id
    protected void linkTo(int foundItemId){
        if(this.getStatus() != ItemStatus.REPORTED) {
            throw new IllegalStateException("Cannot link a found item to a lost report that is not in REPORTED status.");
        }
        this.matchedFoundId = foundItemId;
    }

    // This is equivalent to setFoundItemId() but only for resetting the id to -1
    protected void unlink(){
        this.matchedFoundId = -1;
    }
}
