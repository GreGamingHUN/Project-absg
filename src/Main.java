import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import javafx.scene.shape.Rectangle;
import units.Tile;

import static javafx.scene.paint.Color.rgb;

public class Main extends Application {


    //Game Stages:
    //-1 = Intro
    //0 = Tactical Phase
    //1 = Fight Phase
    public int gameStage = -1;
    public static int difficulty = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        VBox root = FXMLLoader.load(getClass().getResource("FX_Events.fxml"));
        primaryStage.setResizable(false);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fasz");
        primaryStage.show();
    }

    static int gridSizeX = 12;
    static int gridSizeY = 10;
    static int rectSize = 30;
    static Tile[][] tileArray = new Tile[gridSizeX][gridSizeY];

    public static void startGameMain(GridPane tilesGrid, int diff) {
        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {tileArray[i][j] = new Tile(new Rectangle(rectSize, rectSize), i, j);
                tilesGrid.add(tileArray[i][j].getTileRect(), i, j);
                tileArray[i][j].getTileRect().setFill(rgb(74, 207, 110));
            }
        }
        setDifficulty(diff);
    }

    public static void setDifficulty(int diff) {
        difficulty = diff;
        System.out.println(difficulty);
    }


}
