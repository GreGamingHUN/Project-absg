/**
 * A földműves objektum, ami tartalmaz mindent, ami csak a földműveshez tartozó adatokat tartalmazza
 */


package units;

public class Worker extends Unit{
    private String unitName = "Foldmuves";
    private String unitShort = "F";

    public Worker(int unitAmount, Hos parentHos) {
        super(unitAmount, parentHos);
        this.setUnitName(unitName);
        this.setUnitShort(unitShort);
        this.setPrice(2);
        this.setMinDmg(1  * unitAmount);
        this.setDefMinDmg(1);
        this.setMaxDmg(1 * unitAmount);
        this.setDefMaxDmg(1);
        this.setDefaultHp(3);
        this.setHp(3 * unitAmount);
        this.setMaxHp(this.getHp());
        this.setSpeed(4);
        this.setPriority(8  + parentHos.getMoral() - 1);
    }
}
