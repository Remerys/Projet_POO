package items;

public class Bow extends Weapon {
    private static final String DESCRIPTION = "Magical bow crafted from stellar materials, enabling its user to shoot ethereal arrows. Its elegant design reflects the connection between nature and cosmic power.";
    private static final int WEIGHT = 5;
    private static final int DAMAGE = 5;
    private static final int RANGE = 10;

    public Bow() {
        super(Bow.WEIGHT, Bow.DAMAGE, Bow.RANGE, Bow.DESCRIPTION);
    }
}
