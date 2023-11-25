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
        locs.add(new Location("Le commencement", "Une île paisible loin de tout le reste du monde"));
        locs.add(new Location("Île n°1", "Une île inconnue située à une extrémité de l'archipel. Vous pouvez observer une autre île au loin."));
        locs.add(new Location("Île n°2", "L'île principale de l'archipel."));
        locs.add(new Location("Île de la quête", "Une île perdue presque hors de l'archipel. L'ambiance y est hostile."));

        // Exits for the 1st island
        locs.get(1).addExit(locs.get(2), "L'île au loin semble atteignable en nageant grâce au courant.");
        locs.get(1).addExit(locs.get(1), "Vous observez une grotte sur l'île, et des symboles semblent indiquer qu'un code est nécessaire afin d'y pénetrer");

        // Exits for the 2nd island
        locs.get(2).addExit(locs.get(3), "En observant l'horizon, vous distinguez aisément une immense île. Elle semble habitée. S'y rendre à la nage semble possible");
        
        // Exits for the 3rd island
        locs.get(3).addExit(locs.get(1), "Vous semblez pouvoir vous rendre à la première île visitée à la nage.");
        locs.get(3).addExit(locs.get(2), "L'île principale semble aussi atteignable aisément.");       

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


    /* --------------------*
     *       Getters       *
     * --------------------*/

    /**
     * Returns the name of the location
     */
    public String getName() {
        return this.NAME;
    }

    // TODO:
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
