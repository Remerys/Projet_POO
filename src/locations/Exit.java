package locations;

/**
 * Class exit : allows the Locations to switch from one to another.
 */
public class Exit {
    private final Location NEW_LOC;
    private final String DESCRIPTION;

    /**
     * Constructor for exits
     * @param newLoc
     * @param description
     */
    public Exit(Location newLoc, String description) {
        this.NEW_LOC = newLoc;
        this.DESCRIPTION = description;
    }

    /**
     * Returns the description of an exit
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Returns the location
     */
    public Location exit() throws Exception {
        return this.NEW_LOC;
    }
}
