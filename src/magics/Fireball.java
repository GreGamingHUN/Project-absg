package magics;

import scripts.GameLogic;
import units.Hos;
import units.Unit;

public class Fireball extends Magic {
    public Fireball(Hos parentHos) {
        super(parentHos);
        this.setName("Tuzlabda");
        this.setPrice(120);
        this.setMana(9);
    }
    @Override
    public void attack(Hos source, Unit target) {
        try {
            for (int i = target.getPosX() - 1; i < target.getPosX() + 2; i++) {
                if (i >=GameLogic.gridSizeX || i < 0) {
                    continue;
                }
                for (int j = target.getPosY() - 1; j < target.getPosY() + 2; j++) {
                    if (j >=GameLogic.gridSizeY || j < 0) {
                        continue;
                    }
                    if (GameLogic.tileArray[i][j].getEmberOnTile() != null) {
                        GameLogic.tileArray[i][j].getEmberOnTile().setHp(GameLogic.tileArray[i][j].getEmberOnTile().getHp()
                                - source.getMagicUp() * 20);
                        GameLogic.tileArray[i][j].getEmberOnTile().getDamaged();
                        if (GameLogic.tileArray[i][j].getEmberOnTile().getHp() <= 0) {
                            GameLogic.tileArray[i][j].setEmberOnTile(null);
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }
}
