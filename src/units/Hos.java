package units;

import magics.Magic;

import java.util.Objects;
import java.util.Scanner;

public class Hos {
    private Magic[] boughtMagic = new Magic[3];
    private Unit[] boughtUnits = new Unit[20];
    private int balance;
    private int mana;

    private int abilityPrice = 5;
    private int tempAbilityPrice = 5;

    private int dmgUp = 1;
    private int defUp = 1;
    private int magicUp = 1;
    private int knowledge = 1;
    private int moral = 1;
    private int luck = 1 ;

    //region Constructors
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
    //endregion

    //region Getters


    public Unit[] getBoughtUnits() {
        return this.boughtUnits;
    }

    public int getTempAbilityPrice() {
        return this.tempAbilityPrice;
    }

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

    public Magic[] getBoughtMagic() {
        return this.boughtMagic;
    }

    //endregion

    //region Setters

    public void setBoughtUnits(Unit[] boughtUnits) {
        this.boughtUnits = boughtUnits;
    }

    public void setTempAbilityPrice(int tempAbilityPrice) {
        this.tempAbilityPrice = tempAbilityPrice;
    }

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
        int price = 0;
        for (int i = 0; i < dmgUp; i++) {
            price += this.getTempAbilityPrice();
            this.setTempAbilityPrice((int)Math.ceil(this.getTempAbilityPrice() * 1.1));
        }
        System.out.println("Ez " + price +" aranyba fog kerülni. Elfogadod? (y/n)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        if (Objects.equals(answer, "y") || Objects.equals(answer, "Y")) {
            if (this.getBalance() - price >= 0) {
                this.dmgUp += dmgUp;
                this.setBalance(this.getBalance() - price);
                this.setAbilityPrice(this.getTempAbilityPrice());
            } else {
                System.out.println("Nincs ehhez eleg aranyad!");
            }
        }
    }

    public void setDefUp(int defUp) {
        int price = 0;
        for (int i = 0; i < defUp; i++) {
            price += this.getAbilityPrice();
            this.setAbilityPrice((int)Math.ceil(this.getAbilityPrice() * 1.1));
        }
        System.out.println("Ez " + price +" aranyba fog kerülni. Elfogadod? (y/n)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        if (Objects.equals(answer, "y") || Objects.equals(answer, "Y")) {

            if (this.getBalance() - price >= 0) {
                this.defUp += defUp;
                this.setBalance(this.getBalance() - price);
            } else {
                System.out.println("Nincs ehhez eleg aranyad!");
            }
        }
    }

    public void setMagicUp(int magicUp) {
        int price = 0;
        for (int i = 0; i < magicUp; i++) {
            price += this.getAbilityPrice();
            this.setAbilityPrice((int)Math.ceil(this.getAbilityPrice() * 1.1));
        }
        System.out.println("Ez " + price +" aranyba fog kerülni. Elfogadod? (y/n)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        if (Objects.equals(answer, "y") || Objects.equals(answer, "Y")) {

            if (this.getBalance() - price >= 0) {
                this.magicUp += magicUp;
                this.setBalance(this.getBalance() - price);
            } else {
                System.out.println("Nincs ehhez eleg aranyad!");
            }
        }
    }

    public void setKnowledge(int knowledge) {
        int price = 0;
        for (int i = 0; i < knowledge; i++) {
            price += this.getAbilityPrice();
            this.setAbilityPrice((int)Math.ceil(this.getAbilityPrice() * 1.1));
        }
        System.out.println("Ez " + price +" aranyba fog kerülni. Elfogadod? (y/n)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        if (Objects.equals(answer, "y") || Objects.equals(answer, "Y")) {

            if (this.getBalance() - price >= 0) {
                this.knowledge += knowledge;
                this.setBalance(this.getBalance() - price);
            } else {
                System.out.println("Nincs ehhez eleg aranyad!");
            }
        }
    }

    public void setMoral(int moral) {
        int price = 0;
        for (int i = 0; i < moral; i++) {
            price += this.getAbilityPrice();
            this.setAbilityPrice((int)Math.ceil(this.getAbilityPrice() * 1.1));
        }
        System.out.println("Ez " + price +" aranyba fog kerülni. Elfogadod? (y/n)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        if (Objects.equals(answer, "y") || Objects.equals(answer, "Y")) {

            if (this.getBalance() - price >= 0) {
                this.moral += moral;
                this.setBalance(this.getBalance() - price);
            } else {
                System.out.println("Nincs ehhez eleg aranyad!");
            }
        }
    }

    public void setLuck(int luck) {
        int price = 0;
        for (int i = 0; i < luck; i++) {
            price += this.getAbilityPrice();
            this.setAbilityPrice((int)Math.ceil(this.getAbilityPrice() * 1.1));
        }
        System.out.println("Ez " + price +" aranyba fog kerülni. Elfogadod? (y/n)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        if (Objects.equals(answer, "y") || Objects.equals(answer, "Y")) {

            if (this.getBalance() - price >= 0) {
                this.luck += luck;
                this.setBalance(this.getBalance() - price);
            } else {
                System.out.println("Nincs ehhez eleg aranyad!");
            }
        }
    }

    public void setBoughtMagic(Magic[] boughtMagic) {
        this.boughtMagic = boughtMagic;
    }

    //endregion
}
