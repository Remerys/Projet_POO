package locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import characters.Character;
import characters.Talker;
import characters.Fighter;
import items.Item;

public class Location {
    // Constants declarations
    private final String NAME;
    private final String DESCRIPTION;

    private String 


    private HashMap<String, Exit> exits = new HashMap<String, Exit>();

    // The characters ares stocked in two lists, because they are usedd in two ways
    private List<Fighter> fighters = new ArrayList<Fighter>();
    private List<Talker> talkers = new ArrayList<Talker>();

    private List<Item> items = new ArrayList<Item>();

    /**
     * Returns a list of locations for the game
     */
    public static ArrayList<Location> createGameLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();

        // TODO: Add characters to the locations
        // Creation of the locations
        locs.add(new Location("Starting Island", "A peaceful island far from the rest of the world."));
        locs.add(new Location("Island #1", "An unknown island located at one end of the archipelago. You can observe another island in the distance."));
        locs.add(new Location("Island #2", "The main island of the archipelago."));
        locs.add(new Location("Quest Island", "A lost island almost outside the archipelago. The atmosphere there is hostile."));

        // Exits for the 1st island
        locs.get(1).addExit(locs.get(2), "The island in the distance appears reachable by swimming with the help of the current.");
        locs.get(1).addExitWithCode(locs.get(0), "coucou", "You observe a cave on the island, and symbols suggest that a code is needed to enter it.");

        // Exits for the 2nd island
        locs.get(2).addExit(locs.get(3), "As you gaze at the horizon, you easily make out a vast island. It appears inhabited. Swimming there seems possible.");

        // Exits for the 3rd island
        locs.get(3).addExit(locs.get(1), "It seems like you can swim to the first visited island.");
        locs.get(3).addExit(locs.get(2), "The main island also seems easily reachable.");

        return locs;
    }


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
    public void addExit(Location loc, String description) {
        Exit exit = new Exit(loc, description);
        this.exits.put(loc.getName(), exit);
    }

    // TODO: Regarder avec enum
    public void addExitWithCode(Location loc, String code, String description) {
        Exit exit = new ExitWithCode(loc, code, description);
        this.exits.put(loc.getName(), exit);
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
    public void addCharacter(Character c) throws Exception {
        boolean hasCharacterBeenAdded = false;

        if (c instanceof Talker) {
            this.talkers.add((Talker)c);
            hasCharacterBeenAdded = true;
        }

        if (c instanceof Fighter) {
            this.fighters.add((Fighter)c);
            hasCharacterBeenAdded = true;
        }

        if (!hasCharacterBeenAdded) {
            throw new Exception("The character can't be interacted with !");
        }
    }

    /**
     * Removes a given character from the location
     */
    public void removeCharacter(Character c) throws Exception {
        if (c instanceof Talker) {
            this.removeTalker((Talker)c);
        }

        if (c instanceof Fighter) {
            this.removeFighter((Fighter)c);
        }
    }

    private void removeTalker(Talker c) throws Exception {
        boolean res = this.talkers.remove(c);

        if (!res) {
            throw new Exception("removeTalker : The character couldn't be removed !");
        }
    }

    private void removeFighter(Fighter c) throws Exception {
        boolean res = this.fighters.remove(c);

        if (!res) {
            throw new Exception("removeFighters : The character couldn't be removed !");
        }
    }

     /**
     * Adds a given item to the location
     */
    public void addItem(Item i) {
        this.items.add(i);
    }

     /**
     * Adds a given item to the location
     */
    public void removeItem(Item i) {
        this.items.remove(i);
    }

    public String enterExitCode(String exitName, String code) {
        if (this.exits.containsKey(exitName)) {
            Exit exit = this.exits.get(exitName);
            if (exit instanceof ExitWithCode) {
                ((ExitWithCode)exit).enterCode(code);
            } else {
                return 
            }
        }
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

    /**
     * Returns the characters
     */
    public List<Talker> getTalkers() {
        return this.talkers;
    }

    public List<Fighter> getFighters() {
        return this.fighters;
    }

    /**
     * Returns the description of the location
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public Item getItem(String itemName) {
        for (Item i : this.items) {
            if (i.toString() == itemName) {
                return i;
            }
        }

        return null;
    }

    /**
     * Returns a list of couples of (descriptions)
     */
    public List<String> getExitDescriptions() {
        List<String> descriptions = new ArrayList<String>();

        this.exits.forEach((key, value) -> {
            descriptions.add(key + ": " + value.getDescription());
        });

        return descriptions;
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
