package magics;

import units.Hos;
import units.Unit;

public class Magic {
    private String name;
    private Hos parentHos;
    private int price;
    private int mana;
    private Hos source;

    public Magic(Hos parentHos) {
        this.setParentHos(parentHos);
    }

    public void attack(Hos source, Unit target) {

    }

    //region Getters

    public Hos getParentHos() {
        return this.parentHos;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getMana() {
        return this.mana;
    }

    public Hos getSource() {
        return this.source;
    }

    //endregion

    //region Setters


    public void setParentHos(Hos parentHos) {
        this.parentHos = parentHos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSource(Hos source) {
        this.source = source;
    }

    //endregion
}
