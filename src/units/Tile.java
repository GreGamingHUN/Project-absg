package units;

public class Tile {
    Unit unitOnTile = null;
    int posX, posY = -1;



    public Tile(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Unit getEmberOnTile() {
        return this.unitOnTile;
    }

    public void setEmberOnTile(Unit unitOnTile) {
        this.unitOnTile = unitOnTile;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
