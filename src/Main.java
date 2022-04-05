import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import javafx.scene.shape.Rectangle;
import units.Archer;
import units.Griff;
import units.Tile;
import units.Worker;

import static javafx.scene.paint.Color.rgb;

public class Main extends Application {


    //Game Stages:
    //-1 = Intro
    //0 = Tactical Phase
    //1 = Fight Phase
    public int gameStage = -1;
    public static int difficulty = 1;

    private static GridPane unitsGrid;

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

    public static void startGameMain(GridPane tilesGrid, GridPane uGrid, int diff) {
        unitsGrid = uGrid;
        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {tileArray[i][j] = new Tile(new Rectangle(rectSize, rectSize), i, j);
                tilesGrid.add(tileArray[i][j].getTileRect(), i, j);
                tileArray[i][j].getTileRect().setFill(rgb(74, 207, 110));
            }
        }
        setDifficulty(diff);

        //region selectorSetUp
        Worker worker = new Worker();
        ImageView workerImgView = worker.getImg();
        workerImgView.setFitHeight(50);
        workerImgView.setFitWidth(50);
        unitsGrid.add(workerImgView, 0, 0);
        unitsGrid.add(new Text("Földműves"), 0, 1);

        Archer archer = new Archer();
        ImageView archerImgView = archer.getImg();
        archerImgView.setFitHeight(50);
        archerImgView.setFitWidth(50);
        unitsGrid.add(archerImgView, 1, 0);
        unitsGrid.add(new Text("Íjász"), 1, 1);

        Griff griff = new Griff();
        ImageView griffImgView = griff.getImg();
        griffImgView.setFitHeight(50);
        griffImgView.setFitWidth(50);
        unitsGrid.add(griffImgView, 2, 0);
        unitsGrid.add(new Text("Griff"), 2, 1);
        //endregion
    }

    public static void setDifficulty(int diff) {
        difficulty = diff;
        System.out.println(difficulty);
    }

    public static void setUpUnitsGrid() {

    }



}
