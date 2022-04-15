package magics;

import scripts.GameLogic;
import units.Unit;

public class Fireball extends Magic {
    public Fireball() {
        this.setName("fireball");
        this.setPrice(120);
        this.setMana(9);
    }

    public void attack(Unit target) {
        for (int i = target.getPosX() - 1; i < target.getPosX()+2; i++) {
            for (int j = target.getPosY() - 1; j < target.getPosY()+2; j++) {
                GameLogic.tileArray[i][j].getEmberOnTile().setHp(target.getHp() - this.getSource().getMagicUp() * 20);
            }
        }
    }
}
