package items;

public abstract class MusicalInstrument extends Item implements Usable {
    private final String DESCRIPTION;

    public MusicalInstrument(int weight, String description) {
        super(weight);
        this.DESCRIPTION = description;
    }

    public String getDescription() {
        String description = "NAME : " + this.getClass().getSimpleName() + "\nDESCRIPTION : " + this.DESCRIPTION;
        return description;
    }
}
