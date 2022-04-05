import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.rgb;

public class FX_EventsController {

    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private ToggleGroup difficulty;

    @FXML
    private RadioButton difficultyEasy;

    @FXML
    private RadioButton difficultyHard;

    @FXML
    private RadioButton difficultyMedium;

    @FXML
    private VBox rootScene;

    @FXML
    private Pane sideBarPreGame;

    @FXML
    private Button startButton;

    @FXML
    private GridPane tilesGrid;

    @FXML
    void startGame(ActionEvent event) {
        System.out.println("fasz");
        int gridSizeX = 12;
        int gridSizeY = 10;
        int rectSize = 30;
        Tile[][] tileArray = new Tile[gridSizeX][gridSizeY];
        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {
                tileArray[i][j] = new Tile(new Rectangle(rectSize, rectSize), i, j);
                tilesGrid.add(tileArray[i][j].getTileRect(), i, j);
                tileArray[i][j].getTileRect().setFill(rgb(74, 207, 110));
            }
        }
    }


}
