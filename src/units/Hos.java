package units;

public class Hos {
    private int balance;
    private int mana;

    private int abilityPrice = 5;

    private int dmgUp = 1;
    private int defUp = 1;
    private int magicUp = 1;
    private int knowledge = 1;
    private int moral = 1;
    private int luck = 1 ;

    public Hos() {
        this.balance = 1000;
    }

    public Hos(int diff) {
        switch (diff) {
            case 1:
                this.balance = 1300;
                break;
            case 2:
                this.balance = 1000;
                break;
            case 3:
                this.balance = 700;
                break;
        }
    }


    //region Getters


    public int getAbilityPrice() {
        return this.abilityPrice;
    }

    public int getBalance() {
        return this.balance;
    }

    public int getMana() {
        return this.mana;
    }

    public int getDmgUp() {
        return this.dmgUp;
    }

    public int getDefUp() {
        return this.defUp;
    }

    public int getMagicUp() {
        return this.magicUp;
    }

    public int getKnowledge() {
        return this.knowledge;
    }

    public int getMoral() {
        return this.moral;
    }

    public int getLuck() {
        return this.luck;
    }
    //endregion

    //region Setters


    public void setAbilityPrice(int abilityPrice) {
        this.abilityPrice = abilityPrice;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setDmgUp(int dmgUp) {
        this.dmgUp = dmgUp;
    }

    public void setDefUp(int defUp) {
        this.defUp = defUp;
    }

    public void setMagicUp(int magicUp) {
        this.magicUp = magicUp;
    }

    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
    }

    public void setMoral(int moral) {
        this.moral = moral;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    //endregion
}
