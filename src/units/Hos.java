package units;

public class Hos {
    private int balance;
    private int mana;

    private int dmgUp = 1;
    private int defUp = 1;
    private int magicUp = 1;
    private int knowledge = 1;
    private int moral = 1;
    private int luck = 1 ;

    public Hos() {
        this.balance = 1000;
    }

    public Hos(int balance, int diff) {
        switch (diff) {
            case 1:
                this.balance = 1300;
                break;
            case 2:
                this.balance = 1000;
            case 3:
                this.balance = 700;
        }
    }


    //region Getters
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
}
