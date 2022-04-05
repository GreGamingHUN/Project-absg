package units;

import javafx.scene.shape.Rectangle;
import units.*;

import static javafx.scene.paint.Color.rgb;

public class Tile {
    Unit unitOnTile = null;
    Rectangle tileRect = null;
    int posX, posY = -1;




    public Tile(Rectangle tileRect, int posX, int posY) {
        this.setTileRect(tileRect);
        this.getTileRect().setOnMouseEntered(e -> {
            this.tileRect.setFill(rgb(0, 0, 0));
        });

        this.getTileRect().setOnMouseExited(e -> {
            this.tileRect.setFill(rgb(74, 207, 110));
        });
        this.getTileRect().setOnMouseClicked(e -> {
            System.out.println(getPosX() + " " + getPosY());
        });
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
