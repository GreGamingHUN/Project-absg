package units;

import java.util.Random;

public class Unit {

    private int posX;
    private int posY;
    private boolean selected = false;

    private Hos parentHos;
    //Core stats
    private int price;
    private int defMinDmg;
    private int minDmg;
    private int defMaxDmg;
    private int maxDmg;
    private int defaultHp;
    private int hp;
    private int maxHp;
    private int speed;
    private int priority;

    private int unitAmount = 1;

    private boolean visszatamadott = false;

    private String unitName;
    private String unitShort;

    public Unit(int unitAmount, Hos parentHos) {
        this.setParentHos(parentHos);
        this.setUnitAmount(unitAmount);
    }

    public void attack(Unit enemy) {
        Random r = new Random();
        int dmgToEnemy;
        if (r.nextInt(1, 101) <= this.getParentHos().getLuck() * 5) {
            dmgToEnemy = 2 * ((int)((1 - enemy.getParentHos().getDefUp() * 0.05) * ((this.getParentHos().getDmgUp() + 1) * r.nextInt(this.getMinDmg(), this.getMaxDmg() + 1))));
        } else {
            dmgToEnemy = (int)((1 - enemy.getParentHos().getDefUp() * 0.05) * ((this.getParentHos().getDmgUp() + 1) * r.nextInt(this.getMinDmg(), this.getMaxDmg() + 1)));
        }
        System.out.println(dmgToEnemy);
        enemy.setHp(enemy.getHp() - dmgToEnemy);
        System.out.println(enemy.getHp());
    }

    public void getDamaged (Unit target) {
        Random r = new Random();
        if (this.getHp() <= 0) {
            this.setUnitAmount(0);
            return;
        }
        double newHp= hp/defaultHp;
        this.setUnitAmount((int)Math.ceil(newHp) + 1);
        this.setMinDmg(this.getDefMinDmg() * this.getUnitAmount());
        this.setMaxDmg(this.getDefMaxDmg() * this.getUnitAmount());
        if (this.getHp() > 0 && !this.isVisszatamadott()) {
            target.setHp((int)(target.getHp() - (r.nextInt(this.getMinDmg(), this.getMaxDmg() + 1)) * 0.5));
        }
    }

    public void getDamaged() {
        if (this.getHp() <= 0) {
           this.setUnitAmount(0);
           return;
        }
        double newHp= hp/defaultHp;
        if (newHp < 1) {
            newHp = 1;
        }
        this.setUnitAmount((int)Math.ceil(newHp) + 1);
        this.setMinDmg(this.getDefMinDmg() * this.getUnitAmount());
        this.setMaxDmg(this.getDefMaxDmg() * this.getUnitAmount());
    }

    //region Getters


    public int getDefMinDmg() {
        return this.defMinDmg;
    }

    public int getDefMaxDmg() {
        return this.defMaxDmg;
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public boolean isVisszatamadott() {
        return this.visszatamadott;
    }

    public int getDefaultHp() {
        return this.defaultHp;
    }
    public boolean isSelected() {
        return this.selected;
    }
    public String getUnitShort() {
        return this.unitShort;
    }
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


    public void setDefMinDmg(int defMinDmg) {
        this.defMinDmg = defMinDmg;
    }

    public void setDefMaxDmg(int defMaxDmg) {
        this.defMaxDmg = defMaxDmg;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setVisszatamadott(boolean visszatamadott) {
        this.visszatamadott = visszatamadott;
    }

    public void setDefaultHp(int defaultHp) {
        this.defaultHp = defaultHp;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public void setUnitShort(String unitShort) {
        this.unitShort = unitShort;
    }
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
