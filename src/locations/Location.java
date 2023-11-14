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

}
