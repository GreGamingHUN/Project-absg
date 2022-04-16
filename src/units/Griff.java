package units;

import java.util.Random;

public class Griff extends Unit{
    private String unitName = "Griff";
    private String unitShort = "G";

    public Griff(int unitAmount, Hos parentHos) {
        super(unitAmount, parentHos);
        this.setUnitName(unitName);
        this.setUnitShort(unitShort);
        this.setPrice(15);
        this.setMinDmg(5  * unitAmount);
        this.setDefMinDmg(5);
        this.setMaxDmg(10  * unitAmount);
        this.setDefMaxDmg(10);
        this.setDefaultHp(30);
        this.setHp(30  * unitAmount);
        this.setMaxHp(this.getHp());
        this.setSpeed(7);
        this.setPriority(15  + parentHos.getMoral() - 1);
    }

    @Override
    public void getDamaged (Unit target) {
        Random r = new Random();
        if (this.getHp() <= 0) {
            this.setUnitAmount(0);
            return;
        }
        double newHp= this.getHp()/this.getDefaultHp();
        this.setUnitAmount((int)Math.floor(newHp));
        this.setMinDmg(this.getDefMinDmg() * this.getUnitAmount());
        this.setMaxDmg(this.getDefMaxDmg() * this.getUnitAmount());
        if (this.getHp() > 0) {
            target.setHp((int)(target.getHp() - (r.nextInt(this.getMinDmg(), this.getMaxDmg() + 1)) * 0.05));
        }
    }
}
