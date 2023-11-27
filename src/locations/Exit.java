package locations;

public class Exit {
    private final Location NEW_LOC;
    private final String DESCRIPTION;

    public Exit(Location newLoc, String description) {
        this.NEW_LOC = newLoc;
        this.DESCRIPTION = description;
    }

    public String getDescription() {
        return this.DESCRIPTION;
    }

    public Location exit() throws Exception {
        return this.NEW_LOC;
    }
}
