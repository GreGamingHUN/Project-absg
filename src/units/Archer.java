package units;

public class Archer extends Unit{

    private String unitName = "Ijasz";
    private String unitShort = "I";

    public Archer(int unitAmount, Hos parentHos) {
        super(unitAmount, parentHos);
        this.setUnitName(unitName);
        this.setUnitShort(unitShort);
        this.setPrice(6);
        this.setMinDmg(2  * unitAmount);
        this.setMaxDmg(4  * unitAmount);
        this.setDefaultHp(7);
        this.setHp(7  * unitAmount);
        this.setSpeed(4);
        this.setPriority(9 + parentHos.getMoral() - 1);
    }

    public void shoot(Unit target) {

    }
}