package units;



public class Griff extends Unit{
    private String unitName = "G";

    public Griff() {
        this.setUnitName(unitName);
        this.setPrice(15);
        this.setMinDmg(5);
        this.setMaxDmg(10);
        this.setHp(30);
        this.setSpeed(7);
        this.setPriority(15);
    }
}
