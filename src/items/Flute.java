package items;

public class Flute extends MusicalInstrument {
    private static final String DESCRIPTION = "Enchanted musical instrument that produces ethereal tunes with a soothing aura. Its melodies have the power to calm emotions and enchant those who listen, making it a versatile tool for bards and adventurers alike.";
    private static final int WEIGHT = 1;

    public Flute() {
        super(Flute.WEIGHT, Flute.DESCRIPTION);
    }

    public void use() {
        System.out.println("tututu.. tutu.. tu.. tu.. tu.. tutututututututututututututu..");
    }
}
