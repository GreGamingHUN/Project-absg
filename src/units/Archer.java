package units;

public class Archer extends Unit{

    private String unitName = "Ijasz";
    private String unitShort = "I";

    public Archer(int unitAmount) {
        super(unitAmount);
        this.setUnitName(unitName);
        this.setUnitShort(unitShort);
        this.setPrice(6);
        this.setMinDmg(2);
        this.setMaxDmg(4);
        this.setHp(7);
        this.setSpeed(4);
        this.setPriority(9);
    }

    public void shoot(Unit target) {

    }
}