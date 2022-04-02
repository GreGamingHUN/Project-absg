import javafx.scene.shape.Rectangle;

public class Tile {
    Ember emberOnTile;
    Rectangle tileRect;
    int posX, posY;

    public Tile(Rectangle tileRect, int posX, int posY) {
        this.tileRect = tileRect;
        this.posX = posX;
        this.posY = posY;
    }

    public Ember getEmberOnTile() {
        return this.emberOnTile;
    }

    public void setEmberOnTile(Ember emberOnTile) {
        this.emberOnTile = emberOnTile;
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
