package units;

public class Griff extends Unit{
    private String unitName = "Griff";
    private String unitShort = "G";

    public Griff(int unitAmount, Hos parentHos) {
        super(unitAmount, parentHos);
        this.setUnitName(unitName);
        this.setUnitShort(unitShort);
        this.setPrice(15);
        this.setMinDmg(5  * unitAmount);
        this.setMaxDmg(10  * unitAmount);
        this.setDefaultHp(30);
        this.setHp(30  * unitAmount);
        this.setSpeed(7);
        this.setPriority(15  + parentHos.getMoral() - 1);
    }
}
