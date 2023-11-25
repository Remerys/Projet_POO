package items;

public class Flute extends MusicalInstrument {
    private static final int WEIGHT = 1;

    public Flute() {
        super(Flute.WEIGHT);
    }

    public void use() {
        System.out.println("tututu.. tutu.. tu.. tu.. tu.. tutututututututututututututu..");
    }
}
