package units;

public class Creeper extends Unit{
    private String unitName = "Creeper";
    private String unitShort = "C";
    public Creeper(int unitAmount, Hos parentHos) {
        super(unitAmount, parentHos);
        this.setUnitName(unitName);
        this.setUnitShort(unitShort);
        this.setPrice(30);
        this.setMinDmg(10  * unitAmount);
        this.setDefMinDmg(1);
        this.setMaxDmg(20 * unitAmount);
        this.setDefMaxDmg(1);
        this.setDefaultHp(3);
        this.setHp(3 * unitAmount);
        this.setMaxHp(this.getHp());
        this.setSpeed(4);
        this.setPriority(13  + parentHos.getMoral() - 1);
    }
}
