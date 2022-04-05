package units;

public class Unit {

    private String pathToImg;
    private Hos parentHos;
    //Core stats
    private int price;
    private int dmg;
    private int hp;
    private int speed;
    private int priority;

    public Unit(int price, int dmg, int hp, int speed, int priority, Hos parent) {
        this.setPrice(price);
        this.setDmg(dmg);
        this.setHp(hp);
        this.setSpeed(speed);
        this.setPriority(priority);
    }

    public void normalTamadas(Unit enemy) {
        
    }

    //region Getters
    public int getPrice() {
        return this.price;
    }

    public int getDmg() {
        return this.dmg;
    }

    public int getHp() {
        return this.hp;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getPriority() {
        return this.priority;
    }

    public Hos getParentHos() {
        return this.parentHos;
    }

    //endregion

    //region Setters

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setParentHos(Hos parentHos) {
        this.parentHos = parentHos;
    }

    //endregion
}
