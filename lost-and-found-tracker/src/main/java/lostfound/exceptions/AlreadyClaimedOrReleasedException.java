package lostfound.exceptions;

public class AlreadyClaimedOrReleasedException extends TrackerException {
    public AlreadyClaimedOrReleasedException(String message) {
        super(message);
    }
    
}
