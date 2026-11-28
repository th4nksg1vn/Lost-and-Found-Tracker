package lostfound.model;

import lostfound.enums.ItemCategory;
import lostfound.enums.ItemStatus;
import java.time.LocalDateTime;

public abstract class Item {
    private final int ITEM_ID; // For simplicity, we will use an integer as the item ID. In a real-world application, we'd want to use a more complex identifier.
    private ItemCategory category;
    private String color;
    private String description;
    private LocalDateTime occuredAt;
    private ItemStatus status;
    private String holderName; // Can be null. Only for ID_CARD
    private String idNumber; // Can be null. Only for ID_CARD
    private String model; // Can be null. Only for ID_CARD

    protected Item(int ITEM_ID, ItemCategory category, String color, String description, LocalDateTime occuredAt, ItemStatus status) {
        this.ITEM_ID = ITEM_ID;
        this.category = category;
        this.color = color;
        this.description = description;
        this.occuredAt = occuredAt;
        this.status = status;
    }

    public int getITEM_ID() {return ITEM_ID;}
    public ItemCategory getCategory() {return category;}
    public String getColor() {return color;}
    public String getDescription() {return description;}
    public LocalDateTime getOccuredAt() {return occuredAt;}
    public ItemStatus getStatus() {return status;}
    
    public String getHolderName() {
        if(category != ItemCategory.ID_CARD) {
            throw new UnsupportedOperationException("Holder name is only applicable for ID cards.");
        }
        return holderName;
    }
    public String getIdNumber() {
        if(category != ItemCategory.ID_CARD) {
            throw new UnsupportedOperationException("ID number is only applicable for ID cards.");
        }
        return idNumber;
    }
    public String getModel() {
        if(category != ItemCategory.MOBILE_DEVICE) {
            throw new UnsupportedOperationException("Model is only applicable for Mobile devices.");
        }
        return model;
    }

    public void setCategory(ItemCategory category) {
        if(category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        this.category = category;
    }

    public void setColor(String color) {
        if(color == null || color.isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        this.color = color;
    }

    public void setDescription(String description) {
        if(description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description;
    }

    public void setStatus(ItemStatus status) {
        if(status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
    }

    public void setOccuredAt(LocalDateTime occuredAt) {
        if(occuredAt == null) {
            throw new IllegalArgumentException("Occurrence time cannot be null");
        }
        this.occuredAt = occuredAt;
    }

    public void setHolderName(String holderName) {
        if(holderName == null || holderName.isEmpty()) {
            throw new IllegalArgumentException("Holder name cannot be null or empty");
        }
        if(category != ItemCategory.ID_CARD) {
            throw new UnsupportedOperationException("Holder name is only applicable for ID cards.");
        }
        this.holderName = holderName;
    }

    public void setIdNumber(String idNumber) {
        if(idNumber == null || idNumber.isEmpty()) {
            throw new IllegalArgumentException("ID number cannot be null or empty");
        }
        if(category != ItemCategory.ID_CARD) {
            throw new UnsupportedOperationException("ID number is only applicable for ID cards.");
        }
        this.idNumber = idNumber;
    }

    public void setModel(String model) {
        if(model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }
        if(category != ItemCategory.MOBILE_DEVICE) {
            throw new UnsupportedOperationException("Model is only applicable for Mobile devices.");
        }
        this.model = model;
    }

    public boolean matchesKeyWord(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return false;
        }
        String lowerKeyword = keyword.toLowerCase();
        return (category != null && category.toString().toLowerCase().contains(lowerKeyword)) ||
               (color != null && color.toLowerCase().contains(lowerKeyword)) ||
               (description != null && description.toLowerCase().contains(lowerKeyword)) ||
               (holderName != null && holderName.toLowerCase().contains(lowerKeyword)) ||
               (idNumber != null && idNumber.toLowerCase().contains(lowerKeyword)) ||
               (model != null && model.toLowerCase().contains(lowerKeyword));
    }

    public void updateStatus(ItemStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("New status cannot be null");
        }

        /**  You can only go in the order:
        * REPORTED/FOUND → MATCHED → CLAIMED → RELEASED
        * Any attempt to set the status to a previous state will throw an exception.
        */
        if (this.status == ItemStatus.REPORTED || this.status == ItemStatus.FOUND) {
            if (newStatus == ItemStatus.MATCHED || newStatus == ItemStatus.CLAIMED || newStatus == ItemStatus.RELEASED) {
                this.status = newStatus;
            } else {
                throw new IllegalArgumentException("Invalid status transition from " + this.status + " to " + newStatus);
            }
        } else if (this.status == ItemStatus.MATCHED) {
            if (newStatus == ItemStatus.CLAIMED || newStatus == ItemStatus.RELEASED) {
                this.status = newStatus;
            } else {
                throw new IllegalArgumentException("Invalid status transition from " + this.status + " to " + newStatus);
            }
        } else if (this.status == ItemStatus.CLAIMED) {
            if (newStatus == ItemStatus.RELEASED) {
                this.status = newStatus;
            } else {
                throw new IllegalArgumentException("Invalid status transition from " + this.status + " to " + newStatus);
            }
        } else if (this.status == ItemStatus.RELEASED) {
            throw new IllegalArgumentException("Cannot change status from RELEASED");
        }
    }
}
