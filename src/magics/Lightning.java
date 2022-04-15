package magics;

import units.Hos;
import units.Tile;
import units.Unit;

public class Lightning extends Magic {

    public Lightning() {
        this.setName("Villam");
        this.setPrice(60);
        this.setMana(5);
    }

    public void attack(Unit target) {
        target.setHp(target.getHp() - this.getSource().getMagicUp() * 30);
    }
}
