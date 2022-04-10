package units;

public class Archer extends Unit{

    private static String pathToImg = "img/archer.png";

    private String unitName = "I";

    public Archer() {
        this.setUnitName(unitName);
        this.setPrice(6);
        this.setMinDmg(2);
        this.setMaxDmg(4);
        this.setHp(7);
        this.setSpeed(4);
        this.setPriority(9);
    }

    public String getPathToImg() {
        return pathToImg;
    }
}