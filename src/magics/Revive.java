package magics;

import units.Hos;
import units.Unit;

public class Revive extends Magic {
    public Revive(Hos parentHos) {
        super(parentHos);
        this.setName("Feltamasztas");
        this.setPrice(120);
        this.setMana(6);
    }
    @Override
    public void attack(Hos source, Unit target) {
        if (target.getHp() + target.getParentHos().getMagicUp() * 50 >= target.getMaxHp()) {
            target.setHp(target.getMaxHp());
            double newHp= target.getHp()/target.getDefaultHp();
            target.setUnitAmount((int)Math.ceil(newHp));
            target.setMinDmg(target.getUnitAmount() * target.getDefMinDmg());
            target.setMaxDmg(target.getUnitAmount() * target.getDefMaxDmg());
        } else {
            target.setHp(target.getHp() + target.getParentHos().getMagicUp() * 50);
            double newHp= target.getHp()/target.getDefaultHp();
            target.setUnitAmount((int)Math.ceil(newHp));
            target.setMinDmg(target.getUnitAmount() * target.getDefMinDmg());
            target.setMaxDmg(target.getUnitAmount() * target.getDefMaxDmg());
        }
    }
}
