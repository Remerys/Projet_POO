package locations;

import java.util.HashMap;
import java.util.List;

public abstract class Location {
    private final String NAME;
    private final String DESCRIPTION;

    private final HashMap<String, Exit> EXITS = new HashMap();

    /**
     * Creates a new location from its name and its description
     */
    public Location(String name, String description) {
        this.NAME = name;
        this.DESCRIPTION = description;
    }

    /**
     * Adds an exit to the location
     */
    public void addExit(String name, Exit exit) {
        this.EXITS.put(name, exit);
    }

    /**
     * Removes the exit from the location if it exists. Else throws an error.
     */
    public void removeExit(Exit exit) throws Exception {
        if (this.EXITS.containsValue(exit)){
            this.EXITS.remove(exit);
        } 
        else {
            throw new Exception("removeExit : The exit doesn't exist !");
        }
    }
}
