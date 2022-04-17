/**
 * Az atombomba objektum. Tartalmazza a hozzá tartozó adatokat, valamint a "támadását", ami a sebzésért felelős
 */

package magics;

import scripts.GameLogic;
import units.Hos;
import units.Unit;

public class Nuclearbomb extends Magic{
    public Nuclearbomb(Hos parentHos) {
        super(parentHos);
        this.setName("Atombomba");
        this.setPrice(200);
        this.setMana(30);
    }

    @Override
    public void attack(Hos source, Unit target) {
        if (!source.isUsedAttackMagic()) {
            try {
                for (int i = target.getPosX() - 2; i < target.getPosX() + 3; i++) {
                    if (i >= GameLogic.gridSizeX || i < 0) {
                        continue;
                    }
                    for (int j = target.getPosY() - 2; j < target.getPosY() + 3; j++) {
                        if (j >=GameLogic.gridSizeY || j < 0) {
                            continue;
                        }
                        if (GameLogic.tileArray[i][j].getEmberOnTile() != null) {
                            GameLogic.tileArray[i][j].getEmberOnTile().setHp(GameLogic.tileArray[i][j].getEmberOnTile().getHp()
                                    - source.getMagicUp() * 20);
                            GameLogic.tileArray[i][j].getEmberOnTile().getDamaged();
                            GameLogic.tileArray[i][j].getEmberOnTile().setPoisoned(true);
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
}
