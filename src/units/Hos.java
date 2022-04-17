/**
 * A Hős objektum, ami tartalmazza a játékos és az ellenfél specifikus adatait
 */

package units;

import magics.Magic;

import java.util.Objects;
import java.util.Scanner;

public class Hos {
    private String name;
    private boolean boughtLightning = false;
    private boolean boughtFireball = false;
    private boolean boughtRevive = false;
    private Magic[] boughtMagic = new Magic[5];
    private Unit[] boughtUnits = new Unit[20];
    private int balance;
    private int mana = 30;
    private int maxMana;

    private int abilityPrice = 5;
    private int tempAbilityPrice = 5;

    private int dmgUp = 1;
    private int defUp = 1;
    private int magicUp = 1;
    private int knowledge = 1;
    private int moral = 1;
    private int luck = 1 ;

    private boolean usedAttackMagic = false;

    /**
     * A hős alap támadása
     * @param target a célpont, akit meg akar támadni
     */
    public void attack(Unit target) {
        target.setHp(target.getHp() - (this.getDmgUp() * 10));
        target.getDamaged();
    }

    //region Constructors

    /**
     * Az ellenfél konstruktorja
     */
    public Hos() {
        name = "enemy";
        this.balance = 1000;
    }

    /**
     * A játékos konstruktorja
     * @param diff A nehézség, mely alapján a játékos megkapja az aranyat
     */
    public Hos(int diff) {
        name = "player";
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


    public int getMaxMana() {
        return this.maxMana;
    }

    public boolean isUsedAttackMagic() {
        return this.usedAttackMagic;
    }

    public String getName() {
        return this.name;
    }

    public boolean isBoughtLightning() {
        return this.boughtLightning;
    }

    public boolean isBoughtFireball() {
        return this.boughtFireball;
    }

    public boolean isBoughtRevive() {
        return this.boughtRevive;
    }

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

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setUsedAttackMagic(boolean usedAttackMagic) {
        this.usedAttackMagic = usedAttackMagic;
    }

    public void setBoughtLightning(boolean boughtLightning) {
        this.boughtLightning = boughtLightning;
    }

    public void setBoughtFireball(boolean boughtFireball) {
        this.boughtFireball = boughtFireball;
    }

    public void setBoughtRevive(boolean boughtRevive) {
        this.boughtRevive = boughtRevive;
    }

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

    /**
     * A +támadás fejlesztés beállítása
     * @param dmgUp ahány szintet akar fejleszteni a játékos
     */
    public void setDmgUp(int dmgUp) {
        if (Objects.equals(this.name, "player")) {
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
        } else {
            int price = 0;
            for (int i = 0; i < dmgUp; i++) {
                price += this.getTempAbilityPrice();
                this.setTempAbilityPrice((int)Math.ceil(this.getTempAbilityPrice() * 1.1));
            }
            if (this.getBalance() - price >= 0) {
                this.dmgUp += dmgUp;
                this.setBalance(this.getBalance() - price);
                this.setAbilityPrice(this.getTempAbilityPrice());
            }
        }
    }

    /**
     * A +védekezés fejlesztés beállítása
     * @param defUp ahány szintet akar fejleszteni a játékos
     */
    public void setDefUp(int defUp) {
        if (Objects.equals(this.name, "player")) {
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
        } else {
            int price = 0;
            for (int i = 0; i < defUp; i++) {
                price += this.getAbilityPrice();
                this.setAbilityPrice((int)Math.ceil(this.getAbilityPrice() * 1.1));
            }
            if (this.getBalance() - price >= 0) {
                this.defUp += defUp;
                this.setBalance(this.getBalance() - price);
            }
        }
    }

    /**
     * a +varázslat (mana mennyiség) beállítása
     * @param magicUp ahány szintet akar fejleszteni a játékos
     */
    public void setMagicUp(int magicUp) {

        if (Objects.equals(this.name, "player")) {
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
        } else {
            int price = 0;
            for (int i = 0; i < magicUp; i++) {
                price += this.getAbilityPrice();
                this.setAbilityPrice((int)Math.ceil(this.getAbilityPrice() * 1.1));
            }
            if (this.getBalance() - price >= 0) {
                this.magicUp += magicUp;
                this.setBalance(this.getBalance() - price);
            }
        }

    }

    public void setKnowledge(int knowledge) {

        if(Objects.equals(this.name, "player")) {
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
        } else {
            int price = 0;
            for (int i = 0; i < knowledge; i++) {
                price += this.getAbilityPrice();
                this.setAbilityPrice((int)Math.ceil(this.getAbilityPrice() * 1.1));
            }
                if (this.getBalance() - price >= 0) {
                    this.knowledge += knowledge;
                    this.setBalance(this.getBalance() - price);
                }
        }

    }

    public void setMoral(int moral) {

        if(Objects.equals(this.name, "player")) {
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
        } else {
            int price = 0;
            for (int i = 0; i < moral; i++) {
                price += this.getAbilityPrice();
                this.setAbilityPrice((int)Math.ceil(this.getAbilityPrice() * 1.1));
            }
            if (this.getBalance() - price >= 0) {
                this.moral += moral;
                this.setBalance(this.getBalance() - price);
            } else {
                System.out.println("Nincs ehhez eleg aranyad!");
            }
        }
    }

    public void setLuck(int luck) {

        if(Objects.equals(this.name, "player")) {
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
        } else {
            int price = 0;
            for (int i = 0; i < luck; i++) {
                price += this.getAbilityPrice();
                this.setAbilityPrice((int)Math.ceil(this.getAbilityPrice() * 1.1));
            }
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
