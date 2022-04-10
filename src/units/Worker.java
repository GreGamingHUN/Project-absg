package units;

public class Worker extends Unit{
    static String pathToImg = "img/villager.jpg";

    private String unitName = "M";

    public Worker() {
        this.setUnitName(unitName);
        this.setPrice(2);
        this.setMinDmg(1);
        this.setMaxDmg(1);
        this.setHp(3);
        this.setSpeed(4);
        this.setPriority(8);
    }

    public String getPathToImg() {
        return pathToImg;
    }
}
