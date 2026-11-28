package lostfound.model;

import java.time.LocalDateTime;

import lostfound.enums.ItemCategory;
import lostfound.enums.ItemStatus;

/**
 * A student finds an item, BUT a worker puts it in the lost and found.
 * A FoundItem contains:
 * The worker who found the item (the worker)
 * The id of the lost item that matches the found item (if any)
 */

public class FoundItem extends Item{
    private Worker Worker;
    private int matchedLostId; // Must be null (-1) in the beginning.

    public FoundItem(int ITEM_ID, ItemCategory category, String color, String description, LocalDateTime occuredAt, ItemStatus status, Worker worker) {
        super(ITEM_ID, category, color, description, occuredAt, status);
        this.Worker = worker;
        this.matchedLostId = -1; // No match initially
    }

    protected void linkTo(int foundItemId){
        if(this.getStatus() != ItemStatus.REPORTED) {
            throw new IllegalStateException("Cannot link a found item to a lost report that is not in REPORTED status.");
        }
        this.matchedLostId = foundItemId;
    }

    protected void unlink(){
        this.matchedLostId = -1;
    }
}
