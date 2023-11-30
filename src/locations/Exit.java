package locations;

public class Exit {
    private final Location NEW_LOC;
    private final String DESCRIPTION;

    /**
     * Constructor for exits
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

    public Location exit() throws Exception {
        return this.NEW_LOC;
    }
}
