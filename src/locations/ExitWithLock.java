package locations;

/**
 * Class ExitWithLock : Extends Exit and adds a lock to the exits
 */
public abstract class ExitWithLock extends Exit {
    private boolean is_locked ;

    /**
     * Constructor for exitWithLock
     * @param newLoc
     * @param description
     */
    public ExitWithLock(Location newLoc, String description) {
        super(newLoc, description);
        this.is_locked = true;
    }

    /**
     * Unlocks the door
     */
    public void unlock() throws Exception {
        this.is_locked = false;
    }

    /**
     * Exits the location if possible
     */
    @Override
    public Location exit() throws Exception {
        if (!this.is_locked) {
            return super.exit();
        } else {
            throw new Exception("exit : The door is locked !");
        }
    }
}
