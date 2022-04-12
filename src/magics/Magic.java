package magics;

import units.Hos;

public class Magic {
    private String name;
    private int price;
    private int mana;
    private Hos source;
    private int amount;

    public Magic(int amount) {
        this.amount = amount;
    }

    //region Getters


    public int getAmount() {
        return this.amount;
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


    public void setAmount(int amount) {
        this.amount = amount;
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
