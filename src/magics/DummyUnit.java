/**
 * Egy varázslat, ami az ellenfél egyik egységét egy 1 életerős senkivé teszi
 */

package magics;

import units.Hos;
import units.Unit;

public class DummyUnit extends Magic{
    public DummyUnit(Hos parentHos) {
        super(parentHos);
        this.setName("DummyUnit");
        this.setPrice(150);
        this.setMana(40);
    }

    @Override
    public void attack(Hos source, Unit target) {

    }
}
