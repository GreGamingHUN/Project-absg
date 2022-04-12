package units;

public class Worker extends Unit{
    private String unitName = "Munkas";
    private String unitShort = "M";

    public Worker(int unitAmount) {
        super(unitAmount);
        this.setUnitName(unitName);
        this.setUnitShort(unitShort);
        this.setPrice(2);
        this.setMinDmg(1);
        this.setMaxDmg(1);
        this.setHp(3);
        this.setSpeed(4);
        this.setPriority(8);
    }
}
