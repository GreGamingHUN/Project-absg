import javafx.scene.shape.Rectangle;

public class Tile {
    Unit unitOnTile;
    Rectangle tileRect;
    int posX, posY;

    public Tile(Rectangle tileRect, int posX, int posY) {
        this.tileRect = tileRect;
        this.posX = posX;
        this.posY = posY;
    }

    public Unit getEmberOnTile() {
        return this.unitOnTile;
    }

    public void setEmberOnTile(Unit unitOnTile) {
        this.unitOnTile = unitOnTile;
    }

    public Rectangle getTileRect() {
        return this.tileRect;
    }

    public void setTileRect(Rectangle tileRect) {
        this.tileRect = tileRect;
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
