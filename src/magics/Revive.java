package magics;

import units.Unit;

public class Revive extends Magic {
    public Revive(int amount) {
        super(amount);
        this.setName("revive");
        this.setPrice(120);
        this.setMana(6);
    }

    public void revive(Unit target) {

    }
}
