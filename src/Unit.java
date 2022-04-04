public class Unit {
    private int attack;
    private int defense;
    private int magic;
    private int knowledge;
    private int moral;
    private int luck;

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        if (attack < 1) {
            this.attack = 1;
        } else if (attack > 10) {
            this.attack = 10;
        } else {
            this.attack = attack;
        }
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int defense) {
        if (defense < 1) {
            this.defense = 1;
        } else if (defense > 10) {
            this.defense = 10;
        } else {
            this.defense = defense;
        }
    }

    public int getMagic() {
        return this.magic;
    }

    public void setMagic(int magic) {
        if (magic < 1) {
            this.magic = 1;
        } else if (magic > 10) {
            this.magic = 10;
        } else {
            this.magic = magic;
        }
    }

    public int getKnowledge() {
        return this.knowledge;
    }

    public void setKnowledge(int knowledge) {
        if (knowledge < 1) {
            this.knowledge = 1;
        } else if (knowledge > 10) {
            this.knowledge = 10;
        } else {
            this.knowledge = knowledge;
        }
    }

    public int getMoral() {
        return this.moral;
    }

    public void setMoral(int moral) {
        if (moral < 1) {
            this.moral = 1;
        } else if (moral > 10) {
            this.moral = 10;
        } else {
            this.moral = moral;
        }
    }

    public int getLuck() {
        return this.luck;
    }

    public void setLuck(int luck) {
        if (luck < 1) {
            this.luck = 1;
        } else if (luck > 10) {
            this.luck = 10;
        } else {
            this.luck = luck;
        }
    }
}
