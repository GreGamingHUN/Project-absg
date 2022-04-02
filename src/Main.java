import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import javafx.scene.shape.Rectangle;
import java.util.Stack;

public class Main extends Application {

    Stage window;
    Scene scene1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        //Layout 1
        GridPane root = new GridPane();
        GridPane map = new GridPane();
        GridPane sideBar = new GridPane();

        map.setMinSize(400, 200);
        map.setPadding(new Insets(10, 10, 10, 10));
        map.setVgap(1);
        map.setHgap(1);
        map.setAlignment(Pos.CENTER);
        int gridSizeX = 12;
        int gridSizeY = 10;
        int rectSize = 60;
        root.add(map, 0, 0);
        root.add(sideBar, 1, 0);
        Tile[][] tiles = new Tile[gridSizeX][gridSizeY];

        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {
                tiles[i][j] = new Tile(new Rectangle(rectSize, rectSize), i, j);
                map.add(tiles[i][j].getTileRect(), i, j);
                tiles[i][j].getTileRect().setFill(Color.rgb(74, 207, 110));
            }
        }
        sideBar.add(new Label("Fasz"), 0, 0);
        sideBar.setMaxHeight(20);


        //root.getChildren().addAll();
        scene1 = new Scene(root);

        //Set Default Scene
        window.setScene(scene1);
        window.setTitle("ABSG");
        window.show();
    }

}
