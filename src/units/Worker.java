package units;

public class Worker extends Unit{
    private String unitName = "Munkas";
    private String unitShort = "M";

    public Worker(int unitAmount, Hos parentHos) {
        super(unitAmount, parentHos);
        this.setUnitName(unitName);
        this.setUnitShort(unitShort);
        this.setPrice(2);
        this.setMinDmg(1  * unitAmount);
        this.setMaxDmg(1  * unitAmount);
        this.setDefaultHp(3);
        this.setHp(3 * unitAmount);
        this.setSpeed(4);
        this.setPriority(8  + parentHos.getMoral() - 1);
    }
}
