package locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Location {
    private final String NAME;
    private final String DESCRIPTION;

    private HashMap<String, Exit> exits = new HashMap<String, Exit>();
    private List<Character> characters = new ArrayList<Character>();


    /* --------------------*
     *    Constructors     *
     * --------------------*/

    /**
     * Creates a new location from its name and its description
     */
    public Location(String name, String description) {
        this.NAME = name;
        this.DESCRIPTION = description;
    }


    /* --------------------*
     *       Setters       *
     * --------------------*/

    /**
     * Adds an exit to the location
     */
    public void addExit(String name, Exit exit) {
        this.exits.put(name, exit);
    }

    /**
     * Removes the exit from the location if it exists. Else throws an error.
     */
    public void removeExit(String exitName) throws Exception {
        if (this.exits.containsKey(exitName)){
            this.exits.remove(exitName);
        } 
        else {
            throw new Exception("removeExit : The exit doesn't exist !");
        }
    }

    /**
     * Adds a given character to the location
     */
    public void addCharacter(Character c) {
        this.characters.add(c);
    }

    /**
     * Removes a given character from the location
     */
    public void removeCharacter(Character c) {
        this.characters.remove(c);
    }


    /* --------------------*
     *       Getters       *
     * --------------------*/

    /**
     * Returns the name of the location
     */
    public String getName() {
        return this.NAME;
    }

    public List<Character> getCharacters() {
        return this.characters;
    }

    /**
     * Returns the description of the location
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }


    /* --------------------*
     *    Other Methods    *
     * --------------------*/

    /**
     * Returns the new location associated to the string
     */
    public Location exitTo(String exit) throws Exception {
        return this.exits.get(exit).exit();
    }

    
}
