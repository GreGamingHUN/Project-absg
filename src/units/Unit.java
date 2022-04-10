package units;

import java.util.Random;

public class Unit {

    private int posX;
    private int posY;

    private Hos parentHos;
    //Core stats
    private int price;
    private int minDmg;
    private int maxDmg;
    private int hp;
    private int speed;
    private int priority;

    private int unitAmount = 1;

    private String unitName;

    public Unit() {

    }

    public Unit(int unitAmount) {
        this.setUnitAmount(unitAmount);
    }

    public void attack(Unit enemy) {
        Random r = new Random();
        int dmgToEnemy = (int) ((r.nextInt(this.getMaxDmg()-this.getMinDmg()) + this.getMinDmg()) * (1 + (0.1 * this.getParentHos().getDmgUp())) -
                (0.05 * enemy.getParentHos().getDefUp()));
        enemy.setHp(enemy.getHp() - dmgToEnemy);
    }

    //region Getters


    public int getUnitAmount() {
        return this.unitAmount;
    }

    public String getUnitName() {
        return this.unitName;
    }

    public int getPrice() {
        return this.price;
    }

    public int getMinDmg() {
        return this.minDmg;
    }

    public int getHp() {
        return this.hp * this.getUnitAmount();
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

    public int getMaxDmg() {
        return this.maxDmg;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    //endregion

    //region Setters


    public void setUnitAmount(int unitAmount) {
        this.unitAmount = unitAmount;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setMinDmg(int minDmg) {
        this.minDmg = minDmg;
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

    public void setMaxDmg(int maxDmg) {
        this.maxDmg = maxDmg;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    //endregion
}
