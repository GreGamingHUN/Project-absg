package units;
public class Worker extends Unit{
    private String pathToImg;

    public Worker(int price, int dmg, int hp, int speed, int priority, Hos parent) {
        super(price, dmg, hp, speed, priority, parent);
        this.setPrice(price);
        this.setDmg(dmg);
        this.setHp(hp);
        this.setSpeed(speed);
        this.setPriority(priority);
    }
}
