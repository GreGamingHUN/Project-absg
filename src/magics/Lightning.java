package magics;

import units.Hos;
import units.Tile;
import units.Unit;

public class Lightning extends Magic {

    public Lightning(Hos parentHos) {
        super(parentHos);
        this.setName("Villam");
        this.setPrice(60);
        this.setMana(5);
    }

    @Override
    public void attack(Hos source, Unit target) {
        target.setHp(target.getHp() - this.getParentHos().getMagicUp() * 30);
    }
}
