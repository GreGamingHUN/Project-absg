package units;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Archer extends Unit{
    static String pathToImg = "img/archer.png";

    public Archer() {
        this.setImg(new ImageView(new Image(this.getPathToImg())));
        this.getImg().setOnMouseEntered(e -> {
            this.getImg().setOpacity(0.5);
        });

        this.getImg().setOnMouseExited(e -> {
            this.getImg().setOpacity(1);
        });
        this.getImg().setOnMouseClicked(e -> {
            System.out.println("Archer");
        });
    }

    public Archer(int price, int dmg, int hp, int speed, int priority, Hos parent) {
        super(price, dmg, hp, speed, priority, parent);
        this.setPrice(price);
        this.setMinDmg(dmg);
        this.setHp(hp);
        this.setSpeed(speed);
        this.setPriority(priority);
    }

    public String getPathToImg() {
        return pathToImg;
    }
}